package com.skill.bookmarkservice.domain.application;

import com.skill.bookmarkservice.domain.dto.out.BookmarkListPageResDto;

public interface BookmarkService {

    void addBookmark(String memberUuid, String postUuid);

    void removeBookmark(String memberUuid, String postUuid);

    boolean isBookmarked(String memberUuid, String postUuid);

    BookmarkListPageResDto getBookmarkList(String memberUuid, int page);
}
