# AgroCart - Backend

AgroCart is a dropshipping-based e-commerce backend built using **Spring Boot** and **MongoDB**, offering secure REST APIs, JWT authentication, and modular architecture. This backend powers the AgroCart frontend and supports user management, product browsing, order processing, and more.

---

## 🚀 Tech Stack

- **Java 21**
- **Spring Boot 3.5**
- **Spring Security + JWT**
- **MongoDB**
- **RESTful APIs**
- **Maven**

---

## 📦 Features

- ✅ User Registration & Login (JWT-secured)
- 🔐 Role-Based Access Control (Admin/User)
- 🛒 Cart, Wishlist, Orders, and Payments
- 📦 Product Management (CRUD)
- 💬 Review & Rating System
- 📊 Admin Dashboard APIs
- 🧩 CORS Configured for Frontend Integration

---

## 📁 Project Structure

src/
└── main/
└── java/com/agrocart/web/
├── controller/
├── service/
├── model/
├── repository/
├── dto/
└── config/

## 🔧 Setup Instructions

1. Clone the repo:
   ```bash
   git clone https://github.com/harshitshukla9752/AgroCart.git
   cd AgroCart
   
2. Update MongoDB connection in src/main/resources/application.properties:
   '''bash
   spring.data.mongodb.uri=mongodb://localhost:27017/agrocart
   
3. Build and run:
   '''bash
   mvn clean install
   mvn spring-boot:run

📫 API Base URL

http://localhost:8080/api


