package example.security.controller;

import example.security.dto.MemberDto;
import example.security.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/")
    public String index() {
        return "/home/index";
    }

    @GetMapping("/member/join")
    public String joinForm(Model model) {
        model.addAttribute("member", new MemberDto());

        return "/member/joinForm";
    }

    @PostMapping("/member/join")
    public String join(MemberDto memberDto) {
        memberService.join(memberDto);

        return "redirect:/";
    }

    @GetMapping("/member/login")
    public String login() {

        return "/member/loginForm";
    }

    @GetMapping("/member/info")
    public String info() {

        return "/member/user_info";
    }
}
