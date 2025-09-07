package com.example.blog.blogapp.controller;

import com.example.blog.blogapp.repository.BlogPostRepository;
import com.example.blog.blogapp.model.BlogPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // This means that this class is a Controller
@RequestMapping("/api/posts") // This means that all URLs start with /api/posts
@CrossOrigin(origins = "http://localhost:3000") // Allows requests from our React frontend
public class BlogPostController {

    @Autowired // This means to get the bean called blogPostRepository
    private BlogPostRepository blogPostRepository;

    // GET all blog posts
    @GetMapping
    public List<BlogPost> getAllPosts() {
        return blogPostRepository.findAll();
    }

    // GET a single blog post by ID
    @GetMapping("/{id}")
    public ResponseEntity<BlogPost> getPostById(@PathVariable String id) {
        Optional<BlogPost> post = blogPostRepository.findById(id);
        return post.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // CREATE a new blog post
    @PostMapping
    public BlogPost createPost(@RequestBody BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }

    // UPDATE a blog post
    @PutMapping("/{id}")
    public ResponseEntity<BlogPost> updatePost(@PathVariable String id, @RequestBody BlogPost postDetails) {
        Optional<BlogPost> optionalPost = blogPostRepository.findById(id);
        if (optionalPost.isPresent()) {
            BlogPost post = optionalPost.get();
            post.setTitle(postDetails.getTitle());
            post.setContent(postDetails.getContent());
            return ResponseEntity.ok(blogPostRepository.save(post));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE a blog post
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable String id) {
        if (blogPostRepository.existsById(id)) {
            blogPostRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

