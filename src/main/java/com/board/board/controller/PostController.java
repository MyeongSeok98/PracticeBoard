package com.board.board.controller;

import com.board.board.dto.PostDTO;
import com.board.board.service.PostService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000",
        allowCredentials = "true")
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/{post_id}")
    public ResponseEntity<?> viewPost(@PathVariable("post_id") Long post_id){
        PostDTO post = postService.findById(post_id);

        return ResponseEntity.ok(post);
    }

    @PostMapping("/write")
    public ResponseEntity<?> write(
            @RequestBody PostDTO postDTO,
            HttpSession httpSession){
        String writerName = (String) httpSession.getAttribute("loginName");
        postDTO.setPostWriter(writerName);

        postDTO.setLikes(0);
        System.out.println("postDTO = "+ postDTO);
        postService.save(postDTO);
        return new ResponseEntity<>(postDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> list(){
        List<PostDTO> all = postService.findAll();
        return ResponseEntity.ok(all);
    }
}
