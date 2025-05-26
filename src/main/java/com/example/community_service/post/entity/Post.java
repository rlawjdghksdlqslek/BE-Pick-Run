package com.example.community_service.post.entity;

import com.example.community_service.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;


@Document(collection = "posts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    private String Id;

    private String postUuid;
    private String memberUuid;
    private Long categoryListId;
    private String title;
    private String contents;
    private List<Image> images;

    private boolean blindStatus;
    private boolean deletedStatus;
    private LocalDateTime deletedAt;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public Post(
            String postUuid,
            String memberUuid,
            Long categoryListId,
            String title,
            String contents,
            List<Image> images,
            boolean blindStatus,
            boolean deletedStatus,
            LocalDateTime deletedAt,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.postUuid = postUuid;
        this.memberUuid = memberUuid;
        this.categoryListId = categoryListId;
        this.title = title;
        this.contents = contents;
        this.images = images;
        this.blindStatus = blindStatus;
        this.deletedStatus = deletedStatus;
        this.deletedAt = deletedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}