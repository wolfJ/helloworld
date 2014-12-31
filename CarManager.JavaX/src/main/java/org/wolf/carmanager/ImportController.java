package org.wolf.carmanager;

import com.alibaba.fastjson.JSON;
import jxl.*;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ImportController {
    private Logger logger = LoggerFactory.getLogger(ImportController.class);
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

    private static String defaultDatePattern = "yyyy-MM-dd";
    private static String defaultDateTimePattern = "yyyy-MM-dd HH:mm:ss";
    private Date start;

    private Date end;

    @RequestMapping(value = "/queryImportLog.do", produces = {"application/json;charset=UTF-8"})
    public
    @ResponseBody
    String queryImportLog(HttpServletRequest req, HttpServletResponse reps) {
        logger.error("do queryImportLog.do");
        List<ImportPO> list = importMapper.selectAll();
        PageQueryResult result = new PageQueryResult();
        result.setData(list);
        return JSON.toJSONString(result);
    }


    @RequestMapping(value = "/queryx.do", produces = {"application/json;charset=UTF-8"})
    public
    @ResponseBody
    String queryx(CarForm form, HttpServletRequest req, HttpServletResponse reps) {
        PageQueryResult result = new PageQueryResult();
        try {
            PageQueryParam<CarForm> param = new PageQueryParam<CarForm>(form, form.getiDisplayStart(), form.getiDisplayLength());
            List<CarPO> list = new ArrayList<CarPO>();
            if(!StringUtils.isEmpty(param.getParam().chePai)){
                param.getParam().chePai = param.getParam().chePai.toUpperCase();
            }
            startTimer();
            list = this.mapper.selectCarsOptCP(param);
            logger.info("selectCarsOptCP:{}, time:{}ms",param.getParam().chePai,endTimerAndGetMills());
            if(!StringUtils.isEmpty(param.getParam().chePai) && (list==null || list.isEmpty()) ) {
                startTimer();
                list = this.mapper.selectCarsNormal(param);
                logger.info("selectCarsNormal:{}, time:{}ms",param.getParam().chePai,endTimerAndGetMills());
            }
            int totalCount ;
            if(!StringUtils.isEmpty(param.getParam().chePai)
                    || !StringUtils.isEmpty(param.getParam().dianHua)
                    ||!StringUtils.isEmpty(param.getParam().faDongJi)
                    ||!StringUtils.isEmpty(param.getParam().shenFengZheng) ){
                totalCount = 10;
            }else{
                startTimer();
                totalCount = this.mapper.countCars(param);
                logger.info("countCars, time:{}ms",endTimerAndGetMills());
            }
            result.setData(list);
            result.setDraw(form.getiDisplayStart());
            result.setiTotalDisplayRecords(totalCount);
            result.setiTotalRecords(totalCount);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return JSON.toJSONString(result);
    }


    @RequestMapping(value = "/upload.do", method = RequestMethod.POST)
    public void handleFileUpload(@RequestParam MultipartFile file, @RequestParam("fileName") String fileName, HttpServletRequest request, HttpServletResponse response) {
        StringBuffer sb = new StringBuffer("开始处理上传的文件...");
        try {
            fileName = URLDecoder.decode(fileName,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        logger.info("do upload.do...");
        if (file.isEmpty() || StringUtils.isEmpty(fileName)) {
            appendBuffer(sb, "上传了空的文件！");
            logger.warn("上传了空的文件...");
        } else if (!fileName.endsWith(".xls")) {
            appendBuffer(sb, "文件【" + fileName + "】不是XLS文件！");
            logger.warn("文件【" + fileName + "】不是XLS文件！");
        } else {
            try {
                startTimer();
                Workbook book = Workbook.getWorkbook(convertToFIle(file,fileName));
                Sheet sheet = book.getSheet(0);
                if (validate(sheet, sb)) {
                    appendBuffer(sb, "验证表格格式通过.");
                    List<CarPO> pos = parseToPOs(sheet, sb);
                    appendBuffer(sb, "准备插入数据库...");
                    startTimer();
//                mapper.insertBatch(pos);//如果记录太多的话，需要更改mysql server的最大支持数，太麻烦。
                    boolean insert = insertBatch(pos, sb);
                    if (insert)
                        insertImportLog(fileName);
                    appendBuffer(sb, "插入数据库结束。 共" + pos.size() + "条记录， 耗时：" + endTimerAndGetSeconds() + "秒.");
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
            logger.error("do response exp:" + e.getMessage());
        }
//        return "async.html?msg=" + sb.toString();
    }

    private void insertImportLog(String filename) {
        ImportPO ipo = new ImportPO();
        ipo.setImportFileName(filename);
        ipo.setImportTime(new Date());
        importMapper.insert(ipo);
    }

    private boolean insertBatch(List<CarPO> pos, StringBuffer sb) {
        try {
            int count = pos.size();
            int start = 0;
            int end = 0;
            while (!(start == count)) {
                end += 2000;
                if (end >= count) {
                    end = count;
                }
                List<CarPO> toSaveList = pos.subList(start, end);
                mapper.insertBatch(toSaveList);
                start = end;
            }
            appendBuffer(sb, "保存数据成功:)");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("insertBatch exp:" + e.getMessage());
            appendBuffer(sb, "保存失败，插入时出现异常:" + e.getMessage());
            return false;
        }
    }

    private List<CarPO> parseToPOs(Sheet sheet, StringBuffer sb) throws Exception {
        appendBuffer(sb, "开始解析文件内容...，共" + sheet.getRows() + "行");
        int rows = sheet.getRows();
        List<CarPO> list = new ArrayList<CarPO>();
        int i = 1;
        try {
            Cell[] heads = sheet.getRow(0);
            for (i = 1; i < rows; i++) {
                Cell[] cells = sheet.getRow(i);

                CarPO po = new CarPO();
                //车牌,车主,车辆型号,电话,发动机号,车架号,登记日期,车辆品牌,身份证号,保险到期,地址
                for (int j = 0; j < cells.length; j++) {
                    if ("车牌".equals(heads[j].getContents())) {
                        po.setChePai(cells[j].getContents().toUpperCase());
                    } else if ("车主".equals(heads[j].getContents())) {
                        po.setCheZhu(cells[j].getContents());
                    } else if ("车辆型号".equals(heads[j].getContents())) {
                        po.setCheXinHao(cells[j].getContents());
                    } else if ("电话".equals(heads[j].getContents())) {
                        po.setDianHua(cells[j].getContents());
                    } else if ("发动机号".equals(heads[j].getContents())) {
                        po.setFaDongJi(cells[j].getContents());
                    } else if ("车架号".equals(heads[j].getContents())) {
                        po.setCheJiaHao(cells[j].getContents());
                    } else if ("车辆品牌".equals(heads[j].getContents())) {
                        po.setChePingPai(cells[j].getContents());
                    } else if ("身份证号".equals(heads[j].getContents())) {
                        po.setShenFengZheng(cells[j].getContents());
                    } else if ("地址".equals(heads[j].getContents())) {
                        po.setDiZhi(cells[j].getContents());
                    } else if ("登记日期".equals(heads[j].getContents()) && !StringUtils.isEmpty(cells[j].getContents())) {
                        if (cells[j].getType() == CellType.DATE) {
                            po.setDengJiRQ(((DateCell) cells[j]).getDate());
                        } else {
                            po.setDengJiRQ(getDateFromStr(cells[j].getContents(), sb));
                        }
                    } else if ("保险到期".equals(heads[j].getContents()) && !StringUtils.isEmpty(cells[j].getContents())) {
                        if (cells[j].getType() == CellType.DATE) {
                            po.setBaoXianRQ(((DateCell) cells[j]).getDate());
                        } else {
                            po.setBaoXianRQ(getDateFromStr(cells[j].getContents(), sb));
                        }
                    }

                }
                list.add(po);
            }
        } catch (Exception e) {
            appendBuffer(sb, "解析文件时异常，第【" + (i + 1) + "】行出现异常");
            logger.error("解析文件时异常，第【" + (i + 1) + "】行出现异常 exp:" + e.getMessage());
            throw e;
        }
        appendBuffer(sb, "解析文件内容结束，耗时：" + endTimerAndGetSeconds() + "秒.");
        return list;
    }

    private Date getDateFromStr(String strDate, StringBuffer sb) throws Exception {
        try {
            if (defaultDatePattern.length() == strDate.length()) {
                return new SimpleDateFormat(defaultDatePattern).parse(strDate);
            } else if (defaultDateTimePattern.length() == strDate.length()) {
                return new SimpleDateFormat(defaultDateTimePattern).parse(strDate);
            } else {
                return new SimpleDateFormat(defaultDatePattern).parse(strDate);
            }
        } catch (ParseException e) {
            try {
                return new SimpleDateFormat(defaultDateTimePattern).parse(strDate);
            } catch (ParseException e1) {
                String msg = "日期格式不正确：" + strDate + ", 只支持:" + defaultDatePattern + " 和 " + defaultDateTimePattern + " 两种格式.";
                logger.error(msg);
                appendBuffer(sb, msg);
                throw new Exception(msg);
            }
        }
    }

    private long endTimerAndGetSeconds() {
        if (start == null)
            start = new Date();
        end = new Date();
        return (end.getTime() - start.getTime()) / 1000;
    }
    private long endTimerAndGetMills() {
        if (start == null)
            start = new Date();
        end = new Date();
        return (end.getTime() - start.getTime());
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
                return validateFailure(sb, "前11列中，发现未定义的列：" + head.getContents() + ", 标准列头:" + headStr);
//            if (!headArr[i].equals(head.getContents()))
//                return validateFailure(sb, "第" + (i + 1) + "列不是" + headArr[i] + ", 标准列头:" + headStr);
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

    private File convertToFIle(MultipartFile file, String fileName) throws IOException {
        File convFile = new File(fileName);
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

}
