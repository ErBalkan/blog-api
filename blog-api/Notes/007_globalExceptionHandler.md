# Global Exception Handler – Hata Yönetimi Katmanı

## Amaç:
1. Uygulama genelinde oluşabilecek hataları merkezi bir yerde yakalamak

2. Kullanıcıya anlamlı ve standart hata cevapları dönmek

3. Kod tekrarını önlemek

4. Güvenliği artırmak (örneğin: NullPointer’ı dışarı sızdırmamak)

`📁 Klasör: exception/`
`📄 ResourceNotFoundException.java`

```java
public class ResourceNotFoundException extends RuntimeException {
    // Bu exception, belirli bir kaynağın bulunamadığı durumlarda fırlatılır.
    // Örneğin, bir kullanıcı, yazı veya kategori bulunamadığında kullanılabilir.
    // Bu exception, uygulama genelinde yakalanabilir ve uygun bir HTTP cevabı döndürülebilir.
    // Bu exception, genellikle RESTful API'lerde kullanılır.
    // Örneğin, bir RESTful API'de bir kullanıcı bulunamadığında bu exception fırlatılabilir.
    public ResourceNotFoundException(String message) {
    // Bu constructor, exception mesajını alır ve üst sınıfın constructor'ına iletir.
    // Bu mesaj, exception'ın neden fırlatıldığını belirtir.
    // Örneğin, "Kullanıcı bulunamadı" gibi bir mesaj olabilir.
        super(message);
    }
}
```

🔍 Bu, özel bir RuntimeException. Mevcut olmayan bir post istenirse, bu fırlatılacak.

`📄 GlobalExceptionHandler.java`

```java
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice // Uygulama genelinde tüm controller'ları dinleyen bir yapı
public class GlobalExceptionHandler {

    // Özel ResourceNotFoundException için handler
    @ExceptionHandler(ResourceNotFoundException.class) // Belirli bir exception türünü yakalar
    public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex) { 
        // ResourceNotFoundException, örneğin bir kaynak bulunamadığında fırlatılır
        // ve bu metot çağrılır.
        // Bu metot, exception'ı yakalar ve uygun bir HTTP cevabı döner.
        Map<String, Object> body = new HashMap<>(); // Hata cevabını tutacak bir Map oluştur
        body.put("timestamp", LocalDateTime.now()); // Hata zamanını ekle
        body.put("status", HttpStatus.NOT_FOUND.value()); // HTTP durum kodunu ekle
        body.put("error", "Not Found"); // Hata mesajını ekle
        body.put("message", ex.getMessage()); // Exception mesajını ekle
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND); // Map'i ResponseEntity olarak döndür
    }

    // Tüm diğer hatalar için generic handler
    @ExceptionHandler(Exception.class) // Tüm diğer exception türlerini yakalar
    public ResponseEntity<?> handleGeneralException(Exception ex) {
        // Bu metot, beklenmeyen hataları yakalar ve uygun bir HTTP cevabı döner.
        // Örneğin, veritabanı bağlantı hataları, sunucu hataları vb. durumlar için kullanılabilir.
        // Bu metot, genel bir hata yakalama mekanizması sağlar.
        Map<String, Object> body = new HashMap<>(); // Hata cevabını tutacak bir Map oluştur
        body.put("timestamp", LocalDateTime.now()); // Hata zamanını ekle
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value()); // HTTP durum kodunu ekle
        body.put("error", "Internal Server Error"); // Hata mesajını ekle
        body.put("message", ex.getMessage()); // Exception mesajını ekle
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR); // Map'i ResponseEntity olarak döndür
    }
}

/*
🔍 @ControllerAdvice → Uygulama genelinde tüm controller’ları dinleyen bir yapı
🔍 @ExceptionHandler → Belirli exception türlerini yakalayıp özel işlem yapar
🔐 Verilen cevabı kontrol ederek hem frontend'e düzgün bilgi sağlarız hem loglamaya olanak tanırız
 */

```

### ✅ Ne Başardık?
- Tüm hatalar tek merkezde yönetiliyor.

- API kullanıcıları net mesajlar alıyor.

- Kod karmaşası ve tekrar ortadan kalktı.

- Kurumsal mimariye yakışır bir Exception Handling yapısı kuruldu.