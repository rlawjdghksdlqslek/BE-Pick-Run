package com.example.postreadservice.dto.out;

import com.example.postreadservice.entity.Image;
import com.example.postreadservice.entity.PostReadModel;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostReadModelResDto {

    private String postUuid;
    private String memberUuid;
    private Long mainCategoryId;
    private Long subCategoryId;

    private String title;
    private String contents;
    private List<Image> images;

    private boolean blindStatus;
    private boolean deletedStatus;

    private long viewCount;
    private long likeCount;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public PostReadModelResDto(
            String postUuid,
            String memberUuid,
            Long mainCategoryId,
            Long subCategoryId,
            String title,
            String contents,
            List<Image> images,
            boolean blindStatus,
            boolean deletedStatus,
            long viewCount,
            long likeCount,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.postUuid = postUuid;
        this.memberUuid = memberUuid;
        this.mainCategoryId = mainCategoryId;
        this.subCategoryId = subCategoryId;
        this.title = title;
        this.contents = contents;
        this.images = images;
        this.blindStatus = blindStatus;
        this.deletedStatus = deletedStatus;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static PostReadModelResDto from(PostReadModel postReadModel) {
        return PostReadModelResDto.builder()
                .postUuid(postReadModel.getPostUuid())
                .memberUuid(postReadModel.getMemberUuid())
                .mainCategoryId(postReadModel.getMainCategoryId())
                .subCategoryId(postReadModel.getSubCategoryId())
                .title(postReadModel.getTitle())
                .contents(postReadModel.getContents())
                .images(postReadModel.getImages())
                .blindStatus(postReadModel.isBlindStatus())
                .deletedStatus(postReadModel.isDeletedStatus())
                .viewCount(postReadModel.getViewCount())
                .likeCount(postReadModel.getLikeCount())
                .createdAt(postReadModel.getCreatedAt())
                .updatedAt(postReadModel.getUpdatedAt())
                .build();
    }
}
