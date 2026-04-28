# 🛒 First REST API – Spring Boot Product Management API

A fully functional **RESTful API** built with **Java** and the **Spring Framework**, developed as part of the Spring Framework Apps university project at Akademia Finansów i Biznesu Vistula.

The application manages products through a complete **CRUD** (Create, Read, Update, Delete) interface, backed by an **H2 in-memory database**, documented with **Swagger UI (OpenAPI)**, and structured following industry best practices.

---

## 📋 Table of Contents

- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure)
- [Getting Started](#-getting-started)
- [API Endpoints](#-api-endpoints)
- [Usage Examples](#-usage-examples)
- [Swagger UI](#-swagger-ui)
- [H2 Database Console](#-h2-database-console)
- [How It Works](#-how-it-works)
- [Exception Handling](#-exception-handling)

---

## 🧰 Tech Stack

| Technology | Version | Purpose |
|---|---|---|
| Java | 21 | Programming language |
| Spring Boot | 3.4.5 | Application framework |
| Spring Web (MVC) | via Boot | REST controller support |
| Spring Data JPA | via Boot | Database ORM layer |
| Hibernate | via JPA | Entity management |
| H2 Database | via Boot | In-memory database |
| Springdoc OpenAPI | 2.8.6 | Swagger UI / API docs |
| Spring Boot DevTools | via Boot | Hot reload during development |
| Maven | 3.x | Dependency management & build |

---

## 📁 Project Structure

```
src/main/java/pl/edu/vistula/firstrestapispring/
│
├── FirstRestApiSpringApplication.java        # Main entry point
│
├── product/
│   ├── api/
│   │   ├── request/
│   │   │   ├── ProductRequest.java           # Incoming POST request body
│   │   │   └── UpdateProductRequest.java     # Incoming PUT request body
│   │   ├── response/
│   │   │   └── ProductResponse.java          # Outgoing response body
│   │   └── ProductController.java            # REST Controller (all endpoints)
│   │
│   ├── domain/
│   │   └── Product.java                      # JPA Entity (database table)
│   │
│   ├── repository/
│   │   └── ProductRepository.java            # JPA Repository interface
│   │
│   ├── service/
│   │   └── ProductService.java               # Business logic layer
│   │
│   └── support/
│       ├── exception/
│       │   └── ProductNotFoundException.java  # Custom exception
│       ├── ProductExceptionAdvisor.java       # Global exception handler (@ControllerAdvice)
│       ├── ProductExceptionSupplier.java      # Exception factory (Supplier pattern)
│       └── ProductMapper.java                # Object mapper (Request ↔ Entity ↔ Response)
│
└── shared/
    └── api/
        └── response/
            └── ErrorMessageResponse.java     # Standard error response body

src/main/resources/
└── application.properties                    # App configuration (H2, JPA, logging)
```

---

## 🚀 Getting Started

### Prerequisites

Make sure you have the following installed:

- **Java 21** (JDK)
- **Maven 3.x**
- **IntelliJ IDEA** (recommended) or any Java IDE

### Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/first-rest-api-spring.git
cd first-rest-api-spring
```

### Run the Application

**Option 1 — IntelliJ IDEA:**
1. Open the project in IntelliJ
2. Wait for Maven to download dependencies
3. Click the green ▶ **Run** button next to `FirstRestApiSpringApplication`

**Option 2 — Maven command line:**
```bash
mvn spring-boot:run
```

**Option 3 — Build and run the JAR:**
```bash
mvn clean package
java -jar target/first-rest-api-spring-0.0.1-SNAPSHOT.jar
```

### Verify It's Running

Once started, you should see in the console:

```
Started FirstRestApiSpringApplication in X.XXX seconds
```

The application will be available at: **`http://localhost:8080`**

---

## 📡 API Endpoints

Base URL: `http://localhost:8080/api/v1/products`

| Method | Endpoint | Description | Request Body | Response |
|---|---|---|---|---|
| `POST` | `/api/v1/products` | Create a new product | `{"name": "Laptop"}` | `201 Created` |
| `GET` | `/api/v1/products` | Get all products | — | `200 OK` |
| `GET` | `/api/v1/products/{id}` | Get product by ID | — | `200 OK` |
| `PUT` | `/api/v1/products/{id}` | Update a product | `{"name": "Gaming Laptop", "id": 1}` | `200 OK` |
| `DELETE` | `/api/v1/products/{id}` | Delete a product | — | `204 No Content` |

---

## 📖 Usage Examples

### ➕ Create a Product (POST)

**Request:**
```http
POST http://localhost:8080/api/v1/products
Content-Type: application/json

{
  "name": "Laptop"
}
```

**Response — `201 Created`:**
```json
{
  "id": 1,
  "name": "Laptop"
}
```

---

### 📋 Get All Products (GET)

**Request:**
```http
GET http://localhost:8080/api/v1/products
```

**Response — `200 OK`:**
```json
[
  {
    "id": 1,
    "name": "Laptop"
  },
  {
    "id": 2,
    "name": "Mouse"
  }
]
```

---

### 🔍 Get Product by ID (GET)

**Request:**
```http
GET http://localhost:8080/api/v1/products/1
```

**Response — `200 OK`:**
```json
{
  "id": 1,
  "name": "Laptop"
}
```

**Response — `404 Not Found` (if ID doesn't exist):**
```json
{
  "message": "Product with 99 not found"
}
```

---

### ✏️ Update a Product (PUT)

**Request:**
```http
PUT http://localhost:8080/api/v1/products/1
Content-Type: application/json

{
  "name": "Gaming Laptop",
  "id": 1
}
```

**Response — `200 OK`:**
```json
{
  "id": 1,
  "name": "Gaming Laptop"
}
```

---

### 🗑️ Delete a Product (DELETE)

**Request:**
```http
DELETE http://localhost:8080/api/v1/products/1
```

**Response — `204 No Content`**  
*(empty body — deletion was successful)*

**Response — `404 Not Found` (if ID doesn't exist):**
```json
{
  "message": "Product with 1 not found"
}
```

---

## 🧪 Swagger UI

The API is fully documented and testable via **Swagger UI**.

After starting the app, visit:

```
http://localhost:8080/swagger-ui/index.html
```

You will see an interactive UI like this:

- **POST** `/api/v1/products` — Create product
- **GET** `/api/v1/products` — Find all products
- **GET** `/api/v1/products/{id}` — Find product by ID
- **PUT** `/api/v1/products/{id}` — Update product
- **DELETE** `/api/v1/products/{id}` — Delete product

You can also view the raw **OpenAPI JSON** documentation at:

```
http://localhost:8080/v3/api-docs
```

---

## 🗄️ H2 Database Console

The application uses an **H2 in-memory database**. You can inspect it live via the web console.

1. Visit: `http://localhost:8080/console`
2. Set the **JDBC URL** to:
   ```
   jdbc:h2:mem:testdb
   ```
3. Leave **Username** as `sa` and **Password** empty
4. Click **Connect**

### Useful SQL Queries

```sql
-- View all products
SELECT * FROM PRODUCTS;

-- View table structure
SHOW COLUMNS FROM PRODUCTS;

-- Manually insert a product
INSERT INTO PRODUCTS (NAME) VALUES ('Keyboard');

-- Delete a product
DELETE FROM PRODUCTS WHERE ID = 1;
```

> ⚠️ **Note:** The H2 database is in-memory — all data is lost when the application stops. This is intentional for development/testing.

---

## ⚙️ How It Works

The application follows a **layered architecture** pattern:

```
HTTP Request
     │
     ▼
┌─────────────────────┐
│  ProductController  │  ← Receives HTTP requests, returns HTTP responses
│  (@RestController)  │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│   ProductService    │  ← Business logic, data processing
│     (@Service)      │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│ ProductRepository   │  ← Database read/write via Spring Data JPA
│   (@Repository)     │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│    H2 Database      │  ← In-memory relational database (PRODUCTS table)
└─────────────────────┘
```

### Object Mapping Flow

```
JSON Request Body
       │
       ▼
 ProductRequest  ──(ProductMapper)──►  Product (Entity)  ──► Database
                                            │
                                            ▼
JSON Response  ◄──(ProductMapper)──  ProductResponse
```

The `ProductMapper` component is responsible for converting between:
- `ProductRequest` → `Product` (before saving)
- `Product` → `ProductResponse` (before returning to client)

---

## 🚨 Exception Handling

The application handles errors gracefully using `@ControllerAdvice`.

| Scenario | HTTP Status | Response |
|---|---|---|
| Product found successfully | `200 OK` | Product JSON |
| Product created successfully | `201 Created` | Product JSON |
| Product deleted successfully | `204 No Content` | *(empty)* |
| Product not found by ID | `404 Not Found` | `{"message": "Product with X not found"}` |
| Internal server error | `500 Internal Server Error` | Error details |

The `ProductNotFoundException` is thrown from the service layer and caught by `ProductExceptionAdvisor`, which maps it to a clean `404` response with a descriptive message.

---

## 📝 Configuration

`src/main/resources/application.properties`:

```properties
# H2 Console
spring.h2.console.enabled=true
spring.h2.console.path=/console/
spring.datasource.url=jdbc:h2:mem:testdb

# Hibernate SQL Logging
logging.level.org.hibernate.SQL=DEBUG
```

---

## 📦 Dependencies (`pom.xml`)

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        <version>2.8.6</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

-

