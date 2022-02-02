package example.security.service;

import example.security.domain.Member;
import example.security.dto.MemberDto;
import example.security.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(MemberDto memberDto) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));

        return memberRepository.save(memberDto.toEntity()).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Member> memberWrapper = memberRepository.findByUsername(username);
        Member member = memberWrapper.get();

        List<GrantedAuthority> auth = new ArrayList<>();

        if ("admin".equals(username)) {
            auth.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            auth.add(new SimpleGrantedAuthority((Role.MEMBER.getValue())));
        }

        return new User(member.getUsername(), member.getPassword(), auth);
    }
}
