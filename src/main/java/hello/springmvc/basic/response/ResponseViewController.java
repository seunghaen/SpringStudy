package hello.springmvc.basic.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class ResponseViewController {
    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1(){
        return new ModelAndView("response/hello").addObject("data", "hello!");
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model){
        model.addAttribute("data","Hello!");
        return "response/hello";
    }
}
