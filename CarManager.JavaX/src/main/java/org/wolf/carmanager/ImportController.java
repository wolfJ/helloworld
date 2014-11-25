package org.wolf.carmanager;

import com.alibaba.fastjson.JSON;
import jxl.Cell;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.wolf.carmanager.model.CarForm;
import org.wolf.carmanager.model.CarPO;
import org.wolf.carmanager.model.ImportPO;
import org.wolf.carmanager.persist.CarMapper;
import org.wolf.carmanager.persist.ImportMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ImportController {
    private static final String headStr = "车牌,车主,车辆型号,电话,发动机号,车架号,登记日期,车辆品牌,身份证号,保险到期,地址";
    private String[] headArr;

    {
        headArr = headStr.split(",");
    }

    @Autowired
    private CarMapper mapper;

    @Autowired
    private ImportMapper importMapper;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;


    private Date start;

    private Date end;

    @RequestMapping(value = "/hello.do")
    public
    @ResponseBody
    String hello(@RequestParam("name") String name) {
        try {
            CarPO po = getCarPO();
            mapper.adCar(po);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "hi!";
    }

    @RequestMapping(value = "/helloreq.do")
    public
    @ResponseBody
    String hello(HttpServletRequest req) {
        return "hi!";
    }

    @RequestMapping(value = "/queryx.do", produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    String queryx( CarForm form, HttpServletRequest req, HttpServletResponse reps) {
//        param.setParam(form);
        PageQueryParam<CarForm> param = new PageQueryParam<CarForm>(form,form.getiDisplayStart(),form.getiDisplayLength());
        List<CarPO> list = new ArrayList<CarPO>();
//         list = this.mapper.selectCars(param);
        list = mapper.selectAll();
        int totalCount = this.mapper.countCars(param);
        PageQueryResult result = new PageQueryResult();
        result.setData(list);
        result.setDraw(form.getiDisplayStart());
        result.setRecordsFiltered(form.getiDisplayLength());
       // result.setRecordsTotal(totalCount);
        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/query.do")
    public
    @ResponseBody
    List<CarPO> query() { //
        List<CarPO> list = new ArrayList<CarPO>();
        list.add(getCarPO());
        list.add(getCarPO());
//        PageQueryParam<CarPO> param = new PageQueryParam<CarPO>();
//         list = this.mapper.selectCars(param);
//        int totalCount = this.mapper.countCars(param);
//        return new PaginationSupport(list, 2,10 ,0 );
        return list;
    }

    @RequestMapping(value = "/upload.do", method = RequestMethod.POST)
    public void handleFileUpload(@RequestParam MultipartFile file, HttpServletResponse response) {
        StringBuffer sb = new StringBuffer("开始处理上传的文件...");

        if (file.isEmpty()) {
            appendBuffer(sb, "上传了空的文件！");
        } else if (!file.getOriginalFilename().endsWith(".xls")) {
            appendBuffer(sb, "文件【" + file.getOriginalFilename() + "】不是XLS文件！");
        } else {

            try {
                Workbook book = Workbook.getWorkbook(convertToFIle(file));
                Sheet sheet = book.getSheet(0);
                if (validate(sheet, sb)) {
                    appendBuffer(sb, "验证表格格式通过.");
                    List<CarPO> pos = parseToPOs(sheet, sb);
                    appendBuffer(sb, "准备插入数据库...");
                    startTimer();
//                mapper.insertBatch(pos);//如果记录太多的话，需要更改mysql server的最大支持数，太麻烦。
                    insertBatch(pos, sb);
                    insertImportLog(file.getOriginalFilename());
                    appendBuffer(sb, "插入数据库完成。耗时：" + endTimerAndGetSeconds() + "秒.");
                }
            } catch (IOException e) {
                e.printStackTrace();
                appendBuffer(sb, "解析时遇到了IO异常：" + e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                appendBuffer(sb, "上传过程中遇到了异常：" + e.getMessage());
            }
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-cache");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "x-requested-with");
        try {
            String msg = URLEncoder.encode(sb.toString(), "UTF-8");
            response.addHeader("Location", "async.html?msg=" + msg);
            response.sendRedirect("async.html" + "?msg=" + msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        return "async.html?msg=" + sb.toString();
    }

    private void insertImportLog(String filename) {

        ImportPO ipo = new ImportPO();
        ipo.setImportFileName(filename);
        ipo.setImportTime(new Date());
        importMapper.insert(ipo);
    }

    private void insertBatch(List<CarPO> pos, StringBuffer sb) {

        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH);
        try {
            CarMapper car = session.getMapper(CarMapper.class);
            for (CarPO po : pos) {
                car.adCar(po);
            }
            session.commit();
            session.clearCache();
        } catch (Exception e) {
            e.printStackTrace();
            appendBuffer(sb, "保存数据时出现异常:" + e.getMessage());
        } finally {
            session.close();
        }

    }

    private List<CarPO> parseToPOs(Sheet sheet, StringBuffer sb) throws Exception {
        appendBuffer(sb, "开始解析文件内容...，共" + sheet.getRows() + "行");
        int rows = sheet.getRows();
        List<CarPO> list = new ArrayList<CarPO>();
        startTimer();
        int i = 1;
        try {
            for (i = 1; i < rows; i++) {
                Cell[] cells = sheet.getRow(i);
                CarPO po = new CarPO();
                //车牌,车主,车辆型号,电话,发动机号,车架号,登记日期,车辆品牌,身份证号,保险到期,地址
                if (cells.length > 0)
                    po.setChePai(cells[0].getContents());
                if (cells.length > 1)
                    po.setCheZhu(cells[1].getContents());
                if (cells.length > 2)
                    po.setCheXinHao(cells[2].getContents());
                if (cells.length > 3)
                    po.setDianHua(cells[3].getContents());
                if (cells.length > 4)
                    po.setFaDongJi(cells[4].getContents());
                if (cells.length > 5)
                    po.setCheJiaHao(cells[5].getContents());
                if (cells.length > 6)
                    po.setDengJiRQ(((DateCell) cells[6]).getDate());
                if (cells.length > 7)
                    po.setChePingPai(cells[7].getContents());
                if (cells.length > 8)
                    po.setShenFengZheng(cells[8].getContents());
                if (cells.length > 9)
                    po.setBaoXianRQ(((DateCell) cells[9]).getDate());
                if (cells.length > 10)
                    po.setDiZhi(cells[10].getContents());
                list.add(po);
            }
        } catch (Exception e) {
            appendBuffer(sb, "解析文件时异常，第【" + (i + 1) + "】行出现异常");
            throw e;
        }
        appendBuffer(sb, "解析文件内容结束，耗时：" + endTimerAndGetSeconds() + "秒.");
        return list;
    }

    private int endTimerAndGetSeconds() {
        if (start == null)
            start = new Date();
        end = new Date();
        return (new Date(end.getTime() - start.getTime())).getSeconds();
    }

    private void startTimer() {
        start = new Date();
    }

    private boolean validate(Sheet sheet, StringBuffer sb) {
        sb.append("\n开始验证表格格式...");
        boolean flag = true;
        if (sheet == null)
            return validateFailure(sb, "工作表为空.");

        Cell[] heads = sheet.getRow(0);
        if (heads.length < 10)
            return validateFailure(sb, "表格列数不足11列. 标准列头:" + headStr);

        for (int i = 0; i < 11; i++) {
            Cell head = heads[i];
            if (!headStr.contains(head.getContents()))
                return validateFailure(sb, "发现未定义的列：" + head.getContents() + ", 标准列头:" + headStr);
            if (!headArr[i].equals(head.getContents()))
                return validateFailure(sb, "第" + (i + 1) + "列不是" + headArr[i] + ", 标准列头:" + headStr);
        }

        return flag;
    }

    private boolean validateFailure(StringBuffer sb, String msg) {
        appendBuffer(sb, msg);
        return false;
    }

    private void appendBuffer(StringBuffer sb, String msg) {
        sb.append("<br/>\n" + msg);
    }

    private File convertToFIle(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        file.transferTo(convFile);
        return convFile;
    }


    private CarPO getCarPO() {
        CarPO po = new CarPO();
        po.setChePai("chepai");
        po.setCheJiaHao("ss");
        po.setChePingPai("aaa");
        po.setCheZhu("在大在");
        po.setDianHua(" 工工 ");
        po.setDiZhi("aaaa");
        po.setBaoXianRQ(new Date());
        po.setDengJiRQ(new Date());
        return po;
    }


    public class TmpPO {
        public String first_name;
        public String last_name;
        public String position;
        public String office;
        public String start_date;
        public String salary;

        public TmpPO() {
            first_name = "Airi";
            last_name = "Satou";
            position = "Accountant";
            start_date = "28th Nov 08";
            salary = "$162,700";
        }
    }


}
