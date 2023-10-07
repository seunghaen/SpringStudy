package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.MyModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3Adapter implements MyHandlerAdapter{
    @Override
    public boolean support(Object handler) {
        return (handler instanceof ControllerV3);
    }

    @Override
    public MyModelView handle(HttpServletRequest req, HttpServletResponse resp, Object handler) {
        ControllerV3 controller = (ControllerV3) handler;
        Map<String, String> paramMap = createParamMap(req);
        return controller.process(paramMap);
    }

    public Map<String,String> createParamMap(HttpServletRequest req){
        Map<String, String> map = new HashMap<>();
        req.getParameterNames().asIterator().forEachRemaining(name->map.put(name,req.getParameter(name)));
        return map;
    }
}
