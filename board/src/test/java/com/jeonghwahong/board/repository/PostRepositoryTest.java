package com.jeonghwahong.board.repository;

import com.jeonghwahong.board.entity.Board;
import com.jeonghwahong.board.entity.Manager;
import com.jeonghwahong.board.entity.Member;
import com.jeonghwahong.board.entity.Post;
import com.jeonghwahong.board.entity.role.MemberRole;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;
    @Autowired
    EntityManager em;

    @Test
    @Transactional
    public void findPostByIdSuccess(){
        //given
        Manager manager = Manager.createManager("manager");
        em.persist(manager);
        Board board = Board.createBoard("국내 축구", "국내 축구 게시판", manager);
        em.persist(board);
        Member member = Member.createMember("member", MemberRole.ROLE_MEMBER);
        em.persist(member);
        Post post = Post.createPost("Post", "Post", board, member);
        em.persist(post);
        em.flush();
        em.clear();

        //when
        Long id = post.getId();
        Post foundPost = postRepository.findPostById(id)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 게시물입니다."));
        //then
        assertThat(foundPost.getTitle()).isEqualTo("Post");
        assertThat(foundPost.getContent()).isEqualTo("Post");
        assertThat(foundPost.getView()).isEqualTo(0);
        assertThat(foundPost.getBoard().getId()).isEqualTo(board.getId());
        assertThat(foundPost.getComments().size()).isEqualTo(0);
        assertThat(foundPost.getLikes().size()).isEqualTo(0);

    }

    @Test
    @Transactional
    public void findPostByIdFail(){
        //given
        Manager manager = Manager.createManager("manager");
        em.persist(manager);
        Board board = Board.createBoard("국내 축구", "국내 축구 게시판", manager);
        em.persist(board);
        Member member = Member.createMember("member", MemberRole.ROLE_MEMBER);
        em.persist(member);
        Post post = Post.createPost("Post", "Post", board, member);
        em.persist(post);
        em.flush();
        em.clear();

        //when
        Long id = post.getId() + 2;

        //then
        assertThatThrownBy(()->postRepository.findPostById(id).orElseThrow(()->new NoSuchElementException("존재하지 않는 게시물입니다.")))
                .isInstanceOf(NoSuchElementException.class).hasMessage("존재하지 않는 게시물입니다.");
    }

    @Test
    @Transactional
    public void findBySearch(){
        //given
        Manager manager = Manager.createManager("manager");
        em.persist(manager);
        Board board = Board.createBoard("국내 축구", "국내 축구 게시판", manager);
        em.persist(board);
        Member member = Member.createMember("member", MemberRole.ROLE_MEMBER);
        em.persist(member);
        Post post1 = Post.createPost("Post", "Post", board, member);
        Post post2 = Post.createPost("Post2", "Post", board, member);
        Post post3 = Post.createPost("Post3", "Post", board, member);
        Post post4 = Post.createPost("Post4", "Post", board, member);
        Post post5 = Post.createPost("title", "Post", board, member);
        em.persist(post1);
        em.persist(post2);
        em.persist(post3);
        em.persist(post4);
        em.persist(post5);
        em.flush();
        em.clear();

        //when
        PageRequest pageRequest = PageRequest.of(0, 6, Sort.by(Sort.Direction.DESC,"id"));
        Page<Post> post = postRepository.findBySearch("Post", pageRequest);

        //then
        System.out.println("post = " + post);
    }

}