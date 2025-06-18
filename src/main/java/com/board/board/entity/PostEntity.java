package com.board.board.entity;

import com.board.board.dto.PostDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "post")
public class PostEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_id;

    @Column
    private String postName;

    @Column
    private String postContent;

    @Column
    private String postWriter;

    @Column
    private int likes;

    @OneToMany(
            mappedBy = "post",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<CommentEntity> comments;

    public static PostEntity toPostEntity(PostDTO postDTO){
        PostEntity postEntity = new PostEntity();
        postEntity.setPostName(postDTO.getPostName());
        postEntity.setPostContent(postDTO.getPostContent());
        postEntity.setPostWriter(postDTO.getPostWriter());
        postEntity.setLikes(postDTO.getLikes());

        return postEntity;
    }
}