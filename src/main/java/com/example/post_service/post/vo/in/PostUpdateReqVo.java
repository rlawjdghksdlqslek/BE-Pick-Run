package com.example.post_service.post.vo.in;

import com.example.post_service.post.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostUpdateReqVo {
    private String title;
    private String contents;
    private Long categoryListId;
    private List<Image> images;
}