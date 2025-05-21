# Spring Boot Projesini Oluştur (Spring Initializr)

Kullanacağımız Bağımlılıklar:

- Spring Web (REST API yazmak için)

- Spring Data JPA (veritabanı işlemleri için)

- PostgreSQL Driver (veritabanına bağlanmak için)

- Spring Boot DevTools (otomatik yeniden başlatma)

- Validation (girdi doğrulama için)

- Spring Security (JWT ile kimlik doğrulama için — daha sonra eklenecek)



# Uygulama Ana Sınıfı (Main Class)

```java
package com.patron.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApiApplication.class, args);
    }
}
```

__@SpringBootApplication:__ Bu anotasyon, üç temel anotasyonu birleştirir:

- __@Configuration →__ Yapılandırma sınıfı olduğunu belirtir.

- __@EnableAutoConfiguration →__ Spring Boot’un otomatik yapılandırma yapmasını sağlar.

- __@ComponentScan →__ Belirtilen paketteki tüm bileşenleri tarayıp yükler.

__SpringApplication.run(...):__ Uygulamayı ayağa kaldırır.

