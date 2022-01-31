package example.security.domain;

import java.time.LocalDateTime;

public class MemberTO {

    private String name;

    private String password;

    private String account;

    private LocalDateTime lastAccessDt;

    private LocalDateTime regDt;

    public Member toEntity() {
        Member member = new Member();
        member.create(name, account, password);

        return member;
    }
}
