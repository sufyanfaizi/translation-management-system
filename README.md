# Translation Management System

## Overview
The **Translation Management System** is a Spring Boot-based API that provides scalable and efficient translation management. It allows storing, retrieving, and managing translations for different locales with tagging for context. 

## Features
- **CRUD Operations**: Manage translations with optimized SQL queries.
- **Multi-Locale Support**: Store translations for different languages.
- **Authentication**: Secure API with token-based authentication.
- **High Performance**: Optimized for speed with JSON responses in <500ms.
- **Database Seeding**: Preload translations for scalability testing.

## Tech Stack
- **Backend**: Spring Boot (Maven), Spring Data JPA, Spring Security
- **Database**: MySQL
- **Authentication**: JWT (JSON Web Token)

---

## 🚀 Setup Instructions

### **1. Clone the Repository**
```sh
git clone https://github.com/sufyanfaizi/translation-management-system.git
cd translation-management-system
```

### **2. Configure the Database**
- Update `application.properties` (or `application.yml`) with your database settings:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/translation_db
spring.datasource.username=root
spring.datasource.password=yourpassword
```

### **3. Build and Run the Application**
```sh
mvn clean install
mvn spring-boot:run
```

### **4. 🚀 Swagger API Documentation**
Once the application is running, you can access the Swagger UI for API documentation:

- Swagger UI: http://localhost:8080/swagger-ui/index.html
- OpenAPI Spec: http://localhost:8080/v3/api-docs

### **5. API Endpoints**
| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/auth/login` | User login |
| `POST` | `/api/auth/register` | User Register |
| `GET` | `/api/v1/translations/{locale}` | Get translations by locale |
| `POST` | `/api/v1/translations` | Add new translation |
| `PUT` | `/api/v1/translations` | Update translation |
| `PUT` | `/api/v1/translations/search` | Search translation |
| `DELETE` | `/api/v1/translations/export` | Export translation |

---

## 🎯 Design Choices

### **1. Spring Boot with Maven**
- Modular, easy to extend, and widely supported.
- Built-in dependency management with `pom.xml`.

### **2. Database with JPA/Hibernate**
- ORM simplifies interaction with relational databases.
- Flexible query handling for translation searches.

### **3. JWT Authentication**
- Secure token-based authentication for API access.
- Eliminates session-based authentication overhead.

### **4. Optimized SQL Queries**
- Indexing and batch operations for better performance.
- Lazy loading for unnecessary data retrieval.

---

## 📌 Future Enhancements
- Web UI for translation management.
- Role-based access control (RBAC).
- Caching for high-speed translation lookups.

---

## 👨‍💻 Author
Developed by **Sufyan Faizi**  
