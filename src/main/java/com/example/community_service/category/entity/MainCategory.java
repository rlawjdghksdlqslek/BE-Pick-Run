package com.example.community_service.category.entity;

import com.example.community_service.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "main_category")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MainCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "main_category_id")
    private Integer id;

    @Column(name = "main_category_name", nullable = false)
    private String name;

    @Builder
    public MainCategory(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
