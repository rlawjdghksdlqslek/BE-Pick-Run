package com.skill.bookmarkservice.domain.presentation;


import com.skill.bookmarkservice.common.entity.BaseResponseEntity;
import com.skill.bookmarkservice.common.response.BaseResponseStatus;
import com.skill.bookmarkservice.domain.application.BookmarkService;
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
    public BaseResponseEntity<Void> toggleBookmark(
            @PathVariable String postUuid,
            @RequestHeader("X-Member-UUID") String memberUuid
    ) {
        bookmarkService.toggleBookmark(memberUuid, postUuid);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }
}
