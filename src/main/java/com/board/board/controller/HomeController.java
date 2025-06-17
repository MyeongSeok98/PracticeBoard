package com.board.board.controller;

import com.board.board.dto.PostDTO;
import com.board.board.service.PostService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PostService postService;

    @GetMapping("/")
    public String index(Model model){

        List<PostDTO> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "index";
    }
}