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
}
