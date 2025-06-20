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
                    게시글 UUID 를 기반으로 단일 게시글의 상세 정보를 조회합니다.
                    
                    [요청 경로]
                    - /api/v1/post-read/{postUuid}
                    
                    [요청 파라미터]
                    - path variable: postUuid (String) 게시글 고유 식별자
                    
                    [응답 필드]
                    - title, contents, images, likeCount, viewCount 등 게시글 상세 데이터
                    
                    [처리 로직]
                       - postUuid로 MongoDB에서 게시글 조회
                       - 게시글이 존재하지 않으면 예외 발생
                       - 로그인 사용자인 경우:
                         - Redis를 통해 중복 조회 여부(TTL 10분)를 확인
                         - 중복이 아니면 Redis에 TTL 키 저장 후 Kafka로 조회 이벤트 발행
                    
                    [비동기 처리 흐름]
                       - Kafka Consumer에서 Redis로 postUuid별 조회수 누적
                       - 별도 배치 스케줄러가 1분 주기로 Redis → MongoDB(viewCount) 반영
                    
                    [예외 상황]
                    - NO_EXIST_POST: 해당 UUID 의 게시글이 존재하지 않음
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
