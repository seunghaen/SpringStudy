package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.MyModelView;
import hello.servlet.web.frontcontroller.v4.ControllerV4;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4Adapter implements MyHandlerAdapter{
    @Override
    public boolean support(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public MyModelView handle(HttpServletRequest req, HttpServletResponse resp, Object handler) {
        ControllerV4 controllerV4 = (ControllerV4) handler;
        Map<String, Object> model = new HashMap<>();
        Map<String, String> paramMap = createParamMap(req);
        String viewName = controllerV4.process(paramMap, model);

        MyModelView myModelView = new MyModelView(viewName);
        myModelView.setModel(model);
        return myModelView;

    }

    public Map<String,String> createParamMap(HttpServletRequest req){
        Map<String, String> map = new HashMap<>();
        req.getParameterNames().asIterator().forEachRemaining(name->map.put(name,req.getParameter(name)));
        return map;
    }
}
