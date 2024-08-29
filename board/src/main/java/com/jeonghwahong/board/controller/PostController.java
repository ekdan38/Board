package com.jeonghwahong.board.controller;

import com.jeonghwahong.board.dto.PostResponseDto;
import com.jeonghwahong.board.repository.PostRepository;
import com.jeonghwahong.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/{id}")
    public String getPost(@PathVariable("id") Long id, Model model){
        PostResponseDto post = postService.findPostById(id);
        model.addAttribute("post", post);
        return "posts/post";
    }
}
