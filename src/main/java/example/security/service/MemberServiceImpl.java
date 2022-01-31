package example.security.service;

import example.security.domain.Member;
import example.security.domain.MemberTO;
import example.security.repository.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {

        Optional<Member> memberEntityWrapper = memberDao.findByAccount(account);
        Member memberEntity = memberEntityWrapper.orElse(null);

        List<GrantedAuthority> auth = new ArrayList<>();

        auth.add(new SimpleGrantedAuthority("ROLE_MEMBER"));

        return new User(memberEntity.getAccount(), memberEntity.getPassword(), auth);
    }

    @Transactional
    @Override
    public Long save(MemberTO memberTO) {

        Member member = memberTO.toEntity();
        member.setLastAccessDt(LocalDateTime.now());
        member.setRegDt(LocalDateTime.now());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        member.setPassword(passwordEncoder.encode(member.getPassword()));

        return memberDao.save(member).getId();
    }
}
