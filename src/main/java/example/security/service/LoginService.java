package example.security.service;

import example.security.domain.Member;
import example.security.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {

        Member member = memberRepository.findByMail(mail);

        if (member == null) {
            throw new UsernameNotFoundException(mail);
        }

        return User.builder()
                .username(member.getMail())
                .password(member.getPassword())
                .roles(member.getRole())
                .build();
    }

}
