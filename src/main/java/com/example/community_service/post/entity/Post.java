package com.example.community_service.post.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long Id;

    @Column(name = "post_uuid", nullable = false)
    private String postUuid;

    @Column(name = "member_uuid", nullable = false)
    private String memberUuid;

    @Column(name = "category_list_id", nullable = false)
    private Long categoryListId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "contents", nullable = false, columnDefinition = "TEXT")
    private String contents;

    @Column(name = "blind_status", nullable = false)
    private Boolean blindStatus = false;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "deleted_status", nullable = false)
    private Boolean deletedStatus = false;

    @Builder
    public Post(
            String postUuid,
            String memberUuid,
            Long categoryListId,
            String title,
            String contents,
            Boolean blindStatus,
            Boolean deletedStatus) {
        this.postUuid = postUuid;
        this.memberUuid = memberUuid;
        this.categoryListId = categoryListId;
        this.title = title;
        this.contents = contents;
        this.blindStatus = blindStatus;
        this.deletedStatus = deletedStatus;
    }
}