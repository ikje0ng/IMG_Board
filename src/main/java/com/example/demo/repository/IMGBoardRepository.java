package com.example.demo.repository;

import com.example.demo.entity.IMGBoard;
import org.springframework.data.jpa.repository.JpaRepository;

//repository를 Service가 사용한다.
public interface IMGBoardRepository extends JpaRepository<IMGBoard,Long> {
}