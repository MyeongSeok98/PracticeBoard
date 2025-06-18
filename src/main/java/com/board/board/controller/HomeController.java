package com.board.board.controller;

import com.board.board.dto.PostDTO;
import com.board.board.service.PostService;
// import jakarta.servlet.http.HttpSession; // 사용하지 않으므로 제거
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller; // 사용하지 않으므로 제거
// import org.springframework.ui.Model; // 사용하지 않으므로 제거
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000",
        allowCredentials = "true")
@RestController
@RequiredArgsConstructor
public class HomeController {

    private final PostService postService;

    @GetMapping("/")
    public ResponseEntity<List<PostDTO>> index() { // 1. Model 파라미터 제거

        List<PostDTO> posts = postService.findAll();

        // 2. model.addAttribute(...) 라인 제거 (의미 없는 코드)

        // 3. 조회한 데이터를 바로 HTTP 응답 본문에 담아 반환
        return ResponseEntity.ok(posts);
    }
}