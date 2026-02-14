# Kyra Cosmetics - E-commerce Platform

**Student:** Ana Cîmpeanu  
**Domain Ales:** Cosmetics E-commerce Platform  
**Tehnologie:** Spring Boot + React (Frontend)

---

## 📋 I. Business Requirements și MVP Features

### 10 Business Requirements

1. **User Registration & Authentication** - Utilizatorii pot crea conturi cu validare email și parolă
2. **Product Catalog Management** - Adminii pot adăuga, edita și șterge produse cosmetice cu categorii
3. **Shopping Cart Functionality** - Utilizatorii pot adăuga/șterge produse din coșul de cumpărături
4. **Order Processing** - Utilizatorii pot plasa comenzi și urmări statusul acestora
5. **Inventory Management** - Sistemul urmărește stocurile și previne supravânzarea
6. **Category Organization** - Produsele sunt organizate pe categorii (makeup, skincare, fragrances)
7. **User Profile Management** - Utilizatorii pot vizualiza și actualiza profilul lor
8. **Price Calculation** - Sistemul calculează prețuri totale pentru coș și comenzi
9. **Order History** - Utilizatorii pot vizualiza istoricul comenzilor
10. **Product Search** - Utilizatorii pot căuta produse după nume sau categorie

### 5 Main MVP Features

1. **User Management** - Înregistrare, autentificare, gestionare profil și roluri (USER/ADMIN)
2. **Product Catalog** - CRUD operații pentru produse, categorii și gestionare inventar
3. **Shopping Cart** - Adăugare/ștergere produse, gestionare cantități, persistență per utilizator
4. **Order Processing** - Creare comenzi din coș, tracking status, istoric comenzi
5. **Administrative Functions** - Gestionare produse, categorii și monitorizare comenzi

---

## 🏗️ II. Arhitectura Aplicației Spring Boot

### REST API Endpoints (5+ endpoints per feature)

#### User Management (`/api/users`)
- `POST /api/users/register` - Înregistrare utilizator nou
- `GET /api/users` - Obține toți utilizatorii
- `GET /api/users/{id}` - Obține utilizator după ID
- `PUT /api/users/{id}` - Actualizează profil utilizator
- `DELETE /api/users/{id}` - Șterge utilizator

#### Product Management (`/api/products`)
- `POST /api/products` - Creare produs nou
- `GET /api/products` - Obține toate produsele
- `GET /api/products/{id}` - Obține produs după ID
- `PUT /api/products/{id}` - Actualizează produs
- `DELETE /api/products/{id}` - Șterge produs

#### Category Management (`/api/categories`)
- `POST /api/categories` - Creare categorie nouă
- `GET /api/categories` - Obține toate categoriile
- `GET /api/categories/{id}` - Obține categorie după ID
- `PUT /api/categories/{id}` - Actualizează categorie
- `DELETE /api/categories/{id}` - Șterge categorie

#### Cart Management (`/api/cart`)
- `GET /api/cart/{userId}` - Obține coșul utilizatorului
- `POST /api/cart/{userId}/add` - Adaugă produs în coș
- `PUT /api/cart/{userId}/update` - Actualizează cantitate în coș
- `DELETE /api/cart/{userId}/remove` - Șterge produs din coș
- `DELETE /api/cart/{userId}/clear` - Golește coșul

#### Order Management (`/api/orders`)
- `POST /api/orders/{userId}` - Plasează comandă nouă
- `GET /api/orders/user/{userId}` - Obține comenzile utilizatorului
- `GET /api/orders/{orderId}` - Obține detalii comandă
- `PATCH /api/orders/{orderId}/status` - Actualizează status comandă
- `GET /api/orders` - Obține toate comenzile (Admin)

### Services (1 per feature)

1. **UserService** - Business logic pentru utilizatori (înregistrare, autentificare, gestionare profil)
2. **ProductService** - Business logic pentru produse (CRUD, validări, gestionare stoc)
3. **CategoryService** - Business logic pentru categorii (organizare, asociere produse)
4. **CartService** - Business logic pentru coșul de cumpărături (calcule, validări stoc)
5. **OrderService** - Business logic pentru comenzi (procesare, calcule totale, istoric)

### Repositories (1 per entity)

1. **UserRepository** - Acces date utilizatori
2. **ProductRepository** - Acces date produse
3. **CategoryRepository** - Acces date categorii
4. **CartRepository** - Acces date coșuri
5. **CartItemRepository** - Acces date elemente coș
6. **OrderRepository** - Acces date comenzi
7. **OrderItemRepository** - Acces date elemente comenzi

### Database Entities (6+ entități, 4+ relații)

**7 Entități:**
1. **User** - Conturi utilizatori cu autentificare
2. **Category** - Categorii de produse
3. **Product** - Produse cosmetice
4. **Cart** - Coș de cumpărături
5. **CartItem** - Elemente din coș
6. **Order** - Comenzi clienți
7. **OrderItem** - Elemente din comenzi

