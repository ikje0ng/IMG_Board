package com.example.demo.service;

import com.example.demo.dto.IMGReplyDTO;
import com.example.demo.entity.IMGReply;
import com.example.demo.repository.IMGBoardReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IMGBoardReplyServiceImpl implements IMGBoardReplyService{

    @Autowired //레파지토리 == dao 생각하면 됨
    private final IMGBoardReplyRepository replyrepo;

    //받은dto를 엔터티로 바꾸고 IMGBoardReplyRepository로 저장
    @Override
    public void PostReply(IMGReplyDTO dto) {
        IMGReply reply = dtoToEntity(dto); //DTO->Entity로 변환
        replyrepo.save(reply);             //DB값 삽입
    }
}
