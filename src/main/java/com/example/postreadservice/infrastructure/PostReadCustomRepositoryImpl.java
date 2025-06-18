package com.example.postreadservice.infrastructure;

import com.example.postreadservice.entity.PostReadModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostReadCustomRepositoryImpl implements PostReadCustomRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public Page<PostReadModel> findByCategoryListId(Long categoryListId, Pageable pageable) {
        Query query = new Query(Criteria.where("categoryListId").is(categoryListId))
                .with(pageable);

        long total = mongoTemplate.count(query, PostReadModel.class);
        List<PostReadModel> posts = mongoTemplate.find(query, PostReadModel.class);
        return new PageImpl<>(posts, pageable, total);
    }

    @Override
    public Page<PostReadModel> findByDynamicCategory(
            Long mainCategoryId,
            Long subCategoryId,
            Pageable pageable
    ) {
        Criteria criteria = new Criteria();

        if (mainCategoryId != null) {
            criteria = criteria.and("mainCategoryId").is(mainCategoryId);
        }
        if (subCategoryId != null) {
            criteria = criteria.and("subCategoryId").is(subCategoryId);
        }

        Query pageQuery = new Query(criteria).with(pageable); // 페이징 적용
        Query countQuery = new Query(criteria); // 전체 개수 조회용 쿼리

        long total = mongoTemplate.count(countQuery, PostReadModel.class);
        List<PostReadModel> posts = mongoTemplate.find(pageQuery, PostReadModel.class);

        return new PageImpl<>(posts, pageable, total);
    }

}
