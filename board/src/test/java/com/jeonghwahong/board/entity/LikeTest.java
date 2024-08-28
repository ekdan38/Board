package com.jeonghwahong.board.entity;

import com.jeonghwahong.board.entity.role.MemberRole;
import com.jeonghwahong.board.entity.targetType.TargetType;
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
class LikeTest {

    @Autowired
    EntityManager em;

    @Test
    public void createPostLike(){
        //given
        Member member = Member.createMember("member", MemberRole.ROLE_MEMBER);
        em.persist(member);

        Manager manager = Manager.createManager("manager");
        em.persist(manager);

        Board board = Board.createBoard("Board Name", "Board Description", manager);
        em.persist(board);

        Post post = Post.createPost("Post Title", "Post Description", board, member);
        em.persist(post);

        Like postLike = Like.createPostLike(TargetType.POST, member, post);

        //when
        em.persist(postLike);
        em.flush();
        em.clear();

        //then
        Like foundLike = em.find(Like.class, postLike.getId());
        assertThat(foundLike.getTargetType()).isEqualTo(TargetType.POST);
        assertThat(foundLike.getMember().getId()).isEqualTo(member.getId());
        assertThat(foundLike.getPost().getId()).isEqualTo(post.getId());
        assertThat(foundLike.getComment()).isEqualTo(null);
    }

    @Test
    public void createCommentLike(){
        //given
        Member member = Member.createMember("member", MemberRole.ROLE_MEMBER);
        em.persist(member);

        Manager manager = Manager.createManager("manager");
        em.persist(manager);

        Board board = Board.createBoard("Board Name", "Board Description", manager);
        em.persist(board);

        Post post = Post.createPost("Post Title", "Post Description", board, member);
        em.persist(post);

        Comment comment = Comment.createComment("Comment", post);
        em.persist(comment);

        Like commentLike = Like.createCommentLike(TargetType.COMMENT, member, comment);

        //when
        em.persist(commentLike);
        em.flush();
        em.clear();

        //then
        Like foundLike = em.find(Like.class, commentLike.getId());
        assertThat(foundLike.getTargetType()).isEqualTo(TargetType.COMMENT);
        assertThat(foundLike.getMember().getId()).isEqualTo(member.getId());
        assertThat(foundLike.getComment().getId()).isEqualTo(comment.getId());
        assertThat(foundLike.getPost()).isEqualTo(null);
    }


}