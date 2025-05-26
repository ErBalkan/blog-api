package com.erbalkan.blog_api.utils.exception;

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

