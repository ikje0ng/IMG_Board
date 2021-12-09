package com.example.demo.service;

import com.example.demo.dto.IMGBoardDTO;
import com.example.demo.entity.IMGBoard;
import com.example.demo.repository.IMGBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service    //컨트롤러에 전달할 목적으로 사용됨
@RequiredArgsConstructor
public class IMGBoardServiceImpl implements IMGBoardService {

    @Autowired
    private final IMGBoardRepository IMGrepo;

    //post
    @Override
    public void PostImg(IMGBoardDTO dto) {
        IMGBoard board = dtoToEntity(dto); //DTO->Entity 변환
        IMGrepo.save(board);    //DB에 Entity 내용 저장
    }

    //list
    @Override
    public List<IMGBoard> getTotalIMGBoard() {
        //넘버를 기준으로 내림차순 지정
        Sort sort = Sort.by("num").descending();
        //리스트 형으로 담아서 전달
        List<IMGBoard> list = IMGrepo.findAll(sort);
        return list;
    }

    //read
    @Override
    public IMGBoard getIMGBoard(Long num) {
        //DB의 NUM을 찾아서 번호에 해당하는 모든 한 행 데이터를 갖고 오는 것 (1번을 찾으면 제목, 내용,파일이름 등등 다 갖고 옴)
        Optional<IMGBoard> board = IMGrepo.findById(num);
        return board.get();
    }
}
