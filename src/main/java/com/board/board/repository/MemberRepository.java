package com.board.board.repository;

import com.board.board.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 규칙 어떤 entity
public interface MemberRepository extends JpaRepository <MemberEntity, Long>{
    Optional<MemberEntity> findByMemberEmail(String memberEmail);
}
