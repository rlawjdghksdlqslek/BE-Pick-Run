package com.skill.bookmarkservice.domain.application;

import com.skill.bookmarkservice.client.post.PostServiceClient;
import com.skill.bookmarkservice.client.post.dto.out.ExistsPostResDto;
import com.skill.bookmarkservice.common.entity.BaseResponseEntity;
import com.skill.bookmarkservice.common.exception.BaseException;
import com.skill.bookmarkservice.common.response.BaseResponseStatus;
import com.skill.bookmarkservice.domain.dto.in.BookmarkReqDto;
import com.skill.bookmarkservice.domain.dto.out.BookmarkListPageResDto;
import com.skill.bookmarkservice.domain.dto.out.BookmarkResDto;
import com.skill.bookmarkservice.domain.entity.Bookmark;
import com.skill.bookmarkservice.domain.infrastructure.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final PostServiceClient postServiceClient;

    private static final int DEFAULT_PAGE_SIZE = 10;

    @Transactional
    @Override
    public void addBookmark(String memberUuid, String postUuid) {
        validatePostExists(postUuid);
        if (bookmarkRepository.existsByMemberUuidAndPostUuid(memberUuid, postUuid)) {
            log.warn("북마크가 이미 존재합니다: memberUuid={}, postUuid={}", memberUuid, postUuid);
            throw new BaseException(BaseResponseStatus.ALREADY_EXISTS_BOOKMARK);
        }
        Bookmark bookmark = Bookmark.builder()
                .bookmarkUuid(UUID.randomUUID().toString())
                .memberUuid(memberUuid)
                .postUuid(postUuid)
                .isBookmarked(true)
                .build();
        bookmarkRepository.save(bookmark);
        log.info("북마크 추가 완료: memberUuid={}, postUuid={}", memberUuid, postUuid);
    }

    @Transactional
    @Override
    public void removeBookmark(String memberUuid, String postUuid) {
        Optional<Bookmark> bookmarkOpt = bookmarkRepository.findByMemberUuidAndPostUuid(memberUuid, postUuid);

        if (bookmarkOpt.isEmpty()) {
            log.warn("북마크가 존재하지 않음: memberUuid={}, postUuid={}", memberUuid, postUuid);
            throw new BaseException(BaseResponseStatus.BOOKMARK_NOT_FOUND);
        }
        Bookmark bookmark = bookmarkOpt.get();

        if (!bookmark.getMemberUuid().equals(memberUuid)) {
            log.warn("북마크 소유자 불일치: 요청자={}, 실제={}", memberUuid, bookmark.getMemberUuid());
            throw new BaseException(BaseResponseStatus.INVALID_USER_ROLE);
        }

        bookmarkRepository.delete(bookmark);
        log.info("북마크 삭제 완료: memberUuid={}, postUuid={}", memberUuid, postUuid);
    }

    @Override
    public BookmarkResDto isBookmarked(BookmarkReqDto bookmarkReqDto) {
        Optional<Bookmark> bookmark = bookmarkRepository.findByMemberUuidAndPostUuid(
                bookmarkReqDto.getMemberUuid(), bookmarkReqDto.getPostUuid());

        return BookmarkResDto.builder()
                .postUuid(bookmarkReqDto.getPostUuid())
                .bookmarked(bookmark.isPresent())
                .build();
    }

    @Override
    public BookmarkListPageResDto getBookmarkList(String memberUuid, int page) {
        log.info("북마크 목록 요청: memberUuid={}, page={}", memberUuid, page);
        Pageable pageable = PageRequest.of(page, DEFAULT_PAGE_SIZE);
        Page<Bookmark> resultPage = bookmarkRepository.findBookmarkedPosts(memberUuid, pageable);

        List<String> postUuids = resultPage.getContent().stream()
                .map(Bookmark::getPostUuid)
                .toList();

        return new BookmarkListPageResDto(
                postUuids, page, resultPage.getSize(), resultPage.hasNext(), resultPage.getTotalPages(),
                resultPage.getTotalElements()
        );
    }

    private void validatePostExists(String postUuid) {
        BaseResponseEntity<ExistsPostResDto> response = postServiceClient.existsPost(postUuid);
        if (!response.result().isExistsPost()) {
            throw new BaseException(BaseResponseStatus.POST_NOT_FOUND);
        }
    }
}

