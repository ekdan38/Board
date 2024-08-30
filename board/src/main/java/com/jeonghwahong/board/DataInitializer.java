package com.jeonghwahong.board;

import com.jeonghwahong.board.entity.Board;
import com.jeonghwahong.board.entity.Manager;
import com.jeonghwahong.board.entity.Member;
import com.jeonghwahong.board.entity.Post;
import com.jeonghwahong.board.entity.role.MemberRole;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Profile("local")
public class DataInitializer {
    
    @Autowired
    EntityManager em;

    /**
     * Board, Post 설정
     */
    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void initData(){
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
                Post post = Post.createPost("국내 축구" + i, content(), koreaFootball, member);
                em.persist(post);
            }
            else {
                Post post = Post.createPost("해외 축구" + i, content(), overSeasFootball, member);
                em.persist(post);
            }
        }

    }
    private String content(){
        return "Test Content";
    }

}
