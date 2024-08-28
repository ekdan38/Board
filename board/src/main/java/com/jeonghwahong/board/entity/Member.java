package com.jeonghwahong.board.entity;

import com.jeonghwahong.board.entity.baseEntity.BaseEntity;
import com.jeonghwahong.board.entity.role.MemberRole;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    private Member(String name, MemberRole role){
        this.name = name;
        this.role = role;
    }

    public static Member createMember(String name, MemberRole role){
        return new Member(name,role);
    }
}
