package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.MyModelView;

import java.util.Map;

public interface ControllerV3 {
    MyModelView process(Map<String,String> paramMap);
}
