package com.skill.bookmarkservice.domain.entity;

import com.skill.bookmarkservice.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bookmark")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bookmark extends BaseEntity {

    @Id
    private String bookmarkUuid;

    @Column(name = "member_uuid", nullable = false)
    private String memberUuid;

    @Column(name = "post_uuid", nullable = false)
    private String postUuid;

    @Column(name = "is_bookmarked", nullable = false)
    private boolean isBookmarked;

    @Builder
    public Bookmark(String bookmarkUuid, String memberUuid, String postUuid, boolean isBookmarked) {
        this.bookmarkUuid = bookmarkUuid;
        this.memberUuid = memberUuid;
        this.postUuid = postUuid;
        this.isBookmarked = isBookmarked;
    }

    public void toggle() {
        this.isBookmarked = !this.isBookmarked;
    }
}
