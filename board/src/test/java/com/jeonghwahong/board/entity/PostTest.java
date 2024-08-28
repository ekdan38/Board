package com.jeonghwahong.board.entity;

import com.jeonghwahong.board.entity.role.MemberRole;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostTest {

    @Autowired
    EntityManager em;

    @Test
    public void createPost(){
        //given
        Member member = Member.createMember("member", MemberRole.ROLE_MEMBER);
        em.persist(member);

        Manager manager = Manager.createManager("manager");
        em.persist(manager);

        Board board = Board.createBoard("Board Name", "Board Description", manager);
        em.persist(board);

        Post post = Post.createPost("Post Title", "Post Description", board, member);

        //when
        em.persist(post);
        em.flush();
        em.clear();

        //then
        assertThat(post.getTitle()).isEqualTo("Post Title");
        assertThat(post.getContent()).isEqualTo("Post Description");
        assertThat(post.getView()).isEqualTo(0);
        assertThat(post.getBoard().getId()).isEqualTo(board.getId());
        assertThat(post.getMember().getId()).isEqualTo(member.getId());
    }

    @Test
    public void increaseView(){
        //given
        Member member = Member.createMember("member", MemberRole.ROLE_MEMBER);
        em.persist(member);

        Manager manager = Manager.createManager("manager");
        em.persist(manager);

        Board board = Board.createBoard("Board Name", "Board Description", manager);
        em.persist(board);

        Post post = Post.createPost("Post Title", "Post Description", board, member);

        //when
        em.persist(post);
        em.flush();
        em.clear();

        Integer viewCount = post.increaseView();

        //then
        assertThat(viewCount).isEqualTo(1);


    }


}