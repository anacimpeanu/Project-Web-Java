# 🧪 API Demonstration Guide

## Quick Demo Options

### Option 1: Automated Script Demo (Fastest)
```bash
# Terminal 1: Start the application
cd Project
./mvnw spring-boot:run

# Terminal 2: Run the demo script  
./demo-api.sh
```

### Option 2: Postman Collection (Interactive)
1. Import `Kyra_Cosmetics_API_Demo.postman_collection.json` in Postman
2. Set base URL: `http://localhost:8080/api`
3. Execute requests in the provided order
4. View responses and test different scenarios

### Option 3: Swagger UI (Browser-based)
1. Start application: `./mvnw spring-boot:run`
2. Open: `http://localhost:8080/swagger-ui/index.html`
3. Test endpoints interactively with the provided interface

## Demo Flow Explanation

### 1. User Management
- **Register User**: Creates a new customer account
- **Get All Users**: Retrieves all registered users
- **Get User by ID**: Fetches specific user details

### 2. Category Management  
- **Create Categories**: Sets up product categories (Makeup, Skincare)
- **Get Categories**: Lists all available categories

### 3. Product Management
- **Add Products**: Creates cosmetic products in categories
- **Get All Products**: Retrieves complete product catalog
- **Get by Category**: Filters products by category

### 4. Shopping Cart
- **Add to Cart**: Adds products with quantities to user's cart
- **View Cart**: Shows current cart contents and total
- **Remove from Cart**: Removes specific products

### 5. Order Processing
- **Place Order**: Converts cart contents to an order
- **View Orders**: Shows user's order history
- **Update Status**: Tracks order fulfillment (admin feature)

## Expected Results

✅ **All endpoints should return HTTP 200/201 status codes**  
✅ **Data should persist between requests**  
✅ **Relationships should be maintained (User→Cart→Order)**  
✅ **Business logic should be validated (stock checks, price calculations)**

## Troubleshooting

- **404 Errors**: Ensure Spring Boot app is running on port 8080
- **Validation Errors**: Check request JSON format matches examples
- **Database Issues**: H2 is in-memory, restart app to reset data
- **CORS Issues**: API configured for localhost:3000 frontend access

## Database Access

H2 Console: `http://localhost:8080/h2-console`
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: (empty)

View created tables and data directly in the browser.