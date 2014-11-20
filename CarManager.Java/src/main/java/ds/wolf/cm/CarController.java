package ds.wolf.cm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wolf on 2014/11/20.
 */

@Controller
public class CarController {


    @RequestMapping(value = "/hello")
    public
    @ResponseBody
    String getMessage(@RequestParam String name) {
        HttpServletRequest request
        String msg;
        if (request.getParameterMap().containsKey("state")) {
            String state = request.getParameter("state");
            boolean st = Boolean.parseBoolean(state);
            msg = "change!";
        } else {
            msg = "current state!";
        }
        return msg;
        return "hello wolf!!!!!!!!!!";
    }

}
