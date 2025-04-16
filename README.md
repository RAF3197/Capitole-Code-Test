# 📌 Technical Interview

## 📦 Challenge Description

Development of a **scalable microservice** to manage the company's product catalog.

---

## ✅ Mandatory Requirements Implemented

- [x] REST API to serve a product catalog.
- [x] Endpoint to retrieve the products.
- [x] Discounts:
    - **Electronics**: 15%
    - **Home & Kitchen**: 25%
    - **SKUs ending in `5`**: 30%
    - Only **one discount** is applied per product, **the highest one**.
- [x] Products can be filtered by `category`.
- [x] Products can be sorted by:
    - `SKU`
    - `Price`
    - `Description`
    - `Category`
- [x] Testing
- [x] Follows clean code principles and best practices.

---

## ⭐ Nice to Have Implemented

- [x] **Pagination** support with `page` and `size` parameters.
- [x] **Swagger / OpenAPI** documentation included.
- [] Integration tests included.

---

## 🚀 Tech Stack

- **Kotlin**
- **Spring Boot**
- **PostgreSQL**
- **MapStruct**
- **JUnit 5**, **Mockito**
- **OpenAPI / Swagger UI**

---
## 🧱 Architectural decisions

This project follows the **Hexagonal Architecture (Ports and Adapters)**. This approach makes the codebase more testable, maintainable, and scalable.

### Key decisions:
- **DDD-Domain Driven Design**: Business logic is isolated in the `Domain` layer, with clear contracts defined via interfaces.
- **Infrastructure layer**: Controllers, repositories, and mappers are placed in the `Infrastructure` layer.
- **Application layer**: Contains use cases and services.
- **Discounts**: I decided to implement the `DiscountService` following a `Strategy pattern` at the application layer, if these discounts are going to change frequently it would be a good approach to save the discounts in BD.
- **Dependency Injections**: This project uses **constructor-based dependency injection**, which is the recommended approach in Spring Boot.

Spring automatically injects dependencies via constructor injection thanks to annotations like `@Component`, `@Service`, `@Repository`, etc., and component scanning.

**Example:**
```kotlin
@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val discountCalculator: DiscountCalculator
)
```
___

## ⚙️ Docker Setup

### 1. 📥 Clone the repository
git clone https://github.com/RAF3197/Capitole-Code-Test.git
### 2. 🐳 Run docker-compose
docker-compose up -d --build
### 3. 🌍 Service deployed
http://localhost:8080/
___

## 🧑‍💻 Swagger api documentation

### 1. Active at 
http://localhost:8080/swagger-ui/index.html
___
