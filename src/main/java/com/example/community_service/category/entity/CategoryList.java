package com.example.community_service.category.entity;

import com.example.community_service.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "category_list")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryList extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_list_id")
    private Long id;

    @Column(name = "main_category_id", nullable = false)
    private Long mainCategoryId;

    @Column(name = "main_category_name", nullable = false)
    private String mainCategoryName;

    @Column(name = "sub_category_id", nullable = false)
    private Long subCategoryId;

    @Column(name = "sub_category_name", nullable = false)
    private String subCategoryName;

    @Builder
    public CategoryList(
            Long id,
            Long mainCategoryId,
            String mainCategoryName,
            Long subCategoryId,
            String subCategoryName
    ) {
        this.id = id;
        this.mainCategoryId = mainCategoryId;
        this.mainCategoryName = mainCategoryName;
        this.subCategoryId = subCategoryId;
        this.subCategoryName = subCategoryName;
    }
}
