package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {
    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void clear(){
        memberRepository.clear();
    }

    @Test
    void save(){
        Member m1 = new Member("m1", 123);
        memberRepository.save(m1);
        Member byId = memberRepository.findById(m1.getId());
        assertThat(m1).isEqualTo(byId);
    }

    @Test
    void findAll(){
        Member m1 = new Member("m1", 123);
        Member m2 = new Member("m2", 123);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> all = memberRepository.findAll();
        assertThat(all.size()).isEqualTo(2);
    }

}