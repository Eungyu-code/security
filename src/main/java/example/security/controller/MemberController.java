package example.security.controller;

import example.security.domain.Address;
import example.security.domain.Member;
import example.security.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/add")
    public String addForm(@ModelAttribute MemberForm memberForm) {

        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String add(@Validated @ModelAttribute MemberForm memberForm, BindingResult bindingResult) {

        if (!StringUtils.hasText(memberForm.getNickname())) {
            bindingResult.rejectValue("nickname", "required", null, null);
        }

        if (!StringUtils.hasText(memberForm.getMail())) {
            bindingResult.rejectValue("mail", "required", null, null);
        }

        if (!StringUtils.hasText(memberForm.getPassword())) {
            bindingResult.rejectValue("password", "required", null, null);
        }

        if (memberService.duplicateName(memberForm.getNickname()) != null) {
            bindingResult.rejectValue("nickname", "duplicate", null, null);
        }

        if (memberService.duplicateMail(memberForm.getMail()) != null) {
            bindingResult.rejectValue("mail", "duplicate", null, null);
        }

        if (bindingResult.hasErrors()) {

            log.info("errors = {}", bindingResult.hasErrors());

            return "members/addMemberForm";
        }

        Member member = new Member();
        Address address = new Address(memberForm.getCountry(), memberForm.getCity(), memberForm.getStreet(), memberForm.getZipcode());
        member.create(memberForm.getNickname(), memberForm.getMail(), memberForm.getPassword(), address);

        memberService.join(member);

        return "redirect:/";
    }
}
