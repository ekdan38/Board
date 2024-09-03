package com.jeonghwahong.board.dto.post;

import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class PostPageResponseDto {
    private String query;
    private Page<PostDto> posts;

    public PostPageResponseDto(String query, Page<PostDto> posts) {
        this.query = query;
        this.posts = posts;
    }
}
