package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class IMGBoardDTO {
    private Long num;
    private String subject;     //Form 으로 부터 전달
    private String content;     //Form 으로 부터 전달
    private String folderpath;  //Controller에서 추가삽입
    private String filename;    //Controller에서 추가삽입
    private String s_filename;  //Controller에서 추가삽입
}
