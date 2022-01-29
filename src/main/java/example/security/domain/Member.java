package example.security.domain;

import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String nickname;

    @Column(unique = true)
    private String mail;

    private String password;

    private String role;

    @Embedded
    private Address address;

    public void create(String nickname, String mail, String password, Address address) {

        this.mail = mail;
        this.nickname = nickname;
        this.password = password;
        this.address = address;
    }

    public void encodePassword(PasswordEncoder passwordEncoder) {

        this.password = passwordEncoder.encode(this.password);
    }
}
