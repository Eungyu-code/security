package example.security.repository;

import example.security.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberDao extends JpaRepository<Member, Long> {

    Optional<Member> findByAccount(String account);
}
