package com.board.board.service;

import com.board.board.dto.PostDTO;
import com.board.board.entity.PostEntity;
import com.board.board.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Long save(PostDTO postDTO){
        PostEntity postEntity = new PostEntity();
        postEntity.setPostName(postDTO.getPostName());
        postEntity.setPostContent(postDTO.getPostContent());
        postEntity.setPostWriter(postDTO.getPostWriter());
        postEntity.setLikes(postDTO.getLikes());

        return postRepository.save(postEntity).getPost_id();
    }

    @Transactional(readOnly = true)
    public List<PostDTO> findAll() {
        return postRepository.findAll().stream()
                .map(postEntity -> {
                    PostDTO postDTO = new PostDTO();
                    postDTO.setPost_id(postEntity.getPost_id());
                    postDTO.setPostName(postEntity.getPostName());
                    postDTO.setPostWriter(postEntity.getPostWriter());
                    postDTO.setPostContent(postEntity.getPostContent());
                    postDTO.setLikes(postEntity.getLikes());
                    return postDTO;
                }).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PostDTO findById(Long post_id){
        return postRepository.findById(post_id)
                .map(entity -> {
                    PostDTO dto = new PostDTO();
                    dto.setPost_id(entity.getPost_id());
                    dto.setPostName(entity.getPostName());
                    dto.setPostContent(entity.getPostContent());
                    dto.setLikes(entity.getLikes());
                    dto.setPostWriter(entity.getPostWriter());
                    return dto;
                }).orElseThrow(() -> new EntityNotFoundException("Post is nothing. id=" + post_id));
    }
}
