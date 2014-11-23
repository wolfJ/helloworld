package ds.wolf.cm;

import org.springframework.stereotype.Controller;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.Map;

/**
 * Created by wolf on 2014/11/20.
 */

@Controller
@MultipartConfig
public class CarController {

    private String hello = "helloWorld";

    @RequestMapping(value = "/hello")
    public String getMessage(HttpServletRequest request, HttpServletResponse response) {
        String msg;
        if (request.getParameterMap().containsKey("state")) {
            String state = request.getParameter("state");
            boolean st = Boolean.parseBoolean(state);
            msg = "change!";
        } else {
            msg = "current state!";
        }
        try {
            response.getOutputStream().println(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/hellox")
    public
    @ResponseBody
    String getMessageX(@RequestParam String name) {
        String msg;
        msg = name;
        return msg;
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public void importFile(HttpServletRequest request, HttpServletResponse response) {
        Map map = request.getParameterMap();
        Part filePart = null; // Retrieves <input type="file" name="file">
        try {
            filePart = request.getPart("file");
            String filename = getFilename(filePart);
            InputStream is = filePart.getInputStream();
        // ... (do your job here)
            File file = new File("d:/"+filename);
            FileWriter fw = new FileWriter(file );
            FileOutputStream fos = new FileOutputStream(file);
            byte[] bytes = new byte[1024];
            int i=0;
            while ((i =is.read(bytes))>0){
                fos.write(bytes,0,i);
            }
            fos.flush();
            fos.close();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }


    @RequestMapping(value = "/importx", method = RequestMethod.POST)
    public void importFilex(@RequestParam MultipartFile file) {
        if (file != null) {
        }
    }

}
