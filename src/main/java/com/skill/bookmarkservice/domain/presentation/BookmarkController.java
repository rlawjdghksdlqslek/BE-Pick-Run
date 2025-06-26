package com.skill.bookmarkservice.domain.presentation;


import com.skill.bookmarkservice.common.entity.BaseResponseEntity;
import com.skill.bookmarkservice.common.response.BaseResponseStatus;
import com.skill.bookmarkservice.domain.application.BookmarkService;
import com.skill.bookmarkservice.domain.dto.in.BookmarkReqDto;
import com.skill.bookmarkservice.domain.dto.out.BookmarkListPageResDto;
import com.skill.bookmarkservice.domain.dto.out.BookmarkResDto;
import com.skill.bookmarkservice.domain.vo.out.BookmarkResVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bookmark")
@Tag(name = "bookmark", description = "북마크 API")
@Slf4j
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @Operation(
            summary = "게시글 북마크 추가",
            description = """
            회원이 게시글을 북마크에 추가합니다.

            [요청 경로]
            - POST /api/v1/bookmark/{postUuid}

            [요청 헤더]
            - X-Member-UUID: (String) 회원 고유 식별자

            [요청 파라미터]
            - path variable: postUuid (String) 북마크할 게시글 UUID

            [처리 로직]
            - 회원 UUID 및 게시글 UUID로 북마크 추가

            [예외 상황]
            - NO_EXIST_MEMBER: 회원이 존재하지 않음
            - NO_EXIST_POST: 게시글이 존재하지 않음
        """
    )
    @PostMapping("/{postUuid}")
    public BaseResponseEntity<Void> addBookmark(
            @PathVariable String postUuid,
            @RequestHeader("X-Member-UUID") String memberUuid
    ) {
        bookmarkService.addBookmark(memberUuid, postUuid);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    @Operation(
            summary = "게시글 북마크 제거",
            description = """
            회원이 게시글에 설정된 북마크를 제거합니다.

            [요청 경로]
            - DELETE /api/v1/bookmark/{postUuid}

            [요청 헤더]
            - X-Member-UUID: (String) 회원 고유 식별자

            [요청 파라미터]
            - path variable: postUuid (String) 제거할 게시글 UUID

            [처리 로직]
            - 북마크 존재 여부 확인 후 삭제 처리
        """
    )
    @DeleteMapping("/{postUuid}")
    public BaseResponseEntity<Void> removeBookmark(
            @PathVariable String postUuid,
            @RequestHeader("X-Member-UUID") String memberUuid
    ) {
        bookmarkService.removeBookmark(memberUuid, postUuid);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    @Operation(
            summary = "게시글 북마크 여부 확인",
            description = """
            회원이 특정 게시글을 북마크했는지 여부를 조회합니다.

            [요청 경로]
            - GET /api/v1/bookmark/{postUuid}

            [요청 헤더]
            - X-Member-UUID: (String) 회원 고유 식별자

            [요청 파라미터]
            - path variable: postUuid (String) 확인할 게시글 UUID

            [응답 필드]
            - true: 북마크됨 / false: 북마크되지 않음
        """
    )
    @GetMapping("/{postUuid}")
    public BaseResponseEntity<BookmarkResVo> isBookmarked(
            @PathVariable String postUuid,
            @RequestHeader(value = "X-Member-UUID",required = false) String memberUuid
    ) {
        BookmarkResDto bookmarked = bookmarkService.isBookmarked(BookmarkReqDto.of(memberUuid, postUuid));
        return new BaseResponseEntity<>(BookmarkResDto.toVo(bookmarked));
    }

    @Operation(
            summary = "회원 북마크 목록 조회",
            description = """
            회원이 북마크한 게시글 목록을 페이지별로 조회합니다.

            [요청 경로]
            - GET /api/v1/bookmark/list

            [요청 헤더]
            - X-Member-UUID: (String) 회원 고유 식별자

            [요청 파라미터]
            - page: (int) 페이지 번호 (기본값: 0)

            [응답 필드]
            - 게시글 목록과 페이징 정보 포함
        """
    )
    @GetMapping("/list")
    public BaseResponseEntity<BookmarkListPageResDto> getBookmarks(
            @RequestHeader("X-Member-UUID") String memberUuid,
            @RequestParam(value = "page", defaultValue = "0") int page
    ) {
        return new BaseResponseEntity<>(bookmarkService.getBookmarkList(memberUuid, page));
    }
}