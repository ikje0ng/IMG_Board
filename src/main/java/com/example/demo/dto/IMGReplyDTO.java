package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class IMGReplyDTO {
    //게시물 번호
    private Long rnum;
    //댓글 어떤 게시글에 포함되어있는지
    private Long inum;
    //댓글 내용
    private String rcontent;
    //댓글 등록날짜
    private String regdate;
}
