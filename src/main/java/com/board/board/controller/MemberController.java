package com.board.board.controller;

import com.board.board.dto.MemberDTO;
import com.board.board.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    public final MemberService memberService;
    @GetMapping("login")
    public String loginForm(){
        return "login";
    }

    @GetMapping("signup")
    public String createMember(){
        return "CreateMember";
    }

    @PostMapping("/signup")
    public String savemember(@ModelAttribute MemberDTO memberDTO){
        System.out.println("MemberController.save");
        System.out.println("memberDTO = " + memberDTO);
        memberService.save(memberDTO);
        return "redirect:/";
    }

    @PostMapping("login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session){
        System.out.println("memberDTO = " + memberDTO);
        MemberDTO loginResult = memberService.login(memberDTO);
        if(loginResult != null){
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            session.setAttribute("loginName", loginResult.getMemberName());
            System.out.println("Login Success!");
            System.out.println(session.getAttribute("loginEmail"));
            return "redirect:/";
        }
        else{
            System.out.println("Login failed...");
            return "login";
        }
    }

    @PostMapping("logout")
    public String logout(HttpSession session){
        session.invalidate();
        System.out.println("logout success!");
        return "index";
    }
}
