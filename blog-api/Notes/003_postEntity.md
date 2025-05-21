# Klasör Yapısını Kur
`src/main/java/com/patron/blog/` altında şu klasörleri oluştur:

- entity/
- repository/
- controller/
- service/
- dto/

Bu yapılar, __katmanlı mimarinin__ temelidir.

# Entity Katmanı: Post
__Post.java__ dosyasını __entity/__ klasörüne ekle:

```java
package com.patron.blog.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Getter ve Setter'lar (Lombok eklemeden elle yazacağız)
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
}

```

| Anotasyon              | Görevi                                                            |
| ---------------------- | ----------------------------------------------------------------- |
| `@Entity`              | Bu sınıfın bir veritabanı tablosunu temsil ettiğini belirtir      |
| `@Table(name = "...")` | Tablonun adını `posts` olarak tanımlar                            |
| `@Id`                  | Birincil anahtar olduğunu belirtir                                |
| `@GeneratedValue(...)` | ID'nin otomatik artan şekilde üretilmesini sağlar                 |
| `@Column(...)`         | Sütunlara özel kurallar ekler (null olamaz, metin tipi vs.)       |
| `@PrePersist`          | Kayıt oluşturulurken çalışacak metod (otomatik timestamp ataması) |
| `@PreUpdate`           | Kayıt güncellenirken çalışacak metod                              |


# Neden böyle yazdık? (SOLID: SRP)
Post sınıfı sadece bir veri modeli tanımlar. Başka bir iş yapmaz.
Örneğin: veritabanı işlemleri, iş mantığı, validasyon, DTO dönüşümleri gibi görevler farklı katmanlarda olacak.
Böylece Single Responsibility Principle uygulanmış olur.