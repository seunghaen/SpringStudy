package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.MyModelView;
import hello.servlet.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "FrontContollerV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerV3 extends HttpServlet {
    Map<String, ControllerV3> controllerV1Map = new HashMap<>();

    public FrontControllerV3() {

        controllerV1Map.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerV1Map.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerV1Map.put("/front-controller/v3/members", new MemberListControllerV3());

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();

        ControllerV3 controller = controllerV1Map.get(requestURI);

        if (controller == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(req);
        MyModelView mv = controller.process(paramMap);


        MyView view = viewResolver(mv);
        view.render(mv.getModel(),req,resp);
    }

    private static MyView viewResolver(MyModelView mv) {
        String viewPath = "/WEB-INF/views/"+ mv.getViewName() + ".jsp";
        return new MyView(viewPath);
    }

    private static Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String, String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator().forEachRemaining(name->paramMap.put(name, req.getParameter(name)));
        return paramMap;
    }
}
