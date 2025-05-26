package com.erbalkan.blog_api.service.concretes;

import org.springframework.stereotype.Service;

import com.erbalkan.blog_api.entity.Post;
import com.erbalkan.blog_api.repository.PostRepository;
import com.erbalkan.blog_api.service.abstracts.PostService;
import com.erbalkan.blog_api.utils.exception.ResourceNotFoundException;

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
    Post existingPost = postRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
        // Mevcut postu bul, eğer yoksa ResourceNotFoundException fırlat
        // Eğer post bulunamazsa, ResourceNotFoundException fırlatılır.
        // orElseThrow → Eğer kayıt bulunmazsa özel hata fırlatır, bu da global handler’a düşer
        existingPost.setTitle(post.getTitle()); // Post başlığını güncelle
    existingPost.setContent(post.getContent()); // Post içeriğini güncelle

    return postRepository.save(existingPost); // Güncellenmiş postu kaydet ve döndür
}

@Override
public void deletePost(Long id) {
    Post post = postRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
    
    postRepository.delete(post);
}

}

