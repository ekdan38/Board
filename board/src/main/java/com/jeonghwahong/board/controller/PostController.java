package com.jeonghwahong.board.controller;

import com.jeonghwahong.board.dto.PostDto;
import com.jeonghwahong.board.dto.PostPageResponseDto;
import com.jeonghwahong.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/{id}")
    public String getPost(@PathVariable("id") Long id, Model model){
        PostDto post = postService.findPostById(id);
        model.addAttribute("post", post);
        return "posts/post";
    }

    @GetMapping("/search")
    public String search(@RequestParam("query") String query,
                                      @RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber, Model model){
        PostPageResponseDto post = postService.findByQuery(pageNumber, query);
        model.addAttribute("post", post);
        return "home";
    }

    @GetMapping("/searchApi")
    @ResponseBody
    public PostPageResponseDto searchApi(@RequestParam("query") String query,
                                   @RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber){
        PostPageResponseDto post = postService.findByQuery(pageNumber, query);
        return post;
    }
}
