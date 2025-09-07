package com.example.blog.blog_backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.blog.blog_backend.model.BlogPost; 

@Repository // Indicates that this is a repository component in the Spring context
public interface BlogPostRepository extends MongoRepository<BlogPost, String> {
    // MongoRepository<EntityType, IDType>
    // No need to write any code here, Spring Data MongoDB provides the implementation.
    // You can add custom query methods here if needed.

}

