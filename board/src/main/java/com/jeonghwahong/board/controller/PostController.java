package com.jeonghwahong.board.controller;

import com.jeonghwahong.board.dto.board.BoardDto;
import com.jeonghwahong.board.dto.post.PostAddRequestDto;
import com.jeonghwahong.board.dto.post.PostDto;
import com.jeonghwahong.board.dto.post.PostPageResponseDto;
import com.jeonghwahong.board.entity.Post;
import com.jeonghwahong.board.service.BoardService;
import com.jeonghwahong.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final BoardService boardService;

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

    @GetMapping("/add")
    public String addForm(Model model){
        List<BoardDto> boards = boardService.findAllBoard();
        model.addAttribute("boards", boards);
        model.addAttribute("posts", new PostAddRequestDto());
        return "posts/addForm";
    }

    @PostMapping("/add")
    public String addPost(@Validated @ModelAttribute("boards") PostAddRequestDto postDto,
                          RedirectAttributes redirectAttributes){
        //post 저장 로직 (id)가져오자
        Post savedPost = postService.save(postDto);
        redirectAttributes.addAttribute("itemId", savedPost.getId());
        return "redirect:/posts/{itemId}";
    }
}
