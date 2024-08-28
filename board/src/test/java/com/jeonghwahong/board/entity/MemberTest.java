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
class MemberTest {

    @Autowired
    EntityManager em;

    @Test
    public void createMember(){
        //given
        Member member = Member.createMember("member", MemberRole.ROLE_MEMBER);

        //when
        em.persist(member);
        em.flush();
        em.clear();

        //then
        Member foundMember = em.find(Member.class, member.getId());
        assertThat(foundMember.getName()).isEqualTo("member");
        assertThat(foundMember.getRole()).isEqualTo(MemberRole.ROLE_MEMBER);

    }

}