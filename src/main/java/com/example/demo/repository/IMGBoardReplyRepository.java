package com.example.demo.repository;

import com.example.demo.entity.IMGReply;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//<엔티티, 프라이머리키 자료형>
public interface IMGBoardReplyRepository extends JpaRepository<IMGReply,Long> {
    //내가 원하는 형태의 함수 주입하면 알아서 바디 부분에서 사용 가능
    //inum을 조건절로 하는 함수 생성 (IMGReply)반환 (inum일 기준으로 모든 답변들 갖고 옴, 전달은 inum으로 전달 받음)
    List<IMGReply> findAllByInum(Long inum);

    //inum을 조건절로 하는 함수 생성(정렬조건 추가) (댓글 내림차순으로 하기위해 최근 댓글이 맨 위로 올라오도록)
    List<IMGReply> findAllByInum(Long inum, Sort sort);

    //IMGBoardReplyServiceImpl에서 사용함
}
