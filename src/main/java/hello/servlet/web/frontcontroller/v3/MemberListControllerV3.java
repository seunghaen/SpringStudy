package hello.servlet.web.frontcontroller.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.MyModelView;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3{
    MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public MyModelView process(Map<String, String> paramMap) {
        List<Member> members = memberRepository.findAll();

        MyModelView myModelView = new MyModelView("members");
        myModelView.getModel().put("members", members);

        return myModelView;

    }
}
