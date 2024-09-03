package com.jeonghwahong.board.service;

import com.jeonghwahong.board.dto.board.BoardDto;
import com.jeonghwahong.board.entity.Board;
import com.jeonghwahong.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<BoardDto> findAllBoard(){
        return boardRepository.findAll().stream()
                .map(board -> new BoardDto(board.getId(), board.getName()))
                .collect(Collectors.toList());
    }

    /**
     * 꼭 오류 처리 해주자(커스텀 하던 뭐 하던)
     */
    public Board findById(long id){
        return boardRepository.findById(id).orElseThrow(()-> new NoSuchElementException("없는 board 입니다."));
    }
}
