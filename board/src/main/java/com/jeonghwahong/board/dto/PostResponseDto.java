package com.jeonghwahong.board.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostResponseDto {
    private String title;
    private String content;
    private LocalDateTime modifiedDate;
    private Integer view;
    private Integer like;
    private Integer comment;
    private String createName;
    private String boardName;

    public PostResponseDto(String title, String content, LocalDateTime modifiedDate, Integer view, Integer like, Integer comment, String createName, String boardName) {
        this.title = title;
        this.content = content;
        this.modifiedDate = modifiedDate;
        this.view = view;
        this.like = like;
        this.comment = comment;
        this.createName = createName;
        this.boardName = boardName;
    }
}
