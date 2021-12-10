package com.example.demo.service;

import com.example.demo.dto.IMGReplyDTO;
import com.example.demo.entity.IMGReply;
import com.example.demo.repository.IMGBoardReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IMGBoardReplyServiceImpl implements IMGBoardReplyService{

    @Autowired //레파지토리 == dao 생각하면 됨
    private final IMGBoardReplyRepository replyrepo;

    //받은dto를 엔터티로 바꾸고 IMGBoardReplyRepository로 저장 (테이블에 댓글 저장) 컨트롤러에서 사용
    @Override
    public void PostReply(IMGReplyDTO dto) {
        IMGReply reply = dtoToEntity(dto); //DTO->Entity로 변환
        replyrepo.save(reply);             //DB값 삽입
    }

    @Override
    public List<IMGReply> getreplyList(Long inum) {
        //내림차순 정렬해서 가져옴(프라이머리키(rnum) 기준으로 가져옴 (모든 답변글을 가져옴))
        Sort sort = Sort.by("rnum").descending();
        List<IMGReply> list =  replyrepo.findAllByInum(inum,sort);
        return list; //컨트롤러가 받아서 처리
    }


}
