package example.security.controller;

import example.security.domain.MemberTO;
import example.security.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;

    @GetMapping("/")
    public String home() {
        return "pages/home";
    }

    @GetMapping("/login")
    public String loginView() {
        return "pages/login";
    }

    @GetMapping("/join")
    public String joinView() {
        return "pages/join";
    }

    @PostMapping("/join")
    public String join(MemberTO memberTO) {

        memberService.save(memberTO);

        return "redirect:/login";
    }

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @GetMapping("/member/info")
    public String userInfoView() {
        return "pages/user_info";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String adminView() {
        return "pages/admin";
    }

    @GetMapping("/denied")
    public String deniedView() {
        return "pages/denied";
    }
}
