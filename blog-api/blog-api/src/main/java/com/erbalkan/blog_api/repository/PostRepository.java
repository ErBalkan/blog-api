package com.erbalkan.blog_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erbalkan.blog_api.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // İleride özel sorgular burada yazılacak
}

