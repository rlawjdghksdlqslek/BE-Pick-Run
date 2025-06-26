package com.skill.bookmarkservice.domain.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookmarkResVo {
    private String postUuid;
    private boolean bookmarked;

    @Builder
    public BookmarkResVo(String postUuid, boolean bookmarked) {
        this.postUuid = postUuid;
        this.bookmarked = bookmarked;
    }
}
