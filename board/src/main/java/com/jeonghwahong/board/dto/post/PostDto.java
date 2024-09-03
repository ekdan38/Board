package com.jeonghwahong.board.dto.post;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDto {
    private Long id;
    private String boardName;
    private String createdBy;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private Integer view;
    private Integer like;
    private Integer comment;

    public PostDto(Long id, String boardName,
                   String createdBy,
                   String title,
                   String content,
                   LocalDateTime createdDate,
                   LocalDateTime lastModifiedDate,
                   Integer view,
                   Integer like,
                   Integer comment) {
        this.id = id;
        this.boardName = boardName;
        this.createdBy = createdBy;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.view = view;
        this.like = like;
        this.comment = comment;
    }
}
