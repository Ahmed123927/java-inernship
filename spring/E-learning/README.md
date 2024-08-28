
# E-Learning Platform

This is an E-Learning Platform built using Java and Spring Boot. It allows users to access various courses, upload content, track progress, and more.

## Features

- User registration and authentication
- Course management (creation, updating, deletion)
- Content upload (videos, PDFs, images)
- Progress tracking for students
- Role-based access (students, instructors, admins)

## Getting Started

### Prerequisites

- Java 17 or higher
- PostgreSQL database
- IDE (IntelliJ IDEA)

### Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/Ahmed123927/java-internship/tree/main/spring/E-learning
Navigate to the project directory:
  ```bash
cd E-learning
```



## API Documentation

For detailed API documentation, visit: [API Documentation](https://documenter.getpostman.com/view/33023656/2sAXjGduK5)

## Usage

- **Register a new user:** Use the `/register` endpoint to create a new account.
- **Login:** Authenticate using the `/login` endpoint to receive a token for accessing protected routes.
- **Manage Courses:**
  - Instructors can create, update, and delete courses.
  - Students can enroll in courses and track their progress.
- **Upload Content:** Upload videos, PDFs, or images to the courses via the content upload endpoints.

