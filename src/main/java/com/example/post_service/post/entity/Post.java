package com.example.post_service.post.entity;

import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection = "post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    private String postUuid;

    private String memberUuid;
    private Long mainCategoryId;
    private Long subCategoryId;
    private String title;
    private String contents;

    private boolean blindStatus;
    private boolean deletedStatus;
    private LocalDateTime deletedAt;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public Post(
            String postUuid,
            String memberUuid,
            Long mainCategoryId,
            Long subCategoryId,
            String title,
            String contents,
            boolean blindStatus,
            boolean deletedStatus,
            LocalDateTime deletedAt,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.postUuid = postUuid;
        this.memberUuid = memberUuid;
        this.mainCategoryId = mainCategoryId;
        this.subCategoryId = subCategoryId;
        this.title = title;
        this.contents = contents;
        this.blindStatus = blindStatus;
        this.deletedStatus = deletedStatus;
        this.deletedAt = deletedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void update(
            String title,
            String contents,
            Long mainCategoryId,
            Long subCategoryId
    ) {
        this.title = title;
        this.contents = contents;
        this.mainCategoryId = mainCategoryId;
        this.subCategoryId = subCategoryId;
        this.updatedAt = LocalDateTime.now();
    }
}