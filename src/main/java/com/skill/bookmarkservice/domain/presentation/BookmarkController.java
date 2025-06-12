package com.skill.bookmarkservice.domain.presentation;


import com.skill.bookmarkservice.common.entity.BaseResponseEntity;
import com.skill.bookmarkservice.common.response.BaseResponseStatus;
import com.skill.bookmarkservice.domain.application.BookmarkService;
import com.skill.bookmarkservice.domain.dto.out.BookmarkListPageResDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bookmark")
@Tag(name = "bookmark")
@Slf4j
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @PostMapping("/{postUuid}")
    public BaseResponseEntity<Void> addBookmark(
            @PathVariable String postUuid,
            @RequestHeader("X-Member-UUID") String memberUuid
    ) {
        bookmarkService.addBookmark(memberUuid, postUuid);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    @DeleteMapping("/{postUuid}")
    public BaseResponseEntity<Void> removeBookmark(
            @PathVariable String postUuid,
            @RequestHeader("X-Member-UUID") String memberUuid
    ) {
        bookmarkService.removeBookmark(memberUuid, postUuid);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }


    @GetMapping("/{postUuid}")
    public BaseResponseEntity<Boolean> isBookmarked(
            @PathVariable String postUuid,
            @RequestHeader("X-Member-UUID") String memberUuid
    ) {
        return new BaseResponseEntity<>(bookmarkService.isBookmarked(memberUuid, postUuid));
    }

    @GetMapping("/list")
    public BaseResponseEntity<BookmarkListPageResDto> getBookmarks(
            @RequestHeader("X-Member-UUID") String memberUuid,
            @RequestParam(value = "page", defaultValue = "0") int page
    ) {
        return new BaseResponseEntity<>(bookmarkService.getBookmarkList(memberUuid, page));
    }
}