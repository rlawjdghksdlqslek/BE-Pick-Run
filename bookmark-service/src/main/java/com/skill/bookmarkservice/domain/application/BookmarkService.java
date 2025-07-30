package com.skill.bookmarkservice.domain.application;

import com.skill.bookmarkservice.domain.dto.in.BookmarkReqDto;
import com.skill.bookmarkservice.domain.dto.out.BookmarkListPageResDto;
import com.skill.bookmarkservice.domain.dto.out.BookmarkResDto;

public interface BookmarkService {

    void addBookmark(String memberUuid, String postUuid);

    void removeBookmark(String memberUuid, String postUuid);

    BookmarkResDto isBookmarked(BookmarkReqDto bookmarkReqDto);

    BookmarkListPageResDto getBookmarkList(String memberUuid, int page);
}
