package com.example.blog.blogapp.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data // Lombok annotation to create all the getters, setters, equals, hash, and toString methods
@Document(collection = "blog_posts") // Marks this class as a MongoDB document and sets the collection name
public class BlogPost {

    @Id // Marks this field as the primary key
    private String id;

    private String title;
    private String content;
}
