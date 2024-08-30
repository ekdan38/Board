package com.jeonghwahong.board.service;

import com.jeonghwahong.board.dto.PostListResponseDto;
import com.jeonghwahong.board.dto.PostResponseDto;
import com.jeonghwahong.board.entity.Post;
import com.jeonghwahong.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;

    public Page<PostListResponseDto> findAllPosts(int pageNumber){
        PageRequest pageRequest = PageRequest.of(pageNumber, 6, Sort.by(Sort.Direction.DESC,"id"));
        Page<Post> page = postRepository.findAll(pageRequest);
        return page.map(post ->
                new PostListResponseDto(post.getId()
                        , post.getTitle()
                        , post.getContent()
                        , post.getComments().size()
                        , post.getView()
                        , post.getBoard().getName()
                        , post.getMember().getName()
                        , post.getLikes().size()));
    }

    /**
     * 나중에 exception 커스텀 하던 하자.(test 다시 돌리자)
     * n + 1발생 해결
     */
    public PostResponseDto findPostById(Long id){
        log.info("PostService.findPostById 시작");
        Post post = postRepository.findPostById(id)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 게시물입니다."));

        post.increaseView();
        postRepository.save(post);

        return new PostResponseDto(
                post.getTitle(),
                post.getContent(),
                post.getLastModifiedDate(),
                post.getView(),
                post.getLikes().size(),
                post.getComments().size(),
                post.getMember().getName(),
                post.getBoard().getName()
        );
    }

    public Page<PostListResponseDto> findByQuery(int pageNumber, String query){
        log.info("findByQuery");
        PageRequest pageRequest = PageRequest.of(pageNumber, 6, Sort.by(Sort.Direction.DESC, "id"));
        Page<Post> page = postRepository.findBySearch(query, pageRequest);
        return page.map(post ->
                new PostListResponseDto(post.getId()
                        , post.getTitle()
                        , post.getContent()
                        , post.getView()
                        , post.getComments().size()
                        , post.getBoard().getName()
                        , post.getMember().getName()
                        , post.getLikes().size()));
    }

}
