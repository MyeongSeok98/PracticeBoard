package com.board.board.service;

import com.board.board.dto.MemberDTO;
import com.board.board.entity.MemberEntity;
import com.board.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

// Repository의 save는 jpa에서 지정해주는 것이기 때문에 반드시 save로 이름을 지정해야한다.
@Service
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class MemberService {
    private final MemberRepository memberRepository;
    public void save(MemberDTO memberDTO){
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
    }

    public MemberDTO login(MemberDTO memberDTO){
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        if(byMemberEmail.isPresent()){
            // 조회 결과 해당 이메일이 있을 경우
            MemberEntity memberEntity = byMemberEmail.get();
            if(memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())){
                // 비밀번호 일치
                MemberDTO memberDTO1 = MemberDTO.toMemberDTO(memberEntity);
                System.out.println("MemberService memberDTO : " + memberDTO1);
                return memberDTO1;
            }
            else{
                return null;
            }
        }
        else{
            return null;
        }
    }
}
