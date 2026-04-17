# 🎓 Student Management APP - Spring Boot + Docker + CI/CD

A REST API built with Spring Boot, containerized with Docker, and automated with GitHub Actions CI/CD pipeline.

---

## 🧱 Tech Stack

| Layer        | Tech                        |
|--------------|-----------------------------|
| Framework    | Spring Boot 3.2             |
| Language     | Java 17                     |
| Database     | H2 (in-memory)              |
| ORM          | Spring Data JPA + Hibernate |
| Boilerplate  | Lombok                      |
| Monitoring   | Spring Actuator             |
| Container    | Docker (multi-stage build)  |
| CI/CD        | GitHub Actions              |
| Image Registry | Docker Hub                |

---

## 🚀 Run the Application

### Option 1 — Pull from Docker Hub (easiest, no code needed)
```bash
docker pull nikstatus07/mystudentapp:latest
docker run -p 8080:8080 nikstatus07/mystudentapp:latest
```

### Option 2 — Run with Docker after cloning
```bash
git clone https://github.com/yoyounik/Student_Management_APP_Dockerization.git
cd Student_Management_APP_Dockerization
docker build -t mystudentapp .
docker run -p 8080:8080 mystudentapp
```

### Option 3 — Run locally with Maven
```bash
git clone https://github.com/yoyounik/Student_Management_APP_Dockerization.git
cd Student_Management_APP_Dockerization
mvn spring-boot:run
```

App starts at: **http://localhost:8080**

---

## 📡 API Endpoints

Base URL: `http://localhost:8080/api/students`

| Method | Endpoint             | Description          |
|--------|----------------------|----------------------|
| GET    | `/`                  | Get all students     |
| GET    | `/{id}`              | Get student by ID    |
| GET    | `/department/{dept}` | Get by department    |
| POST   | `/`                  | Create new student   |
| PUT    | `/{id}`              | Update student       |
| DELETE | `/{id}`              | Delete student       |

---

## 🧪 Sample Requests

```bash
# Get all students (4 pre-loaded on startup)
curl http://localhost:8080/api/students

# Get student by ID
curl http://localhost:8080/api/students/1

# Get by department
curl http://localhost:8080/api/students/department/CSE

# Create new student
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

URL: **http://localhost:8080/h2-console**
```
JDBC URL:  jdbc:h2:mem:studentdb
Username:  sa
Password:  (leave blank)
```

---

## ⚙️ CI/CD Pipeline

This project uses **GitHub Actions** for automated CI/CD.

### How it works:
```
Push code to main branch
        ↓
GitHub Actions triggers automatically
        ↓
Checks out code on Ubuntu machine
        ↓
Logs into Docker Hub
        ↓
Builds Docker image (multi-stage — Maven builds jar inside Docker)
        ↓
Pushes image to Docker Hub
```

### Workflow file: `.github/workflows/ci.yml`

Every push to `main` branch automatically builds and pushes a fresh Docker image to Docker Hub — no manual steps needed.

---

## 🐳 Docker Details

- **Multi-stage Dockerfile** — Stage 1 uses Maven to build jar, Stage 2 uses lightweight JRE to run it
- **No local Maven needed** — entire build happens inside Docker
- **Small final image** — only JRE (not JDK) + jar, no source code or build tools
- **Health check** included — works with Kubernetes liveness probes

---

## 🔗 Docker Hub

Image available at:
```
docker pull yoyounik/mystudentapp:latest
```

---

## 📁 Project Structure

```
├── src/
│   └── main/java/com/demo/app/
│       ├── Application.java
│       ├── DataLoader.java
│       ├── controller/
│       │   ├── StudentController.java
│       │   └── GlobalExceptionHandler.java
│       ├── model/
│       │   └── Student.java
│       ├── repository/
│       │   └── StudentRepository.java
│       └── service/
│           └── StudentService.java
├── .github/
│   └── workflows/
│       └── ci.yml
├── Dockerfile
├── docker-compose.yml
└── pom.xml
```
