package com.example.readservice.infrastructure;

import com.example.readservice.entity.PostReadModel;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PostReadRepository extends MongoRepository<PostReadModel,String> {
    PostReadModel findByPostUuid(String id);
}
