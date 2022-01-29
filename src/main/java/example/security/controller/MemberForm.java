package example.security.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {

    private String nickname;
    private String mail;
    private String password;

    private String country;
    private String city;
    private String street;
    private String zipcode;
}
