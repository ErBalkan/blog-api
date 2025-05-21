package com.erbalkan.blog_api.service.abstracts;

import java.util.List;
import java.util.Optional;

import com.erbalkan.blog_api.entity.Post;

public interface PostService {
    Post createPost(Post post);
    List<Post> getAllPosts();
    Optional<Post> getPostById(Long id);
    Post updatePost(Long id, Post post);
    void deletePost(Long id);
}
