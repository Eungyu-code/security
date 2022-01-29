package example.security.service;

import example.security.domain.Member;
import example.security.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    static final String nickname = "이은규";
    static final String mail = "abc";
    static final String password = "qqq";

    @Autowired
    EntityManager em;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @BeforeEach
    void clear() {
        em.clear();
    }


    @Test
    void join() {

        //given
        Member member = new Member();


        //when
        member.create(nickname, mail, password, null);
        memberService.join(member);

        Member findMember = memberRepository.findByMail(mail);

        assertThat(findMember).isEqualTo(member);
    }

    @Test
    void noResultException() {

        assertThat(memberService.duplicateMail(mail)).isEqualTo(null);
        assertThat(memberService.duplicateName(nickname)).isEqualTo(null);
    }

    @Test
    void duplicate() {

        //given
        Member member = new Member();

        //when
        member.create(nickname, mail, password, null);
        memberService.join(member);

        Member findMember = memberService.duplicateMail(mail);

        //then
        assertThat(member).isEqualTo(findMember);
    }
}