package com.example.demo.repository;

import com.example.demo.entity.IMGReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMGBoardReplyRepository extends JpaRepository<IMGReply,Long> {
}
