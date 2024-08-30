package com.jeonghwahong.board.dto;

import lombok.Data;

@Data
public class PostListResponseDto {

    private Long id;
    private String title;
    private String content;
    private Integer comment;
    private Integer view;
    private String boardName;
    private String createdName;
    private Integer like;

    public PostListResponseDto(Long id, String title, String content, Integer comment, Integer view, String boardName, String createdName, Integer like) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.comment = comment;
        this.view = view;
        this.boardName = boardName;
        this.createdName = createdName;
        this.like = like;
    }
}
