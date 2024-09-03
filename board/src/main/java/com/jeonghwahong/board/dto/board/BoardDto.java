package com.jeonghwahong.board.dto.board;

import lombok.Data;

@Data
public class BoardDto {

    private Long id;
    private String name;

    public BoardDto() {
    }

    public BoardDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
