package com.jeonghwahong.board.entity;

import com.jeonghwahong.board.entity.role.MemberRole;
import com.jeonghwahong.board.repository.ManagerRepository;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
class ManagerTest {

    @Autowired
    EntityManager em;

    @Test
    public void createManager(){
        //given
        Manager manager = Manager.createManager("Manager");

        //when
        em.persist(manager);
        em.flush();
        em.clear();

        //then
        Manager foundManager = em.find(Manager.class, manager.getId());
        assertThat(foundManager.getId()).isEqualTo(1);
        assertThat(foundManager.getName()).isEqualTo("Manager");
        assertThat(foundManager.getRole()).isEqualTo(MemberRole.ROLE_MANAGER);

    }

}