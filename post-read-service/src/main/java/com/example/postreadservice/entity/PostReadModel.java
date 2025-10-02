package com.example.postreadservice.entity;

import com.example.postreadservice.kafka.event.PostUpdatedEvent;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.index.TextIndexed;

@Document(collection = "post_read")
@Getter
@NoArgsConstructor
@CompoundIndex(name = "category_sort_created_at", def = "{'deletedStatus': 1, 'mainCategoryId': 1, 'subCategoryId': 1, 'createdAt': -1}")
@CompoundIndex(name = "category_sort_view_count", def = "{'deletedStatus': 1, 'mainCategoryId': 1, 'subCategoryId': 1, 'viewCount': -1}")
@CompoundIndex(name = "category_sort_like_count", def = "{'deletedStatus': 1, 'mainCategoryId': 1, 'subCategoryId': 1, 'likeCount': -1}")
public class PostReadModel {

    @Id
    private String id;
    @Indexed(unique = true)
    private String postUuid;
    private String memberUuid;
    private Long mainCategoryId;
    private Long subCategoryId;
    @TextIndexed
    private String title;
    private String contents;

    private boolean blindStatus;
    private boolean deletedStatus;

    private long viewCount;
    private long likeCount;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    @Builder
    public PostReadModel(
            String postUuid,
            String memberUuid,
            Long mainCategoryId,
            Long subCategoryId,
            String title,
            String contents,
            boolean blindStatus,
            boolean deletedStatus,
            long viewCount,
            long likeCount,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            LocalDateTime deletedAt
    ) {
        this.postUuid = postUuid;
        this.memberUuid = memberUuid;
        this.mainCategoryId = mainCategoryId;
        this.subCategoryId = subCategoryId;
        this.title = title;
        this.contents = contents;
        this.blindStatus = blindStatus;
        this.deletedStatus = deletedStatus;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public void updateFromEvent(PostUpdatedEvent event) {
        this.memberUuid = event.getMemberUuid();
        this.mainCategoryId = event.getMainCategoryId();
        this.subCategoryId = event.getSubCategoryId();
        this.title = event.getTitle();
        this.contents = event.getContents();
        this.blindStatus = event.isBlindStatus();
        this.deletedStatus = event.isDeletedStatus();
        this.updatedAt = event.getUpdatedAt();
    }

    public void softDeleteFromEvent() {
        this.deletedStatus = true;
        this.deletedAt = LocalDateTime.now();
    }

    public void increaseViewCount() {
        this.viewCount++;
    }
}
