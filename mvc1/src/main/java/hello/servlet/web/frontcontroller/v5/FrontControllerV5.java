package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.MyModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3Adapter;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV4Adapter;
import hello.servlet.web.frontcontroller.v5.adapter.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerV5",urlPatterns = "/front-controller/v5/*")
public class FrontControllerV5 extends HttpServlet {
    Map<String, Object> handlerMap = new HashMap<>();
    List<MyHandlerAdapter> adapterList = new ArrayList<>();

    public FrontControllerV5() {
        initHandlerMap();
        initAdapterList();
    }

    private void initHandlerMap() {
        handlerMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
        handlerMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    private void initAdapterList() {
        adapterList.add(new ControllerV3Adapter());
        adapterList.add(new ControllerV4Adapter());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        Object handler = handlerMap.get(requestURI);

        MyHandlerAdapter handlerAdapter = getHandlerAdapter(handler);
        MyModelView modelView = handlerAdapter.handle(req,resp,handler);

        MyView myView = viewResolver(modelView);
        myView.render(modelView.getModel(),req,resp);

    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : adapterList) {
            if(adapter.support(handler)){
                return adapter;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 handler = " + handler);
    }

    private MyView viewResolver(MyModelView modelView){
        return new MyView("/WEB-INF/views/" + modelView.getViewName() + ".jsp");
    }
}
