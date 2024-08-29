package com.jeonghwahong.board.entity;

import com.jeonghwahong.board.entity.baseEntity.BaseEntity;
import com.jeonghwahong.board.entity.targetType.TargetType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "likes")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "like_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private TargetType targetType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    private Like(TargetType targetType, Member member, Post post){
        this.targetType = targetType;
        this.member = member;
        setPost(post);
        this.comment = null;
    }
    private Like(TargetType targetType, Member member, Comment comment){
        this.targetType = targetType;
        this.member = member;
        this.post = null;
        this.comment = comment;
    }

    public static Like createPostLike(TargetType targetType, Member member, Post post){
        return new Like(targetType, member, post);
    }

    public static Like createCommentLike(TargetType targetType, Member member, Comment comment){
        return new Like(targetType,member, comment);
    }

    /**
     * Post 연관관계 편의 메서드
     */
    private void setPost(Post post){
        this.post = post;
        post.getLikes().add(this);
    }
}
