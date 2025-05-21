# PostRepository – Veri Erişim Katmanı (Data Access Layer)

Hedef:

- Post tablosuna erişim sağlamak

- CRUD işlemlerini hazır metodlarla kullanmak

__SOLID:__ Interface Segregation & Dependency Inversion prensiplerine ilk adımı atmak


Klasör: repository/
📄 PostRepository.java:

```java
package com.patron.blog.repository;

import com.patron.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // İleride özel sorgular burada yazılacak
}

```

| Kod / Anotasyon                                           | Açıklama                                                                           |
| --------------------------------------------------------- | ---------------------------------------------------------------------------------- |
| `@Repository`                                             | Spring'e bu sınıfın bir Repository (veri katmanı) olduğunu söyler                  |
| `JpaRepository<Post, Long>`                               | Generic interface: `Post` entity'si için, ID tipi `Long` olan CRUD işlemler sağlar |
| `findById`, `findAll`, `save`, `deleteById` gibi metodlar | Otomatik olarak hazır gelir                                                        |


# Neden böyle yazdık?
Spring Data JPA sana boilerplate (tekrar eden kod) yazdırmadan hazır metotlarla CRUD (create-read-update-delete) işlemleri sağlar.
Ayrıca bu yapı sayesinde Dependency Inversion Principle (DIP) uygulanır çünkü iş katmanı (Service), soyut arayüzlere (PostRepository) bağlı olur, somut sınıflara değil.