package com.jeonghwahong.board.dto.post;

import com.jeonghwahong.board.dto.board.BoardDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class PostAddRequestDto {

    @NotNull
    private BoardDto boardDto;

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
