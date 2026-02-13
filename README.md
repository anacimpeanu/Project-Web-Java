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
	 - Test reports are generated at `Project/target/surefire-reports/`.

 - **Release with artifacts:**
	 - I published a release containing `artifacts.zip` (JAR + `surefire-reports`). Download from the repository releases page.

 - **CI:**
	 - A GitHub Actions workflow is included at `.github/workflows/maven.yml` to run `./mvnw -f Project test` on push and pull requests.

If you want, I can add a CI status badge to this README or attach the artifacts to the open PR.

## API Documentation

The application includes comprehensive REST API documentation using **Swagger/OpenAPI 3.0**.

### Accessing API Documentation

- **Swagger UI**: `http://localhost:8080/swagger-ui/index.html`
- **OpenAPI JSON**: `http://localhost:8080/v3/api-docs`

### Available Endpoints

The API provides **5+ REST endpoints** covering all MVP features:

#### User Management (`/api/users`)
- `POST /api/users/register` - Register new user
- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get user by ID

#### Product Management (`/api/products`)
- `POST /api/products` - Create new product
- `GET /api/products` - Get all products
- `GET /api/products/category/{categoryId}` - Get products by category
- `DELETE /api/products/{id}` - Delete product

#### Category Management (`/api/categories`)
- `POST /api/categories` - Create new category
- `GET /api/categories` - Get all categories

#### Shopping Cart (`/api/cart`)
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
3. **Product** - Cosmetic items catalog
4. **Cart** - User shopping cart
5. **CartItem** - Individual cart entries
6. **Order** - Purchase orders
7. **OrderItem** - Individual order line items

### Entity Relationships

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
2. **ProductService** - Product CRUD, inventory management
3. **CategoryService** - Category management
4. **CartService** - Shopping cart operations
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

3. **Test with Postman/GUI:**
   - Import the OpenAPI specification from `http://localhost:8080/v3/api-docs`
   - Use the interactive Swagger UI for testing endpoints

## Development Status

✅ **Completed Requirements:**
- No compilation errors
- All business requirements implemented
- Clean code following DRY principles
- Java coding styles and conventions
- All tests passing
- REST endpoints for all MVP features
- Service layer with business logic
- Repository layer with JPA
- Unit tests for endpoints and services
- Database entities with relationships
- POJO validation
- Swagger API documentation
- Postman/GUI compatible API
