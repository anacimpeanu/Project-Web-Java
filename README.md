# Kyra Cosmetics - Web Java Project

## Business Domain: Cosmetics E-commerce Platform

### 10 Business Requirements

1. **User Registration & Authentication**: Users can create accounts with email and password validation
2. **Product Catalog Management**: Admin can add, edit, and remove cosmetic products with categories
3. **Shopping Cart Functionality**: Users can add/remove products to/from their shopping cart
4. **Order Processing**: Users can place orders and track order status
5. **Inventory Management**: System tracks product stock levels and prevents overselling
6. **Category Organization**: Products are organized by cosmetic categories (makeup, skincare, fragrances)
7. **User Profile Management**: Users can view and update their profile information
8. **Price Calculation**: System calculates total prices including cart totals and order totals
9. **Order History**: Users can view their past orders and order details
10. **Product Search**: Users can search for products by name or category

### 5 Main MVP Features

1. **User Management**
   - User registration and authentication
   - Role-based access (USER/ADMIN)
   - Profile management

2. **Product Catalog**
   - Product CRUD operations
   - Category management
   - Inventory tracking

3. **Shopping Cart**
   - Add/remove products
   - Quantity management
   - Cart persistence per user

4. **Order Processing**
   - Order creation from cart
   - Order status tracking
   - Order history

5. **Administrative Functions**
   - Product management
   - Category management
   - Order monitoring

## Tests, artifacts and CI

 - **Run tests locally:**

	 ```bash
	 cd Project
	 ./mvnw -f Project test
	 ```

 - **Built artifact & reports:**
	 - The project JAR is produced at `Project/target/Project-0.0.1-SNAPSHOT.jar`.
 - **Release with artifacts:**
	 - I published a release containing `artifacts.zip` (JAR + `surefire-reports`). Download from the repository releases page.

 - **CI:**
	 - A GitHub Actions workflow is included at `.github/workflows/maven.yml` to run `./mvnw -f Project test` on push and pull requests.

<<<<<<< HEAD
=======
## API Documentation

The application includes comprehensive REST API documentation using **Swagger/OpenAPI 3.0**.

### Accessing API Documentation

### Available Endpoints

- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get user by ID
- `POST /api/products` - Create new product
- `GET /api/products` - Get all products
#### Category Management (`/api/categories`)
- `POST /api/categories` - Create new category
- `GET /api/cart/{userId}` - Get user's cart
- `POST /api/cart/{userId}/add` - Add product to cart
- `DELETE /api/cart/{userId}/remove` - Remove product from cart

#### Order Management (`/api/orders`)
- `POST /api/orders/{userId}` - Place new order
- `GET /api/orders/user/{userId}` - Get user's orders
- `PATCH /api/orders/{orderId}/status` - Update order status

## Technical Architecture

### Database Entities

The application persists data for **at least 6 entities** with **4+ relations**:

1. **User** - Customer accounts with authentication
2. **Category** - Product categorization

- User ↔ Cart (One-to-One)
- User ↔ Order (One-to-Many)
- Category ↔ Product (One-to-Many)
- Cart ↔ CartItem (One-to-Many)
- Order ↔ OrderItem (One-to-Many)
- Product ↔ CartItem (Many-to-One)
- Product ↔ OrderItem (Many-to-One)

### Services & Business Logic

The application implements **5 services** with comprehensive business logic:

1. **UserService** - User registration, authentication, profile management
5. **OrderService** - Order processing and status management

### Data Validation

All POJO classes include validation constraints using **Bean Validation API**:
- Input validation for all endpoints
- Custom validation rules
- Error handling and meaningful responses

## Running the Application

1. **Start the application:**
   ```bash
   cd Project
   ./mvnw spring-boot:run
   ```

2. **Access the application:**
   - API Base URL: `http://localhost:8080/api`
   - Swagger UI: `http://localhost:8080/swagger-ui/index.html`
   - H2 Console: `http://localhost:8080/h2-console`
- REST endpoints for all MVP features
- Service layer with business logic
- Repository layer with JPA
- Unit tests for endpoints and services
- Database entities with relationships
- POJO validation
- Swagger API documentation
- Postman/GUI compatible API

## 🧪 **API Testing & Demonstration**

### Automated Demo Script

For a quick automated demonstration of all features:

```bash
# Start the application first
./mvnw spring-boot:run

# In another terminal, run the demo script
./demo-api.sh
```

This script will automatically test all MVP features in sequence and display the results.

### Postman Collection

A complete Postman collection is provided to demonstrate all API functionality:

📁 **File**: `Kyra_Cosmetics_API_Demo.postman_collection.json`

**Import Steps:**
1. Open Postman
2. Click "Import" button
3. Select the collection file
4. Start the Spring Boot application
5. Run the requests in sequence

**Test Flow:**
1. **User Management** - Register users and retrieve user data
5. **Order Processing** - Place orders, track status, view order history

### Alternative: Swagger UI Testing

You can also test all endpoints interactively using Swagger UI:
- URL: `http://localhost:8080/swagger-ui/index.html`
- All endpoints are documented with examples
- Interactive testing interface included

### Live Demo Instructions

**Quick Start Demo:**
1. Start the application: `./mvnw spring-boot:run`
2. Wait for "Started ProjectApplication" message
3. Open Swagger UI: `http://localhost:8080/swagger-ui/index.html`
4. Follow this sequence:
   - Register a user via `/api/users/register`
   - Create categories via `/api/categories`
   - Add products via `/api/products`
   - Test shopping cart via `/api/cart/{userId}/add`
   - Place an order via `/api/orders/{userId}`

**Sample Data for Testing:**
```json
// User Registration
{
  "name": "Test User",
  "email": "test@email.com", 
  "password": "password123",
  "role": "CUSTOMER"
}

// Category Creation
{
  "name": "Makeup"
}

// Product Creation  
{
  "name": "Red Lipstick",
  "description": "Matte red lipstick",
  "price": 25.99,
  "stock": 100
}
```
>>>>>>> 2d6e030 (fix)
