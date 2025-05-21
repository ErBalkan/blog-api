# PostController – Web Katmanı (Presentation Layer)
Hedef:

- RESTful API uç noktaları (endpoint) oluşturmak

- CRUD işlemlerini HTTP üzerinden yönetmek

- DTO kullanmadan temel yapıyı test etmek

Klasör: controller/
📄 PostController.java

```java
package com.patron.blog.controller;

import com.patron.blog.entity.Post;
import com.patron.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @Autowired // Spring bu servisi constructor ile enjekte eder
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        return ResponseEntity.ok(postService.createPost(post));
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        return postService.getPostById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post post) {
        return ResponseEntity.ok(postService.updatePost(id, post));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
```

| Kod / Anotasyon                                | Açıklama                                                         |
| ---------------------------------------------- | ---------------------------------------------------------------- |
| `@RestController`                              | Bu sınıfın REST API controller olduğunu belirtir                 |
| `@RequestMapping("/api/posts")`                | Tüm rotalar `/api/posts` ile başlar                              |
| `@PostMapping`                                 | HTTP POST isteği için                                            |
| `@GetMapping`, `@PutMapping`, `@DeleteMapping` | CRUD metodlarına karşılık gelir                                  |
| `@RequestBody`                                 | Gelen JSON’ı Post objesine map eder                              |
| `@PathVariable`                                | URL'deki `{id}` parametresini alır                               |
| `ResponseEntity<>`                             | HTTP cevabını status code ile birlikte döndürmek için kullanılır |


# Neden bu yapıyı kullandık?
1. REST mimarisi kurallarına uygunluk 

2. Controller sadece yönlendirici: hiçbir iş mantığı içermez → SRP (Single Responsibility Principle) uygulanmış olur

3. Gelecekte DTO, ExceptionHandler, Validation kolayca entegre edilebilir → Open/Closed Principle

# RESPONSEENTITY 

ResponseEntity<T> sınıfı, Java Spring Boot çatısında HTTP yanıtlarını daha kontrollü ve detaylı bir şekilde döndürmek için kullanılan güçlü bir yapıdır. Basitçe söylemek gerekirse:

🔥 ResponseEntity, sadece veri değil, aynı zamanda HTTP status code, header gibi detayları da döndürebilmeni sağlar.

```java
return ResponseEntity.notFound().build();

// notFound() → 404 durumunu ayarlar
// .build() → ResponseEntity<Void> nesnesini oluşturur
```