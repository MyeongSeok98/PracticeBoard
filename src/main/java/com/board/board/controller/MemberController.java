package com.board.board.controller;

import com.board.board.dto.MemberDTO;
import com.board.board.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;

@CrossOrigin(origins = "http://localhost:3000",
        allowCredentials = "true")
@RestController
@RequestMapping("/api/members") // API 경로에 일관성을 주기 위해 공통 경로를 지정하는 것이 좋습니다.
@RequiredArgsConstructor
public class MemberController {

    public final MemberService memberService;

    // GET /login, GET /signup 은 React Router에서 페이지를 보여주는 역할이므로 백엔드에서 제거합니다.

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody MemberDTO memberDTO) {
        try {
            memberService.save(memberDTO);
            return new ResponseEntity<>("회원가입 성공!", HttpStatus.CREATED); // 201 Created
        } catch (Exception e) {
            return new ResponseEntity<>("회원가입 실패: " + e.getMessage(), HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            session.setAttribute("loginName", loginResult.getMemberName());

            // 성공 시, 로그인한 사용자 정보를 일부 반환해 줄 수 있습니다.
            Map<String, String> response = new HashMap<>();
            response.put("message", "로그인 성공!");
            response.put("memberName", loginResult.getMemberName());
            return ResponseEntity.ok(response); // 200 OK
        } else {
            return new ResponseEntity<>("이메일 또는 비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED); // 401 Unauthorized
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("로그아웃 되었습니다."); // 200 OK
    }
}