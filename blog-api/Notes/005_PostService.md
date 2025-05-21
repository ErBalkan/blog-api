# PostService – İş Mantığı Katmanı
Hedef:

- Post işlemlerini yönetecek servis sınıfını oluşturmak

- CRUD işlemlerini soyutlamak

- Dependency Injection ve SOLID prensiplerini uygulamak

 Klasör: service/
📄 PostService.java

```java
package com.patron.blog.service;

import com.patron.blog.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Post createPost(Post post);
    List<Post> getAllPosts();
    Optional<Post> getPostById(Long id);
    Post updatePost(Long id, Post post);
    void deletePost(Long id);
}
```

Bu bir interface, yani sadece sözleşme.
Gerçek kodu PostServiceImpl içinde yazacağız.
Bu yapı sayesinde Dependency Inversion Principle (DIP) uygulanır: controller → interface’e bağlı olur, implementasyona değil.

PostServiceImpl.java

```java
package com.patron.blog.service;

import com.patron.blog.entity.Post;
import com.patron.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired // Constructor injection: Spring otomatik olarak PostRepository'i buraya enjekte eder.
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
```

| Yapı                     | Açıklama                                                               |
| ------------------------ | ---------------------------------------------------------------------- |
| `PostService` interface  | Uygulamanın iş kurallarını tanımlar.                                   |
| `PostServiceImpl`        | Bu interface’i implement eder, gerçek işlemleri içerir.                |
| `@Service`               | Spring’e bu sınıfın bir servis olduğunu söyler.                        |
| `@Autowired` constructor | Spring, `PostRepository` nesnesini otomatik olarak enjekte eder. (DIP) |
| `Optional<Post>`         | `null` güvenliği için kullanılır.                                      |


Neden böyle yaptık? (SOLID Uygulaması)
- __SRP (Single Responsibility Principle):__ Her sınıf sadece kendi işini yapıyor (PostService sadece iş mantığı)

- __DIP (Dependency Inversion):__ Servis interface’e bağlı, implementasyona değil

- __OCP (Open/Closed):__ Yeni kurallar eklemek için kodu değiştirmeye gerek yok – interface üzerinden genişletilebilir.



