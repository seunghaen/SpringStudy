package hello.servlet.web.frontcontroller.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.MyModelView;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3{
    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyModelView process(Map<String, String> paramMap) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        MyModelView myModelView = new MyModelView("save-result");
        myModelView.getModel().put("member", member);
        return myModelView;
    }
}
