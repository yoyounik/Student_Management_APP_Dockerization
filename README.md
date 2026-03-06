# 🎓 Student Management API — Spring Boot + Docker

A simple, clean REST API built with Spring Boot, ready to Dockerize.  
Perfect for DevOps learning and interview portfolios.

---

## 🧱 Tech Stack

| Layer        | Tech                         |
|--------------|------------------------------|
| Framework    | Spring Boot 3.2              |
| Language     | Java 17                      |
| Database     | H2 (in-memory, zero setup)   |
| ORM          | Spring Data JPA + Hibernate  |
| Boilerplate  | Lombok                       |
| Monitoring   | Spring Actuator              |
| Container    | Docker (multi-stage build)   |

---

## 🚀 Run Locally (without Docker)

```bash
# Make sure Java 17+ and Maven are installed
mvn spring-boot:run
```

App starts at: http://localhost:8080

---

## 🐳 Run with Docker

### Option 1 — Docker Compose (recommended)
```bash
docker-compose up --build
```

### Option 2 — Manual Docker commands
```bash
# Build image
docker build -t student-api .

# Run container
docker run -p 8080:8080 student-api

# Run in background
docker run -d -p 8080:8080 --name student-api student-api

# View logs
docker logs student-api

# Stop container
docker stop student-api

# Remove container
docker rm student-api
```

---

## 📡 API Endpoints

Base URL: `http://localhost:8080/api/students`

| Method | Endpoint              | Description               |
|--------|-----------------------|---------------------------|
| GET    | `/`                   | Get all students          |
| GET    | `/{id}`               | Get student by ID         |
| GET    | `/department/{dept}`  | Get students by dept      |
| POST   | `/`                   | Create new student        |
| PUT    | `/{id}`               | Update student            |
| DELETE | `/{id}`               | Delete student            |

---

## 🧪 Sample cURL Requests

```bash
# Get all students (4 pre-loaded)
curl http://localhost:8080/api/students

# Get by ID
curl http://localhost:8080/api/students/1

# Get by department
curl http://localhost:8080/api/students/department/CSE

# Create student
curl -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -d '{"name":"Karan Mehta","email":"karan@demo.com","department":"CSE","age":22}'

# Update student
curl -X PUT http://localhost:8080/api/students/1 \
  -H "Content-Type: application/json" \
  -d '{"name":"Rahul S","email":"rahul@demo.com","department":"CSE","age":22}'

# Delete student
curl -X DELETE http://localhost:8080/api/students/1

# Health check
curl http://localhost:8080/actuator/health
```

---

## 🗄️ H2 Database Console

Visit: http://localhost:8080/h2-console  
- JDBC URL: `jdbc:h2:mem:studentdb`  
- Username: `sa`  
- Password: *(leave blank)*

---

## 🔍 Project Structure

```
src/
├── main/java/com/demo/app/
│   ├── Application.java          # Entry point
│   ├── DataLoader.java           # Seed data on startup
│   ├── controller/
│   │   ├── StudentController.java       # REST endpoints
│   │   └── GlobalExceptionHandler.java  # Error handling
│   ├── model/
│   │   └── Student.java          # JPA Entity
│   ├── repository/
│   │   └── StudentRepository.java # DB queries
│   └── service/
│       └── StudentService.java   # Business logic
└── resources/
    └── application.properties    # Config
```

---

## 💡 DevOps Interview Talking Points

- **Multi-stage Dockerfile** → smaller final image (JRE only, not JDK)
- **HEALTHCHECK** in Dockerfile → works with Docker Swarm / Kubernetes liveness probes
- **Spring Actuator** → `/actuator/health` endpoint for container orchestration
- **Layer caching** → `COPY pom.xml` before source code speeds up rebuilds
- **docker-compose** → simulates multi-service deployments
- **Stateless app** → easy to scale horizontally

---

## 📦 Push to Docker Hub (for DevOps practice)

```bash
docker tag student-api your-dockerhub-username/student-api:v1
docker push your-dockerhub-username/student-api:v1
```
