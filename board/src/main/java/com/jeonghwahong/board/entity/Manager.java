package com.jeonghwahong.board.entity;

import com.jeonghwahong.board.entity.baseEntity.BaseEntity;
import com.jeonghwahong.board.entity.role.MemberRole;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Manager extends BaseEntity {

    @Getter
    @Id @GeneratedValue
    @Column(name = "manager_id")
    private Long id;

    @Getter
    private String name;

    @Getter
    @Enumerated(EnumType.STRING)
    private MemberRole role;

    private Manager(String name, MemberRole role) {
        this.name = name;
        this.role = role;
    }

    public static Manager createManager(String name){
        return new Manager(name, MemberRole.ROLE_MANAGER);
    }
}
