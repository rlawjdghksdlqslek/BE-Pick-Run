package com.example.post_service.category.entity;

import com.example.post_service.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sub_category")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_category_id")
    private Long id;

    @Column(name = "sub_category_name", nullable = false)
    private String name;

    @Column(name = "color", nullable = false)
    private String color;

    @Builder
    public SubCategory(Long id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public void updateName(String name) {
        this.name = name;
    }
}

