package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

@Controller
@Slf4j
public class RequestParamController {

    @GetMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse res) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username =" + username);
        log.info("age = "+age);

        res.getWriter().write("ok");

    }

    @GetMapping("/request-param-v2")
    public void requestParamV2(@RequestParam String username, @RequestParam int age){
        log.info("username =" + username);
        log.info("age = "+age);
    }

    @GetMapping("/request-param-required")
    @ResponseBody
    public String requestParamRequired(@RequestParam(required = true) String username,
                                       @RequestParam(required = false) Integer age){
        //int 타입에는 null이 못들어온다. 때문에 Wrapper class인 Interger로 해주는 것
        log.info("username =" + username);
        log.info("age = "+age);

        return "ok";
    }

    @GetMapping("/request-param-default")
    @ResponseBody
    public String requestParamDefault(@RequestParam(defaultValue = "guest") String username,
                                     @RequestParam(defaultValue = "-1") int age){
        log.info("username =" + username);
        log.info("age = "+age);

        return "ok";
    }

    @ResponseBody
    @GetMapping("model-attribute-v1")
    public String modelAttributeV1(@RequestParam String username, @RequestParam int age){
        HelloData helloData = new HelloData();
        helloData.setAge(age);
        helloData.setUsername(username);

        log.info("helloData = "+ helloData);

        return "ok";
    }

    @ResponseBody
    @GetMapping("model-attribute-v2")
    public String modelAttributeV2(@ModelAttribute HelloData helloData){
        log.info("helloData = "+ helloData);

        return "ok";
    }

}
