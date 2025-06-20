package com.example.postreadservice.presentation;

import com.example.postreadservice.application.PostReadService;
import com.example.postreadservice.common.entity.BaseResponseEntity;
import com.example.postreadservice.dto.out.PostListPageResponseDto;
import com.example.postreadservice.dto.out.PostReadModelResDto;
import com.example.postreadservice.entity.PostSortType;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/post-read")
@RequiredArgsConstructor
public class PostReadController {

    private final PostReadService postReadService;

    @Operation(
            summary = "단일 게시글 조회",
            description = """
        게시글 UUID를 기반으로 단일 게시글의 상세 정보를 조회합니다.

        [요청 경로]
        - GET /api/v1/post-read/{postUuid}

        [요청 헤더]
        - X-Member-UUID (String, optional): 로그인 사용자의 고유 식별자. 비로그인 사용자는 생략 가능

        [요청 파라미터]
        - path variable: postUuid (String) - 조회 대상 게시글 UUID

        [조회 처리 로직]
        1. postUuid를 기준으로 MongoDB에서 게시글을 조회
        2. 게시글이 존재하지 않으면 예외 발생 (POST_NOT_FOUND)
        3. 조회자 정보 식별 (로그인/비로그인)
           - 로그인 사용자는 memberUuid를 기반으로 Redis 키 구성
           - 비로그인 사용자는 IP + User-Agent 해시로 Redis 키 생성
        4. Redis에 해당 사용자의 조회 기록이 없으면:
           - 10분 TTL로 Redis에 조회 기록 저장
           - 게시글별 Redis 카운터 키를 증가시킴 (즉시 조회수 증가)
        5. Redis에 이미 조회 기록이 있으면:
           - 중복 조회로 간주하고 조회수 증가 생략

        [조회수 집계 처리]
        - Redis에서 postUuid별 조회수(`aggregate:post:view:{postUuid}`)를 실시간으로 누적
        - 배치 1분 간격으로 MongoDB의 viewCount에 반영

        [예외 상황]
        - POST_NOT_FOUND: 해당 UUID의 게시글이 존재하지 않을 경우 발생
        """
    )
    @GetMapping("/{postUuid}")
    public BaseResponseEntity<PostReadModelResDto> getPostRead(
            @PathVariable String postUuid,
            @RequestHeader(value = "X-Member-UUID", required = false) String memberUuid,
            HttpServletRequest request
    ) {
        return new BaseResponseEntity<>(postReadService.getPostRead(postUuid, memberUuid, request));
    }


    @Operation(
            summary = "게시글 목록 조회",
            description = """
                    게시글 목록을 정렬 조건 및 카테고리 기준으로 조회합니다.
                    
                    [요청 파라미터]
                    - mainCategoryId: (Long) 카테고리 ID (nullable)
                    - page: (int) 페이지 번호, 기본값 1
                    - size: (int) 페이지 당 게시글 수, 기본값 8
                    - postSortType: (String) 정렬 방식 (RECENT, POPULAR 등), 기본값 RECENT
                    
                    [응답 필드]
                    - content: 게시글 리스트 (PostResDto[])
                    - totalPages, totalElements 등 페이징 정보 포함
                    
                    [처리 로직]
                    - 정렬 조건 및 카테고리 조건을 기준으로 게시글 페이징 조회
                    
                    [예외 상황]
                    - INVALID_SORT_TYPE: 정의되지 않은 정렬 타입 입력 시
                    """
    )
    @GetMapping()
    public BaseResponseEntity<PostListPageResponseDto> getPostBySort(
            @RequestParam(required = false) Long mainCategoryId,
            @RequestParam(required = false) Long subCategoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size,
            @RequestParam(defaultValue = "RECENT") PostSortType postSortType
    ) {
        return new BaseResponseEntity<>(
                postReadService.getPostBySort(mainCategoryId, subCategoryId, page, size, postSortType));
    }
}
