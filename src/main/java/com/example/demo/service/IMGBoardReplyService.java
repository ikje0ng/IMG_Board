package com.example.demo.service;

import com.example.demo.dto.IMGReplyDTO;
import com.example.demo.entity.IMGReply;

import java.time.LocalDate;
import java.util.List;

public interface IMGBoardReplyService {

    //PostReply(IMGReplyDTO를 받아서 DB로 넘겨주겠다.)
    void PostReply(IMGReplyDTO dto);

    ////현재 읽고 있는 게시물에 달린 모든 댓글 가져오기(읽고 있는 게시물번호를 인자로 받기)
    List<IMGReply> getreplyList(Long inum);

    //외부로 전달 받은 DTO -> ENTITY로 변환
    default IMGReply dtoToEntity(IMGReplyDTO dto){
        //현재 날짜 받아옴
        LocalDate now = LocalDate.now();

        IMGReply reply=IMGReply.builder()
                .rcontent(dto.getRcontent())
                .inum(dto.getInum())
                .regdate(now.toString())
                .build();
        return reply;   //IMGBoardReplyServiceImpl클래스에서 서비스 완성시킴
    }
}
