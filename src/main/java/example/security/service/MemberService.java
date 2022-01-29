package example.security.service;

import example.security.domain.Member;
import example.security.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void join(Member member) {

        member.encodePassword(passwordEncoder);

        memberRepository.join(member);
    }

    public Member duplicateMail(String mail) {

        return memberRepository.findByMail(mail);
    }

    public Member duplicateName(String nickname) {

        return memberRepository.findByName(nickname);
    }
}
