package com.skill.bookmarkservice.domain.dto.out;

import com.skill.bookmarkservice.domain.vo.out.BookmarkResVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookmarkResDto {

    private String postUuid;
    private boolean bookmarked;

    @Builder
    public BookmarkResDto(String postUuid, boolean bookmarked) {
        this.postUuid = postUuid;
        this.bookmarked = bookmarked;
    }

    public static BookmarkResVo toVo(BookmarkResDto bookmarkResDto) {
        return BookmarkResVo.builder()
                .postUuid(bookmarkResDto.getPostUuid())
                .bookmarked(bookmarkResDto.isBookmarked())
                .build();
    }
}
