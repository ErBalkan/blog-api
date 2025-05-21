# application.properties Dosyası

`src/main/resources/application.properties` içine şimdilik şunları ekleyelim:

# Port
server.port=8080

# PostgreSQL bağlantısı
spring.datasource.url=jdbc:postgresql://localhost:5432/blog_db
spring.datasource.username=postgres
spring.datasource.password=yourpassword

# JPA Ayarları
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Zaman dilimi
spring.jackson.time-zone=UTC


yourpassword kısmına kendi veritabanı şifreni yaz.
blog_db isimli bir veritabanını PostgreSQL'de manuel olarak oluşturmalısın. 

