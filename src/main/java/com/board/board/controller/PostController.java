package com.board.board.controller;

import com.board.board.dto.PostDTO;
import com.board.board.service.PostService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/post/{post_id}")
    public String viewPost(@PathVariable("post_id") Long post_id, Model model){
        PostDTO post = postService.findById(post_id);

        model.addAttribute("post", post);
        return "post";
    }

    @GetMapping("/write")
    public String writeForm(){
        return "write";
    }

    @PostMapping("/write")
    public String write(
            @ModelAttribute PostDTO postDTO,
            HttpSession httpSession){
        String writerName = (String) httpSession.getAttribute("loginName");
        postDTO.setPostWriter(writerName);

        postDTO.setLikes(0);
        postService.save(postDTO);

        return "redirect:/";
    }
}
