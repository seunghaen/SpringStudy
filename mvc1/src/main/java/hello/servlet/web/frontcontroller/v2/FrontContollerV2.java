package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "FrontContollerV2", urlPatterns = "/front-controller/v2/*")
public class FrontContollerV2 extends HttpServlet {
    Map<String, ControllerV2> controllerV1Map = new HashMap<>();

    public FrontContollerV2() {

        controllerV1Map.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerV1Map.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerV1Map.put("/front-controller/v2/members", new MemberListControllerV2());

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        System.out.println("requestURI = " + requestURI);
        ControllerV2 controller = controllerV1Map.get(requestURI);
        if (controller == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        MyView view = controller.process(req, resp);
        view.render(req,resp);
    }
}