**Relații între entități:**
- `User` ↔ `Cart` (One-to-One)
- `User` ↔ `Order` (One-to-Many)
- `Category` ↔ `Product` (One-to-Many)
- `Cart` ↔ `CartItem` (One-to-Many)
- `Order` ↔ `OrderItem` (One-to-Many)
- `Product` ↔ `CartItem` (Many-to-One)
- `Product` ↔ `OrderItem` (Many-to-One)

### Validare Date (POJO classes + Custom Constraints)

Toate clasele POJO includ validări folosind **Bean Validation API** și constraint-uri custom:
- `@NotNull`, `@NotEmpty`, `@Email` pentru validări standard
- `@Min`, `@Max`, `@DecimalMin` pentru validări numerice
- `@Pattern` pentru validări regex
- Constraint-uri custom pentru reguli de business specifice
- Error handling cu mesaje clare și detaliate

### Unit Tests

**20+ teste implementate:**
- **Controller Tests** - Teste pentru toate endpoint-urile REST (UserController, ProductController, CategoryController, CartController, OrderController)
- **Service Tests** - Teste pentru logica de business din fiecare serviciu
- **Repository Tests** - Teste pentru operațiile de persistență

**Toate testele pasează** - Vezi rezultatele în `artifacts/surefire-reports/`

---

## 📚 III. Documentație API

### Swagger/OpenAPI Documentation

Aplicația include documentație completă API folosind **Swagger/OpenAPI 3.0**:

- **Swagger UI (Interactive):** `http://localhost:8080/swagger-ui/index.html`
- **OpenAPI JSON:** `http://localhost:8080/v3/api-docs`
- **Export documentație:** [`swagger-api-docs.json`](swagger-api-docs.json)

Documentația cuprinde:
- Toate endpoint-urile REST cu descrieri detaliate
- Scheme de request/response pentru fiecare endpoint
- Exemple de date pentru testare
- Informații despre validări și constrangeri
- Coduri de status HTTP și mesaje de eroare

---

## 🧪 IV. Testing & Demonstrație Funcționalități

### Postman Collection

Colecție Postman completă pentru demonstrarea funcționalităților:

📁 **File:** [`Kyra_Cosmetics_API_Demo.postman_collection.json`](Kyra_Cosmetics_API_Demo.postman_collection.json)

**Cum se importă:**
1. Deschide Postman
2. Click pe "Import"
3. Selectează fișierul collection
4. Pornește aplicația Spring Boot
5. Rulează request-urile în ordine

**Test Flow complet:**
1. User Management (Register, Get Users)
2. Category Creation (Create Makeup, Skincare)
3. Product Management (Add products)
4. Shopping Cart (Add/Remove items)
5. Order Processing (Place order, Track status)

### Swagger UI (Alternativă Postman)

Poți testa toate endpoint-urile interactiv:
- URL: `http://localhost:8080/swagger-ui/index.html`
- Interface interactivă cu toate endpoint-urile
- Exemple și documentație integrată

---

## 🚀 V. Rulare Aplicație

### Pornire Backend

```bash
cd Project
./mvnw spring-boot:run
```

### Acces URL-uri

- **API Base URL:** `http://localhost:8080/api`
- **Swagger UI:** `http://localhost:8080/swagger-ui/index.html`
- **H2 Database Console:** `http://localhost:8080/h2-console`

### Rulare Teste

```bash
cd Project
./mvnw test
```

**Rezultate teste:** Vezi rapoarte în `artifacts/surefire-reports/`

---

## 📦 VI. Structura Proiect

```
Project-Web-Java/
├── Project/                          # Backend Spring Boot
│   ├── src/main/java/
│   │   └── com/example/Project/kyra_cosmetics/
│   │       ├── controller/          # REST Controllers
│   │       ├── service/             # Business Logic
│   │       ├── repository/          # Data Access
│   │       ├── model/               # Entities & DTOs
│   │       └── config/              # Configuration
│   ├── src/test/java/               # Unit Tests
│   └── pom.xml                      # Maven dependencies
├── kyra-frontend/                    # Frontend React (Bonus)
├── swagger-api-docs.json            # Export Swagger Documentation
├── Kyra_Cosmetics_API_Demo.postman_collection.json
├── artifacts/surefire-reports/      # Test Reports
└── README.md                        # Această documentație
```

---

## ✅ Cerințe Îndeplinite

- ✅ **No compilation errors** - Proiectul compilează fără erori
- ✅ **10 Business Requirements** - Definite și implementate
- ✅ **5 MVP Features** - Implementate cu toate funcționalitățile
- ✅ **REST Endpoints** - 5+ endpoints per feature (25+ total)
- ✅ **Services** - 1 service per feature (5 services)
- ✅ **Repositories** - 1 repository per entity (7 repositories)
- ✅ **Unit Tests** - Toate endpoint-urile și serviciile testate
- ✅ **Database** - 6+ entități, 4+ relații
- ✅ **POJO Validation** - Bean Validation + Custom Constraints
- ✅ **Swagger Documentation** - Documentație completă export inclusă
- ✅ **API Testing** - Postman Collection + Swagger UI
- ✅ **Clean Code** - Respectă principiile JAVA coding standards și DRY
- ✅ **All Tests Passed** - Toate testele trec cu succes
