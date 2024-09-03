package com.jeonghwahong.board.service;

import com.jeonghwahong.board.dto.post.PostAddRequestDto;
import com.jeonghwahong.board.dto.post.PostDto;
import com.jeonghwahong.board.dto.post.PostPageResponseDto;
import com.jeonghwahong.board.entity.Board;
import com.jeonghwahong.board.entity.Member;
import com.jeonghwahong.board.entity.Post;
import com.jeonghwahong.board.entity.role.MemberRole;
import com.jeonghwahong.board.repository.MemberRepository;
import com.jeonghwahong.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final BoardService boardService;
    private final MemberRepository memberRepository;

    public PostPageResponseDto findAllPosts(int pageNumber){
        PageRequest pageRequest = PageRequest.of(pageNumber, 6, Sort.by(Sort.Direction.DESC,"id"));
        Page<Post> page = postRepository.findAll(pageRequest);
        Page<PostDto> postDtoPage = page.map(post -> new PostDto(post.getId(),
                post.getBoard().getName(),
                post.getMember().getName(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedDate(),
                post.getLastModifiedDate(),
                post.getView(),
                post.getLikes().size(),
                post.getComments().size()
        ));
        return new PostPageResponseDto(null, postDtoPage);
    }

    /**
     * 나중에 exception 커스텀 하던 하자.(test 다시 돌리자)
     * n + 1발생 해결
     */
    public PostDto findPostById(Long id){
        log.info("PostService.findPostById 시작");
        Post post = postRepository.findPostById(id)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 게시물입니다."));

        post.increaseView();
        postRepository.save(post);
        return new PostDto(post.getId(),
                post.getBoard().getName(),
                post.getMember().getName(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedDate(),
                post.getLastModifiedDate(),
                post.getView(),
                post.getLikes().size(),
                post.getComments().size());
    }

    public PostPageResponseDto findByQuery(int pageNumber, String query){
        log.info("findByQuery");
        PageRequest pageRequest = PageRequest.of(pageNumber, 6, Sort.by(Sort.Direction.DESC, "id"));
        Page<Post> page = postRepository.findBySearch(query, pageRequest);
        Page<PostDto> postDtoPage = page.map(post ->
                new PostDto(post.getId(),
                        post.getBoard().getName(),
                        post.getMember().getName(),
                        post.getTitle(),
                        post.getContent(),
                        post.getCreatedDate(),
                        post.getLastModifiedDate(),
                        post.getView(),
                        post.getLikes().size(),
                        post.getComments().size()));
        return new PostPageResponseDto(query, postDtoPage);
    }

    /**
     * 일단.... member는 id로 대체하자 추후에 로그인 구현하면 바꾸자.
     */
    public Post save(PostAddRequestDto postDto){
        Board board = boardService.findById(postDto.getBoardDto().getId());
        Member member = Member.createMember("TemporaryMember", MemberRole.ROLE_MEMBER);
        memberRepository.save(member);
        Post post = Post.createPost(postDto.getTitle(), postDto.getContent(), board, member);
        postRepository.save(post);
        return post;
    }

}
