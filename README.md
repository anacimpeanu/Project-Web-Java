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

