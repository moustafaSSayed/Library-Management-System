# Library Management System API

## Project Description

The Library Management System API is a Spring Boot-based application designed to manage books, patrons, and borrowing records. This API provides RESTful endpoints to handle CRUD operations for books and patrons, as well as borrowing and returning books.

## Features

- **Book Management**: CRUD operations for managing books.
- **Patron Management**: CRUD operations for managing patrons.
- **Borrowing Records**: Track book borrowing and returns.
- **Validation and Error Handling**: Input validation and graceful error handling.
- **Security**: Basic authentication or JWT-based authorization.
- **Logging**: Logging of method calls, exceptions, and performance metrics using AOP.
- **Caching**: Improve performance with caching for frequently accessed data.
- **Transaction Management**: Ensure data integrity with Spring's `@Transactional`.
- **Testing**: Unit tests to validate functionality using JUnit, Mockito, and SpringBootTest.

## API Endpoints

### Book Management

- **GET /api/books**
  - Retrieve a list of all books.

- **GET /api/books/{id}**
  - Retrieve details of a specific book by ID.

- **POST /api/books**
  - Add a new book to the library.

- **PUT /api/books/{id}**
  - Update an existing book's information.

- **DELETE /api/books/{id}**
  - Remove a book from the library.

### Patron Management

- **GET /api/patrons**
  - Retrieve a list of all patrons.

- **GET /api/patrons/{id}**
  - Retrieve details of a specific patron by ID.

- **POST /api/patrons**
  - Add a new patron to the library.

- **PUT /api/patrons/{id}**
  - Update an existing patron's information.

- **DELETE /api/patrons/{id}**
  - Remove a patron from the library.

### Borrowing Records

**POST /api/borrow/{bookId}/patron/{patronId}**
  - Allow a patron to borrow a book

**PUT /api/return/{bookId}/patron/{patronId}**
  - Record the return of a borrowed book by a patron.

## Data Storage

- **Database**: H2
- **Relationships**:
  - One-to-many relationship between books and borrowing records.

## Validation and Error Handling

- Input validation is implemented for all API requests.
- Exceptions are handled gracefully, returning appropriate HTTP status codes and error messages.

## Security

- Basic authentication is used to protect API endpoints.

## Logging

- Logging is implemented using Aspect-Oriented Programming (AOP) to track method calls, exceptions, and performance metrics.

## Caching

- Spring's caching mechanisms are used to cache frequently accessed data to improve system performance.

## Transaction Management

- Declarative transaction management is implemented using Spring's `@Transactional` to ensure data integrity during critical operations.

## Testing

The application includes comprehensive testing to ensure the functionality of the API endpoints and core business logic. Testing is organized into unit and integration tests.

### Unit Testing

- **Frameworks Used**:
  - **JUnit**: For running the test cases.
  - **Mockito**: For mocking dependencies and verifying interactions.

- **Test Coverage**:
  - The unit tests validate the behavior of the `BookController`, `PatronController`, and `BorrowingRecordController` classes.

- **Running Unit Tests**:
  - To run the unit tests, use the following command:
    ```bash
    ./mvnw test
    ```
