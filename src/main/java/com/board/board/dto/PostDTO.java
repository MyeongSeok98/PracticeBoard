package com.board.board.dto;

import com.board.board.entity.CommentEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostDTO {
    private Long post_id;
    private String postName;
    private String postContent;
    private String postWriter;
    private int likes;
    private List<CommentEntity> comments = new ArrayList<>();
}
