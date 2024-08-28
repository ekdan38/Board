package com.jeonghwahong.board.entity;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardTest {

    @Autowired
    EntityManager em;

    @Test
    public void createBoardTest(){
        //given
        Manager manager = Manager.createManager("manager");
        em.persist(manager);

        Board board = Board.createBoard("Board Name", "Board Description", manager);
        em.persist(board);

        //when
        em.flush();
        em.clear();

        //then
        Board foundBoard = em.find(Board.class, board.getId());
        assertThat(foundBoard.getName()).isEqualTo("Board Name");
        assertThat(foundBoard.getDescription()).isEqualTo("Board Description");
        assertThat(foundBoard.getManager().getId()).isEqualTo(manager.getId());
    }

}