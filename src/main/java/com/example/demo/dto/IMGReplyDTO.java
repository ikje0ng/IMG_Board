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
    //댓글 번호
    private Long rnum;
    //이미지 번호
    private Long inum;
    //댓글 내용
    private String rcontent;
    //댓글 등록날짜
    private String regdate;
}
