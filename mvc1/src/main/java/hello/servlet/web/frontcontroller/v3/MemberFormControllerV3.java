package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.MyModelView;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3{

    @Override
    public MyModelView process(Map<String, String> paramMap) {
        return new MyModelView("new-form");
    }
}
