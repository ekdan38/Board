package com.jeonghwahong.board.entity;

import com.jeonghwahong.board.entity.role.MemberRole;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CommentTest {

    @Autowired
    EntityManager em;

    @Test
    public void createComment(){
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

        //when
        em.persist(comment);
        em.flush();
        em.clear();

        //then
        Comment foundComment = em.find(Comment.class, comment.getId());
        Assertions.assertThat(foundComment.getContent()).isEqualTo("Comment");
        Assertions.assertThat(foundComment.getPost().getId()).isEqualTo(post.getId());
    }

}