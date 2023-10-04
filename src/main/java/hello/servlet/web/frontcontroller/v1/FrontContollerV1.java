package hello.servlet.web.frontcontroller.v1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "FrontContollerV1", urlPatterns = "/front-controller/v1/*")
public class FrontContollerV1 extends HttpServlet {
    Map<String, ControllerV1> controllerV1Map = new HashMap<>();

    public FrontContollerV1() {

        controllerV1Map.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerV1Map.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerV1Map.put("/front-controller/v1/members", new MemberListControllerV1());

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FrontContollerV1.service");
        String requestURI = req.getRequestURI();
        System.out.println("requestURI = " + requestURI);
        ControllerV1 controllerV1 = controllerV1Map.get(requestURI);
        System.out.println("controllerV1 = " + controllerV1);
        if (controllerV1 == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        controllerV1.process(req, resp);
    }
}
