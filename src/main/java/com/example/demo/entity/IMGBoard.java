package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name="tbl_imgboard")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IMGBoard {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long num;
    @Column
    private String subject;
    @Column
    private String content;
    @Column
    private String filename;
    @Column
    private String s_filename;
    @Column
    private String folderpath;
}


