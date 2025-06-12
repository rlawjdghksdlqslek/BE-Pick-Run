package com.skill.bookmarkservice.domain.application;

import com.skill.bookmarkservice.domain.entity.Bookmark;
import com.skill.bookmarkservice.domain.infrastructure.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkRepository bookmarkRepository;


    @Transactional
    @Override
    public void toggleBookmark(String memberUuid, String postUuid) {
        log.info("토글 요청: memberUuid={}, postUuid={}", memberUuid, postUuid);
        Optional<Bookmark> existing = bookmarkRepository.findByMemberUuidAndPostUuid(memberUuid, postUuid);

        if (existing.isPresent()) {
            Bookmark bookmark = existing.get();
            bookmark.toggle();
            bookmarkRepository.save(bookmark);
            log.info(
                    "기존 북마크 상태 토글: bookmarkUuid={}, isBookmarked={}", bookmark.getBookmarkUuid(),
                    bookmark.isBookmarked()
            );
        } else {
            Bookmark newBookmark = Bookmark.builder()
                    .bookmarkUuid(UUID.randomUUID().toString())
                    .memberUuid(memberUuid)
                    .postUuid(postUuid)
                    .isBookmarked(true)
                    .build();
            bookmarkRepository.save(newBookmark);
            log.info("새 북마크 생성: bookmarkUuid={}", newBookmark.getBookmarkUuid());
        }
    }
}

