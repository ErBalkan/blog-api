package com.erbalkan.blog_api.utils.exception;

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
