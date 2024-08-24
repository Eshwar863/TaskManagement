# Task Management System

## Overview

The Task Management System is a Spring Boot application designed to manage user tasks efficiently. It includes features for user authentication, task creation, retrieval, and deletion, providing a robust backend API for task management.

## Table of Contents

- [Overview](#overview)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Setup and Installation](#setup-and-installation)
- [API Endpoints](#api-endpoints)
- [Exception Handling](#exception-handling)

## Technologies Used

- **Programming Language:** Java
- **Framework:** Spring Boot
- **Database:** MySQL
- **Tools:** IntelliJ IDEA, Postman
- **Version Control:** Git

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   ├── com/
│   │   │   ├── TaskManagement/
│   │   │   │   ├── Controller/
│   │   │   │   │   ├── AuthController.java
│   │   │   │   │   ├── TaskController.java
│   │   │   │   ├── Dto/
│   │   │   │   │   ├── LoginDto.java
│   │   │   │   │   ├── TaskDto.java
│   │   │   │   │   ├── UserDto.java
│   │   │   │   ├── Entity/
│   │   │   │   │   ├── Task.java
│   │   │   │   │   ├── UserRole.java
│   │   │   │   │   ├── Users.java
│   │   │   │   ├── Exceptions/
│   │   │   │   │   ├── APIException.java
│   │   │   │   │   ├── TaskNotFound.java
│   │   │   │   │   ├── UserNotFound.java
│   │   │   │   ├── Repository/
│   │   │   │   │   ├── TaskRepo.java
│   │   │   │   │   ├── UserRepo.java
│   │   │   │   ├── Security/
│   │   │   │   │   ├── SecurityConfig.java
│   │   │   │   ├── Service/
│   │   │   │   │   ├── JwtService.java
│   │   │   │   │   ├── TaskService.java
│   │   │   │   │   ├── UserService.java
│   │   │   │   ├── ServiceImpl/
│   │   └── resources/
│   │       ├── application.properties
```

## Setup and Installation

### Prerequisites

- Java JDK 8 or higher
- Maven
- MySQL or any other preferred database

### Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/Eshwar863/TaskManagement.git
   cd task-management-system
   ```

2. **Configure the database**
   - Update `src/main/resources/application.properties` with your database configurations.
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/yourdatabase
   spring.datasource.username=yourusername
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   ```

3. **Build and run the application**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

## API Endpoints

### TaskController

- **Create Task**
  - `POST /api/task/{id}/task`
  - Request Body: `TaskDto`
  - Response: `TaskDto`
  
- **Get All Tasks**
  - `GET /api/task/{userid}/tasks`
  - Response: `List<TaskDto>`
  
- **Get Specific Task**
  - `GET /api/task/{userid}/task/{taskid}`
  - Response: `TaskDto`
  
- **Delete Task**
  - `DELETE /api/task/{userid}/task/{taskid}`
  - Response: `String`

### Example Request

```bash
curl -X POST "http://localhost:8080/api/task/1/task" -H "Content-Type: application/json" -d '{"title":"Sample Task", "description":"This is a sample task."}'
```

## Exception Handling

The application includes custom exceptions to handle specific error cases:

- `APIException`
- `TaskNotFound`
- `UserNotFound`

These exceptions are used to provide meaningful error messages and appropriate HTTP status codes.
