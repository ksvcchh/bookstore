# 📚 Online Library Management System (Backend API)

![Spring Boot Badge](https://img.shields.io/badge/Spring%20Boot-3.2.5-brightgreen)
![Java Version](https://img.shields.io/badge/Java-17-blue)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-via%20Docker-blue)
![Status](https://img.shields.io/badge/Status-Under%20Development-yellowgreen)

---

## 📖 Project Description

This project is the backend API for an Online Library Management System, built using Spring Boot. It provides a robust set of RESTful endpoints to manage books, authors, users, and the borrowing/returning processes within a library environment. The system is designed with a layered architecture (Controller, Service, Repository) to ensure separation of concerns, maintainability, and testability.

## ✨ Features

Currently implemented and planned features include:

*   **Book Management:**
    *   Create, Read (single, all), Update, Delete operations for books.
    *   Validation of incoming book data.
*   **User Management:** (Basic structure for now, will be expanded with Spring Security)
*   **Author Management:** (Planned)
*   **Borrowing & Returning System:** (Planned)
*   **Search & Filtering:** (Planned)
*   **User Authentication & Authorization:** (Planned using Spring Security and JWT)
*   **Global Exception Handling:** Robust error responses for various scenarios.

## 🚀 Technologies Used

*   **Spring Boot:** Framework for building robust, stand-alone, production-grade Spring applications.
*   **Java 21:** The core programming language.
*   **Spring Data JPA:** For easy data access and ORM (Object-Relational Mapping) with Hibernate.
*   **PostgreSQL:** The relational database for data persistence, run via Docker.
*   **Lombok:** Reduces boilerplate code (getters, setters, constructors).
*   **Maven:** Dependency management and build automation tool.
*   **Docker:** For containerizing the PostgreSQL database, ensuring a consistent development environment.
*   **JUnit 5 & Mockito:** For unit and integration testing.
*   **SpringDoc OpenAPI (Swagger):** (Planned) For automatic API documentation generation.

## ⚙️ Setup and Installation

Follow these steps to get the project up and running on your local machine.

### Prerequisites

*   **Java Development Kit (JDK) 17 or higher**
*   **Maven** (version 3.6+)
*   **Docker Desktop** (or Docker Engine for Linux)

### 1. Database Setup (PostgreSQL with Docker)

First, we'll start a PostgreSQL database instance using Docker. This ensures a clean and isolated database environment.

Open your terminal or command prompt and run the following command:

```bash
docker run --name library-postgres \
  -e POSTGRES_DB=library_db \
  -e POSTGRES_USER=library_user \
  -e POSTGRES_PASSWORD=library_password \
  -p 5432:5432 \
  -v library-data:/var/lib/postgresql/data \
  -d postgres:14-alpine
```

*   **`library-postgres`**: The name of your Docker container.
*   **`library_db`**: The name of the database created inside PostgreSQL.
*   **`library_user`**: The username for accessing the database.
*   **`library_password`**: The password for the database user.
*   **`5432:5432`**: Maps the container's port 5432 to your host's port 5432.
*   **`library-data`**: A Docker volume for persistent data storage. Your data will not be lost if you stop/start the container.
*   **`postgres:14-alpine`**: The PostgreSQL Docker image (version 14, lightweight Alpine variant).

**Useful Docker Commands:**

*   To check if the container is running: `docker ps`
*   To stop the container: `docker stop library-postgres`
*   To start a stopped container: `docker start library-postgres`
*   To remove the container (after stopping): `docker rm library-postgres`
*   To remove the data volume (be careful, this deletes all data!): `docker volume rm library-data`

### 2. Backend Application Setup

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/ksvcchh/bookstore
    cd bookstore
    ```

2.  **Configure `application.properties`:**
    Navigate to `src/main/resources/application.properties` and ensure your database connection details match those used in the Docker command:

    ```properties
    # DataSource Settings:
    spring.datasource.url=jdbc:postgresql://localhost:5432/library_db
    spring.datasource.username=library_user
    spring.datasource.password=library_password
    spring.datasource.driver-class-name=org.postgresql.Driver

    # JPA/Hibernate Settings:
    spring.jpa.hibernate.ddl-auto=update # Creates/updates tables based on entities (dev only)
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true

    # Server Port
    server.port=8080
    ```

3.  **Build and Run the application:**
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```
    The application should start on `http://localhost:8080`.

## 💻 API Endpoints

The API base URL is `http://localhost:8080/api/v1`.

### 📚 Book Endpoints (`/api/v1/books`)

**Note on DTOs:** For `POST` and `PUT` requests, we use `BookRequest` DTOs to encapsulate incoming data, which might differ from the internal `Book` entity. Responses will often use `BookResponse` DTOs.

| Method | Endpoint                    | Description                                         | Request Body (Example)             | Response Body (Example)          |
| :----- | :-------------------------- | :-------------------------------------------------- | :--------------------------------- | :------------------------------- |
| `GET`  | `/api/v1/books`             | Retrieve a list of all books.                       | `None`                             | `[ { "id": 1, ... }, ... ]`      |
| `GET`  | `/api/v1/books/{id}`        | Retrieve a single book by its ID.                   | `None`                             | `{ "id": 1, ... }`               |
| `POST` | `/api/v1/books`             | Create a new book.                                  | See `BookRequest` below            | `{ "id": 1, ... }`               |
| `PUT`  | `/api/v1/books/{id}`        | Update an existing book by its ID.                  | See `BookRequest` below            | `{ "id": 1, ... }`               |
| `DELETE`| `/api/v1/books/{id}`       | Delete a book by its ID.                            | `None`                             | `No Content` (HTTP 204)          |

---

#### `BookRequest` DTO Example (for `POST` and `PUT` /books)

```json
{
  "isbn": "978-0345391803",
  "title": "The Hitchhiker's Guide to the Galaxy",
  "publicationDate": "1979-10-12",  // YYYY-MM-DD format
  "publisher": "Harmony Books",
  "totalCopies": 10,
  "authorId": "a1b2c3d4-e5f6-7890-1234-567890abcdef",
  "coverImage": "https://example.com/hitchhiker.jpg",
  "genreIds": ["b1c2d3e4-f5a6-7890-1234-567890abcdef", "c2d3e4f5-a6b7-8901-2345-67890abcdef0"] // List of UUIDs for associated genres
}
```

---

#### Example `curl` Commands:

**1. Create a New Book (POST)**

```bash
curl -X POST \
  http://localhost:8080/api/v1/books \
  -H 'Content-Type: application/json' \
  -d '{
    "isbn": "978-0345339683",
    "title": "The Hobbit",
    "publicationDate": "1937-09-21",
    "publisher": "George Allen & Unwin",
    "totalCopies": 5,
    "authorId": "a1b2c3d4-e5f6-7890-1234-567890abcdef",                     
    "coverImage": "https://example.com/hobbit.jpg",
    "genreIds": ["b1c2d3e4-f5a6-7890-1234-567890abcdef"]                  
  }'
```

**2. Get All Books (GET)**

```bash
curl http://localhost:8080/api/v1/books
```

**3. Get Book by ID (GET)**

```bash
curl http://localhost:8080/api/v1/books/3f8e7d2c-1a9b-4c5d-6e7f-890123456789
```

**4. Update a Book (PUT)**

```bash
curl -X PUT \
  http://localhost:8080/api/v1/books/3f8e7d2c-1a9b-4c5d-6e7f-890123456789 \
  -H 'Content-Type: application/json' \
  -d '{
    "isbn": "978-0345339683",
    "title": "The Hobbit (Revised Edition)",
    "publicationDate": "1937-09-21",
    "publisher": "George Allen & Unwin",
    "totalCopies": 6,  # Updated!
    "authorId": "a1b2c3d4-e5f6-7890-1234-567890abcdef",
    "coverImage": "https://example.com/hobbit_revised.jpg",
    "genreIds": ["b1c2d3e4-f5a6-7890-1234-567890abcdef", "c2d3e4f5-a6b7-8901-2345-67890abcdef0"] # Updated!
  }'
```

**5. Delete a Book (DELETE)**

```bash
curl -X DELETE http://localhost:8080/api/v1/books/3f8e7d2c-1a9b-4c5d-6e7f-890123456789
```

---

## 🛣️ Roadmap / Future Enhancements

*   **Spring Security:** Implement JWT-based authentication and role-based authorization for users (`LIBRARIAN`, `MEMBER`).
*   **Author Management:** Full CRUD operations for authors and linking them to books.
*   **User Profiles:** Detailed user profiles beyond basic authentication.
*   **Borrowing & Returning System:**
    *   API endpoints for users to borrow and return books.
    *   Logic for tracking available copies and due dates.
    *   Handling overdue books and fine calculations.
*   **Book Reservations:** Allow users to reserve books that are currently out of stock.
*   **Search and Filter:** Implement advanced search capabilities (by title, author, ISBN, genre) and pagination for results.
*   **Admin Dashboard:** Specific endpoints for librarian/admin actions (e.g., viewing all borrowings).
*   **Image Uploads:** Integrate with cloud storage (e.g., AWS S3) for book cover images.
*   **Robust Error Handling:** More specific custom exceptions and error messages.
*   **Testing:** Comprehensive unit, integration, and end-to-end tests.
*   **Docker Compose:** For orchestrating the application and database together.
*   **CI/CD Pipeline:** Automate builds, tests, and deployments.