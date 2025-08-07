# DreamShops E-Commerce Backend API

## Description

DreamShops is a simple, robust, and extensible backend API for an e-commerce platform. It provides core functionalities for user management, product catalog, cart, order processing, and secure authentication, built with modern Java and Spring Boot best practices.

## Features

- User registration, authentication (JWT), and profile management
- Product catalog CRUD (admin) and product search/filtering (all users)
- Category and brand-based product queries
- Shopping cart management (add, remove, clear, view cart)
- Order placement and order history
- Role-based access control (admin/user)
- Image upload and management for products
- Exception handling and API responses

## Tech Stack

- Java 17
- Spring Boot 3.3.x
- Spring Data JPA (Hibernate)
- Spring Security (JWT)
- MySQL
- Lombok
- ModelMapper
- Maven

## Installation & Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/yordanossole/E-Commerce-Backend-Application
   cd E-Commerce-Backend-Application
   ```
2. **Configure MySQL:**
   - Create a database named `dream_shops_db`.
   - Update `src/main/resources/application.properties` with your MySQL username and password.
3. **Build the project:**
   ```bash
   ./mvnw clean install
   ```

## Required Environment Variables

Set these in `src/main/resources/application.properties`:

- `spring.datasource.url` (e.g., `jdbc:mysql://localhost:3306/dream_shops_db`)
- `spring.datasource.username`
- `spring.datasource.password`
- `auth.token.jwtSecrete` (JWT secret key)
- `server.port` (default: 9193)

## How to Run the Server

```bash
./mvnw spring-boot:run
```

The server will start on `http://localhost:9193` by default.


## Example API Usage

### Register a User

```http
POST /api/v1/users/add
Content-Type: application/json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john@example.com",
  "password": "yourpassword"
}
```

### Login

```http
POST /api/v1/auth/login
Content-Type: application/json
{
  "email": "john@example.com",
  "password": "yourpassword"
}
```

### Get All Products

```http
GET /api/v1/products/all
```

### Add Product (Admin)

```http
POST /api/v1/products/product/add
Authorization: Bearer <JWT_TOKEN>
Content-Type: application/json
{
  "name": "Laptop",
  "brand": "BrandX",
  "price": 1200.00,
  "inventory": 10,
  "category": "Electronics"
}
```

## How to Run Tests

```bash
./mvnw test
```

## Folder Structure

```
src/
  main/
    java/com/yordanos/dreamShops/
      controller/      # REST controllers (User, Product, Cart, Order, etc.)
      service/         # Business logic (UserService, ProductService, etc.)
      repository/      # Spring Data JPA repositories
      model/           # Entity models (User, Product, Order, etc.)
      dto/             # Data Transfer Objects
      request/         # Request payload classes
      response/        # Response payload classes
      security/        # Security config, JWT, user details
      exceptions/      # Custom exception classes
      enums/           # Enum types (e.g., OrderStatus)
      data/            # Data initialization
      DreamShopsApplication.java  # Main Spring Boot application
    resources/
      application.properties      # App configuration
  test/
    java/com/yordanos/dreamShops/
      DreamShopsApplicationTests.java
```

## Contact

For questions, or issues please contact:

- **Yordanos**
- Email: yordanos.sole@gmail.com
