package com.jeonghwahong.board.controller;

import com.jeonghwahong.board.dto.PostListResponseDto;
import com.jeonghwahong.board.dto.PostResponseDto;
import com.jeonghwahong.board.repository.PostRepository;
import com.jeonghwahong.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/search")
    @ResponseBody
    public List<PostListResponseDto> search(@RequestParam("q") String query,
                                      @RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber){
        Page<PostListResponseDto> postServiceByQuery = postService.findByQuery(pageNumber, query);
        List<PostListResponseDto> content = postServiceByQuery.getContent();
        return content;
    }
}
