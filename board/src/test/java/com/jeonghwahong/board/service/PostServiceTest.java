package com.jeonghwahong.board.service;

import com.jeonghwahong.board.dto.post.PostDto;
import com.jeonghwahong.board.dto.post.PostPageResponseDto;
import com.jeonghwahong.board.entity.Board;
import com.jeonghwahong.board.entity.Manager;
import com.jeonghwahong.board.entity.Member;
import com.jeonghwahong.board.entity.Post;
import com.jeonghwahong.board.entity.role.MemberRole;
import com.jeonghwahong.board.repository.PostRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("test")
class PostServiceTest {

    @Autowired
    PostRepository postRepository;
    @Autowired
    PostService postService;
    @Autowired
    EntityManager em;

    @Test
    @Transactional
    @DisplayName("전체 게시글 가져오는 페이징")
    public void findAllPosts(){
        //given
        initData();

        //when
        PostPageResponseDto page = postService.findAllPosts(0);
        List<PostDto> content = page.getPosts().getContent();

        //then
        assertThat(content.size()).isEqualTo(6);
        assertThat(page.getPosts().getTotalPages()).isEqualTo(9);
        assertThat(page.getPosts().getNumber()).isEqualTo(0);
        assertThat(page.getPosts().getTotalElements()).isEqualTo(50);
        assertThat(page.getPosts().isFirst()).isTrue();
        assertThat(page.getPosts().isLast()).isFalse();
    }

    @Test
    @Transactional
    public void findPostByIdSuccess(){
        //given
        Manager manager = Manager.createManager("manager");
        em.persist(manager);
        Board koreaFootball = Board.createBoard("국내 축구", "국내 축구 게시판", manager);
        em.persist(koreaFootball);
        Member member = Member.createMember("TestMember", MemberRole.ROLE_MEMBER);
        em.persist(member);
        Post post = Post.createPost("Post", "Post", koreaFootball, member);
        em.persist(post);
        em.flush();
        em.clear();

        //when
        PostDto foundPost = postService.findPostById(1L);

        //then
        assertThat(foundPost.getTitle()).isEqualTo("Post");
        assertThat(foundPost.getContent()).isEqualTo("Post");
        assertThat(foundPost.getLike()).isEqualTo(0);
        assertThat(foundPost.getComment()).isEqualTo(0);
        assertThat(foundPost.getCreatedBy()).isEqualTo(member.getName());
        assertThat(foundPost.getView()).isEqualTo(1);

    }

    @Test
    @Transactional
    public void findPostByIdFail(){
        //given
        Manager manager = Manager.createManager("manager");
        em.persist(manager);
        Board koreaFootball = Board.createBoard("국내 축구", "국내 축구 게시판", manager);
        em.persist(koreaFootball);
        Member member = Member.createMember("TestMember", MemberRole.ROLE_MEMBER);
        em.persist(member);
        Post post = Post.createPost("PostFail", "Post", koreaFootball, member);
        em.persist(post);
        em.flush();
        em.clear();

        //when

        //then
        //auto_increment 에 의해 rollback시켜도 2부터 시작...
        assertThatThrownBy(() -> postService.findPostById(3L)).isInstanceOf(NoSuchElementException.class);
    }


    void initData(){
        //Board
        Manager manager = Manager.createManager("manager1");
        em.persist(manager);
        Board koreaFootball = Board.createBoard("국내 축구", "국내 축구 게시판", manager);
        em.persist(koreaFootball);
        Board overSeasFootball = Board.createBoard("해외 축구", "해외 축구 게시판", manager);
        em.persist(overSeasFootball);
        em.flush();

        //Post
        Member member = Member.createMember("TestMember", MemberRole.ROLE_MEMBER);
        em.persist(member);

        for(int i = 0; i < 50; i++){
            if((i % 2) == 0){
                Post post = Post.createPost("국내 축구" + i, "content", koreaFootball, member);
                em.persist(post);
            }
            else {
                Post post = Post.createPost("해외 축구" + i, "content", overSeasFootball, member);
                em.persist(post);
            }
        }

    }
}