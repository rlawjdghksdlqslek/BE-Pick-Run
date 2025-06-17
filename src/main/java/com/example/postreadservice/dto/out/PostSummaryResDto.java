package com.example.postreadservice.dto.out;

import com.example.postreadservice.entity.PostReadModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class PostSummaryResDto {

    private String postUuid;
    private String memberUuid;
    private Long mainCategoryId;
    private Long subCategoryId;
    private String title;
    private String contents;

    private boolean blindStatus;
    private boolean deletedStatus;

    @Builder
    public PostSummaryResDto(
            String postUuid,
            String memberUuid,
            Long mainCategoryId,
            Long subCategoryId,
            String title,
            String contents,
            boolean blindStatus,
            boolean deletedStatus
    ) {
        this.postUuid = postUuid;
        this.memberUuid = memberUuid;
        this.mainCategoryId = mainCategoryId;
        this.subCategoryId = subCategoryId;
        this.title = title;
        this.contents = contents;
        this.blindStatus = blindStatus;
        this.deletedStatus = deletedStatus;
    }

    public static PostSummaryResDto from(PostReadModel model) {
        return PostSummaryResDto.builder()
                .postUuid(model.getPostUuid())
                .memberUuid(model.getMemberUuid())
                .mainCategoryId(model.getMainCategoryId())
                .subCategoryId(model.getSubCategoryId())
                .title(model.getTitle())
                .contents(model.getContents())
                .blindStatus(model.isBlindStatus())
                .deletedStatus(model.isDeletedStatus())
                .build();
    }

}
