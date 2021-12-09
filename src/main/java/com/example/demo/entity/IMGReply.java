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
    private Long rnum;
    @Column
    private Long inum;
    @Column
    private String rcontent;
    @Column
    private String regdate;
}


