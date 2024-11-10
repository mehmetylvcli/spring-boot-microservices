# Spring Boot Microservices
This project is a microservice-based application developed using Java Spring Boot. It integrates PostgreSQL and MongoDB for data management across services. The application is containerized using Docker, making it easy to deploy and scale.

## Technologies Used

- **Java Spring Boot:** Framework for building the microservices.
- **PostgreSQL:** Relational database for managing user and product services.
- **MongoDB:** NoSQL database used by the tax calculation service to store transaction logs.
- **Docker:** Containerization of services and databases for easy deployment and scalability.
- **Microservices Architecture:** Each functional module is designed as an independent microservice, communicating via REST APIs.

## Services

- **User Service:** Manages user accounts (registration, login, profile management).
- **Product Service:** Handles product management (listing, categories, inventory).
- **Tax Calculation Service:** Calculates taxes and stores transaction logs in MongoDB.

## Setup and Installation

### Prerequisites

- Docker
- Docker Compose

### Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/mehmetylvcli/spring-boot-microservices.git
   ```

2. Navigate to the project directory:
   ```bash
   cd spring-boot-microservices

3. Build and start the services using Docker Compose::
   ```bash
   docker-compose up --build
4. The application will be available at the configured port (default ports for services are defined in docker-compose.yml).

## API Usage

### **User Service**

1. **Register a New User**
    - **Endpoint:** `POST /api/auth/signup`
    - **Description:** Registers a new user in the system.
    - **Request Body:**
      ```json
      {
        "username": "string",
        "password": "string"
      }
      ```
    - **Response:**
        - `200 OK` on successful registration.
        - `400 Bad Request` if the user data is invalid or already exists.

   Example request:
   ```bash
   curl -X POST http://localhost:8081/api/auth/signup \
        -H "Content-Type: application/json" \
        -d '{"username": "test", "password": "securepassword"}'
   ```

2. **Login**
    - **Endpoint:** `POST /api/auth/signin`
    - **Description:** Logs in a user and returns a JWT token for authentication.
    - **Request Body:**
      ```json
      {
        "username": "string",
        "password": "string"
      }
      ```
    - **Response:**
        - `200 OK` with a JWT token on successful login.
        - `401 Unauthorized` if the credentials are invalid.

   Example request:
   ```bash
   curl -X POST http://localhost:8081/api/auth/signin \
        -H "Content-Type: application/json" \
        -d '{"username": "test", "password": "securepassword"}'
   ```

### **Product Service**

1. **Get Product by ID**
    - **Endpoint:** `POST /api/products/get/{id}`
    - **Description:** Retrieves details of a specific product by its ID.
      ```
    - **Response:**
        - `200 OK` with product details.
        - `404 Not Found` if the product is not found.

   Example request:
   ```bash
   curl -X GET 'http://localhost:8081/api/products/get/1'
   ```

2. **Add a New Product**
    - **Endpoint:** `POST /api/products/add`
    - **Description:** Adds a new product to the system.
    - **Authorization:** Bearer token required (JWT).
    - **Request Body:**
      ```json
      {
        "name": "string",
        "price": "number",
        "taxRate": "number"
      }
      ```
    - **Response:**
        - `201 Created` on successful product addition.
        - `400 Bad Request` if the product data is invalid.

   Example request using `curl`:
   ```bash
   curl --location 'http://localhost:8081/api/products/add' \
   --header 'Authorization: Bearer <your-jwt-token>' \
   --header 'Content-Type: application/json' \
   --data '{
       "name": "ürün1",
       "price": 1500.0,
       "taxRate": 18
   }'
   ```

3. **Update a Product**
    - **Endpoint:** `PUT /api/products/{id}`
    - **Description:** Updates an existing product by its ID.
    - **Authorization:** Bearer token required (JWT).
    - **Request Body:**
      ```json
      {
        "id": "number",
        "name": "string",
        "price": "number",
        "taxRate": "number"
      }
      ```
    - **Response:**
        - `200 OK` on successful product update.
        - `404 Not Found` if the product is not found.

   Example request using `curl`:
   ```bash
   curl --location --request PUT 'http://localhost:8081/api/products/1' \
   --header 'Authorization: Bearer <your-jwt-token>' \
   --header 'Content-Type: application/json' \
   --data '{
       "id": 1,
       "name": "ürün1000",
       "price": 1500.0,
       "taxRate": 10.0
   }'
   ```


4. **Delete a Product**
    - **Endpoint:** `DELETE /api/products/{id}`
    - **Description:** Deletes a product by its ID.
    - **Authorization:** Bearer token required (JWT).
    - **Response:**
        - `200 OK` on successful deletion.
        - `404 Not Found` if the product is not found.

   Example request using `curl`:
   ```bash
   curl --location --request DELETE 'http://localhost:8081/api/products/4' \
   --header 'Authorization: Bearer <your-jwt-token>'
   ```



### **Tax Calculation Service**

1. **Get Tax Calculation**
    - **Endpoint:** `GET /api/tax-calculate/{id}`
    - **Description:** Retrieves the tax calculation details by transaction ID.
    - **Response:**
        - `200 OK` with tax details.
        - `404 Not Found` if the transaction is not found.

   Example request using `curl`:
   ```bash
   curl --location 'http://localhost:8082/api/tax-calculate/1'
   ```



