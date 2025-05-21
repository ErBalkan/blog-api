package com.erbalkan.blog_api.service.concretes;

import org.springframework.stereotype.Service;

import com.erbalkan.blog_api.entity.Post;
import com.erbalkan.blog_api.repository.PostRepository;
import com.erbalkan.blog_api.service.abstracts.PostService;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    // @Autowired // Constructor injection: Spring otomatik olarak PostRepository'i buraya enjekte eder.
    // Artık yazılmasına gerek yok. 
    // Spring, bu sınıfın bir örneğini oluştururken PostRepository'i otomatik olarak sağlar.
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post updatePost(Long id, Post post) {
        Optional<Post> existingPost = postRepository.findById(id);
        if (existingPost.isPresent()) {
            Post updated = existingPost.get();
            updated.setTitle(post.getTitle());
            updated.setContent(post.getContent());
            return postRepository.save(updated);
        } else {
            throw new RuntimeException("Post not found with id: " + id);
        }
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}

