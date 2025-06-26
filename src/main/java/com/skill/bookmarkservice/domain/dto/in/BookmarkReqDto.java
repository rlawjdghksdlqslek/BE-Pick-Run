package com.skill.bookmarkservice.domain.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookmarkReqDto {

    private String postUuid;
    private String memberUuid;

    @Builder
    public BookmarkReqDto(String postUuid, String memberUuid) {
        this.postUuid = postUuid;
        this.memberUuid = memberUuid;
    }

    public static BookmarkReqDto of(String postUuid, String memberUuid) {
        return BookmarkReqDto.builder()
                .postUuid(postUuid)
                .memberUuid(memberUuid)
                .build();
    }


}
