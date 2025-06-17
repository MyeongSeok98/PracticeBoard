package com.board.board.repository;

import com.board.board.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository  extends JpaRepository<PostEntity, Long> {

}
