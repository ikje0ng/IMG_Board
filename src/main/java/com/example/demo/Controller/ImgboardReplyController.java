package com.example.demo.Controller;

import com.example.demo.dto.IMGReplyDTO;
import com.example.demo.service.IMGBoardReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

//일반적인 컨트롤러 맵핑경로 > 컨트롤러 > 뷰 > 웹브라우저 (뷰를 통해서 페이지가 이동)

//RestController url정보 받았을 때 맵핑 후 전달 (json,xml데이터를 전달)
//(이동없이 현재페이지를 기준으로 데이터를 가져올 수 있음 (이동할 수도 있음))
@RestController
@RequiredArgsConstructor
@Log4j2
public class ImgboardReplyController {

    @Autowired
    private final IMGBoardReplyService service;

    @PostMapping("/Imgboard/replypost.do")
    public void ImgreplyList(IMGReplyDTO dto){
        //서비스 실행;
        log.info("전달받은Reply : " + dto.toString());
        service.PostReply(dto);
    }

}
