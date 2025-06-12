package com.skill.bookmarkservice.domain.application;

public interface BookmarkService {

    void toggleBookmark(String memberUuid, String postUuid);
}
