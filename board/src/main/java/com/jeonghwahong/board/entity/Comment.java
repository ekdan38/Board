package com.jeonghwahong.board.entity;

import com.jeonghwahong.board.entity.baseEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private Comment(String content, Post post){
        this.content = content;
        this.post = post;
    }

    public static Comment createComment(String content, Post post){
        return new Comment(content, post);
    }
}
