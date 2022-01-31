package example.security.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String account;

    private String password;

    private LocalDateTime lastAccessDt;

    private LocalDateTime regDt;

    protected Member() {

    }

    public void create(String name, String account, String password) {
        this.name = name;
        this.account = account;
        this.password = password;
    }

}
