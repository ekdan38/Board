package com.jeonghwahong.board.controller;

import com.jeonghwahong.board.dto.post.PostPageResponseDto;
import com.jeonghwahong.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PostService postService;

    @GetMapping("/")
    public String home(@RequestParam (name = "pageNumber", defaultValue = "0") int pageNumber, Model model){
        PostPageResponseDto post = postService.findAllPosts(pageNumber);
        model.addAttribute("post", post);
        return "home";
    }
}
