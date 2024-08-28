package com.jeonghwahong.board.entity;

import com.jeonghwahong.board.entity.baseEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String content;

    private Integer view;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private Post(String title, String content, Board board, Member member){
        this.title = title;
        this.content = content;
        this.board = board;
        this.member = member;
        this.view = 0;
    }

    public static Post createPost(String title, String content, Board board, Member member){
        return new Post(title, content, board, member);
    }

    public Integer increaseView(){
        return ++this.view;
    }
}
