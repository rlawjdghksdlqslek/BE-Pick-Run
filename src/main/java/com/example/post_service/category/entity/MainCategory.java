package com.example.post_service.category.entity;

import com.example.post_service.common.entity.BaseEntity;
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
    private Long id;

    @Column(name = "main_category_name", nullable = false)
    private String name;

    @Column(name = "icon_Url", nullable = false)
    private String iconUrl;

    @Column(name = "alt", nullable = false)
    private String alt;

    @Builder
    public MainCategory(
            Long id,
            String name,
            String iconUrl,
            String alt
    ) {
        this.id = id;
        this.name = name;
        this.iconUrl = iconUrl;
        this.alt = alt;
    }

    public void updateName(String name) {
        this.name = name;
    }
}
