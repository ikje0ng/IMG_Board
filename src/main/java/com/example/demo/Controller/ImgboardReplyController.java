package com.example.demo.Controller;

import com.example.demo.dto.IMGReplyDTO;
import com.example.demo.entity.IMGReply;
import com.example.demo.service.IMGBoardReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//일반적인 컨트롤러 맵핑경로 > 컨트롤러 > 뷰 > 웹브라우저 (뷰를 통해서 페이지가 이동)

//RestController url정보 받았을 때 맵핑 후 전달 (json,xml데이터를 전달)
//(이동없이 현재페이지를 기준으로 데이터를 가져올 수 있음 (이동할 수도 있음))
@RestController
@RequiredArgsConstructor
@Log4j2
public class ImgboardReplyController {

    @Autowired
    private final IMGBoardReplyService service;


    //돌아오긴 하는데 값을 전달하지는 않아서 제이슨 값을 바디에 실어서 보내줄 수 있도록 수정
    @PostMapping("/Imgboard/replypost.do")
    //IMGReplyDTO dto : 폼으로 부터 받은 값 저장
    //ResponseEntity 응답에 entity를 실어서 전달 reply내용
    public ResponseEntity<List<IMGReply>> ImgreplyList(IMGReplyDTO dto){
        List<IMGReply> list = null;

        log.info("전달받은Reply : " + dto.toString());
        //답변서비스 실행; (post하고나서 결과를 가져오도록) / 답변 하지 않더라도 게시글에 달린 답변을 다 가져옴
        //1. 답변버튼을 클릭했는지 구분(dto값안에서 rcontent 꺼내서 비교)
        if (dto.getRcontent()!=null) {  //만약 답변이 달렸다면
            service.PostReply(dto);
        }
        //2. 모든 답변글 가져오기(이미지 게시물 번호를 갖고 있는 리플들 리스트형태로 꺼내와서 연결하고 리턴) 답글 있던 말든 가져옴
        list = service.getreplyList(dto.getInum());

        //ResponseEntity객체로 보고 있는 페이지로 리턴(상수형태값 전달) (list : 서비스에서 받아온 값, HttpStatus : 상태코드 함께 전달)
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
