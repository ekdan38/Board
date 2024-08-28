package com.jeonghwahong.board.entity.baseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
class BaseTimeEntityTest {

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("생성, 수정 시간 세팅 테스트")
    public void createAndModifiedTest(){
        //given
        LocalDateTime now = LocalDateTime.now();

        TestEntity testEntity = new TestEntity("test");
        em.persist(testEntity);


        //when
        TestEntity savedEntity = em.find(TestEntity.class, testEntity.getId());

        //then
        assertThat(savedEntity.getCreatedDate()).isAfter(now);
        assertThat(savedEntity.getLastModifiedDate()).isAfter(now);
    }

    @Test
    @DisplayName("수정 시간 테스트")
    public void lastModifiedTest(){
        //given
        TestEntity testEntity = new TestEntity("test");
        em.persist(testEntity);
        em.flush();
        em.clear();

        TestEntity savedEntity = em.find(TestEntity.class, testEntity.getId());
        LocalDateTime firstModifiedTime = savedEntity.getLastModifiedDate();

        //when
        savedEntity.setName("modifiedTest");
        em.persist(savedEntity);
        em.flush();
        em.clear();

        TestEntity modifiedEntity = em.find(TestEntity.class, testEntity.getId());
        LocalDateTime lastModifiedDate = modifiedEntity.getLastModifiedDate();

        //then
        assertThat(lastModifiedDate).isAfter(firstModifiedTime);
    }



    @Entity
    @Getter
    static class TestEntity extends BaseTimeEntity{
        @Id @GeneratedValue
        private Long id;

        @Setter
        private String name;

        public TestEntity() {
        }

        public TestEntity(String name) {
            this.name = name;
        }
    }




}