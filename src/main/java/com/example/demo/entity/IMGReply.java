package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name="tbl_imgreply")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IMGReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rnum;          //답변글 프라이머리키
    @Column
    private Long inum;          //읽고 있는 게시물
    @Column
    private String rcontent;    //댓글 내용
    @Column
    private String regdate;     //댓글 시간
}