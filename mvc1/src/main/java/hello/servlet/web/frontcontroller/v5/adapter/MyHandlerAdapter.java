package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.MyModelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MyHandlerAdapter {
    boolean support(Object handler);
    MyModelView handle(HttpServletRequest req, HttpServletResponse resp,Object handler);
}
