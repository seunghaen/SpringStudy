package hello.servlet.web.frontcontroller.v2;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.MyView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MemberListControllerV2 implements ControllerV2{
    private final MemberRepository memberRepository =  MemberRepository.getInstance();
    @Override
    public MyView process(HttpServletRequest req, HttpServletResponse res) {
        List<Member> members = memberRepository.findAll();
        req.setAttribute("members", members);
        return new MyView("/WEB-INF/views/members.jsp");
    }
}
