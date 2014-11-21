package ds.wolf.cm;

import org.springframework.stereotype.Controller;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wolf on 2014/11/20.
 */

@Controller
public class CarController {

    private  String hello="helloWorld";
    @RequestMapping(value = "/hello")
    public String getMessage(HttpServletRequest request, HttpServletResponse response){
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

    @RequestMapping(value = "/import",method = RequestMethod.POST)
    public void importFile(HttpServletRequest request, HttpServletResponse response){

    }
    @RequestMapping(value = "/import",method = RequestMethod.POST)
    public void importFilex( @RequestParam("file") MultipartFile file){

    }

}
