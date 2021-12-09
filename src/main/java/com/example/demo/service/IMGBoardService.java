package com.example.demo.service;


import com.example.demo.dto.IMGBoardDTO;
import com.example.demo.entity.IMGBoard;

import java.util.List;

//여기선 몸통만 구현한다.
public interface IMGBoardService {

    //POST IMGDTO
    void PostImg(IMGBoardDTO dto);

    //GETIMGBoardList
    List<IMGBoard> getTotalIMGBoard();

    //GetIMGBoard
    IMGBoard getIMGBoard(Long num);

    //DTO->ENTITY에 넣는 작업(save해야 되기 때문)
    //IMGBoardDTO:컨트롤러에서 전달함
    default IMGBoard dtoToEntity(IMGBoardDTO dto){
        IMGBoard board = IMGBoard.builder()
                .subject(dto.getSubject())
                .content(dto.getContent())
                .filename(dto.getFilename())
                .s_filename(dto.getS_filename())
                .folderpath(dto.getFolderpath())
                .build();
        return board;
    }
}
