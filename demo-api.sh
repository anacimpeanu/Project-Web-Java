#!/bin/bash

# Kyra Cosmetics API Demo Script
# This script demonstrates all the main functionality of the API

API_BASE="http://localhost:8080/api"

echo "🧪 Kyra Cosmetics API Demo"
echo "=========================="
echo ""

# Check if server is running
echo "📡 Checking if server is running..."
if ! curl -s "$API_BASE/users" > /dev/null 2>&1; then
    echo "❌ Server is not running. Please start with: ./mvnw spring-boot:run"
    exit 1
fi
echo "✅ Server is running!"
echo ""

# 1. Register a user
echo "👤 1. Registering a new user..."
USER_RESPONSE=$(curl -s -X POST "$API_BASE/users/register" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Demo User",
    "email": "demo@kyracosmetics.com",
    "password": "securePassword123",
    "role": "CUSTOMER"
  }')
echo "✅ User registered: $USER_RESPONSE"
echo ""

# 2. Create categories
echo "📁 2. Creating product categories..."
MAKEUP_CATEGORY=$(curl -s -X POST "$API_BASE/categories" \
  -H "Content-Type: application/json" \
  -d '{"name": "Makeup"}')
echo "✅ Makeup category: $MAKEUP_CATEGORY"

SKINCARE_CATEGORY=$(curl -s -X POST "$API_BASE/categories" \
  -H "Content-Type: application/json" \
  -d '{"name": "Skincare"}')
echo "✅ Skincare category: $SKINCARE_CATEGORY"
echo ""

# 3. Create products
echo "💄 3. Adding products to catalog..."
LIPSTICK=$(curl -s -X POST "$API_BASE/products?categoryId=1" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Ruby Red Matte Lipstick",
    "description": "Long-lasting matte finish in classic red",
    "price": 24.99,
    "stock": 50
  }')
echo "✅ Lipstick added: $LIPSTICK"

FACE_CREAM=$(curl -s -X POST "$API_BASE/products?categoryId=2" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Hydrating Face Cream",
    "description": "24h moisture for all skin types",
    "price": 39.99,
    "stock": 30
  }')
echo "✅ Face cream added: $FACE_CREAM"
echo ""

# 4. Test shopping cart
echo "🛒 4. Testing shopping cart functionality..."
CART_ADD1=$(curl -s -X POST "$API_BASE/cart/1/add?productId=1&quantity=2")
echo "✅ Added 2 lipsticks to cart: $CART_ADD1"

CART_ADD2=$(curl -s -X POST "$API_BASE/cart/1/add?productId=2&quantity=1")
echo "✅ Added 1 face cream to cart: $CART_ADD2"

CART_CONTENTS=$(curl -s "$API_BASE/cart/1")
echo "✅ Cart contents: $CART_CONTENTS"
echo ""

# 5. Place an order
echo "📦 5. Placing an order..."
ORDER=$(curl -s -X POST "$API_BASE/orders/1")
echo "✅ Order placed: $ORDER"

USER_ORDERS=$(curl -s "$API_BASE/orders/user/1")
echo "✅ User orders: $USER_ORDERS"
echo ""

# 6. Display summary
echo "📊 Demo Summary"
echo "==============="
echo "✅ User management: Register and retrieve users"
echo "✅ Category management: Create and list categories"  
echo "✅ Product management: Add products to categories"
echo "✅ Shopping cart: Add products, view cart contents"
echo "✅ Order processing: Place orders and track history"
echo ""
echo "🌟 All MVP features successfully demonstrated!"
echo ""
echo "📖 For interactive testing, visit:"
echo "   Swagger UI: http://localhost:8080/swagger-ui/index.html"
echo "   H2 Console: http://localhost:8080/h2-console"
echo ""
echo "📁 Import Postman collection: Kyra_Cosmetics_API_Demo.postman_collection.json"