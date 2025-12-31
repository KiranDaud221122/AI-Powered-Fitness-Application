# 🏋️‍♂️ AI-Powered Fitness Application

<div align="center">

![Java](https://img.shields.io/badge/Java-17+-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=for-the-badge&logo=spring)
![Microservices](https://img.shields.io/badge/Architecture-Microservices-blue?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-In%20Development-yellow?style=for-the-badge)

**Project: AI-Driven Fitness Recommendation Platform (Microservices Architecture)**

Designed and developed a Spring Boot–based microservices system consisting of User, Activity, Recommendation, AI, and API Gateway services. Implemented service discovery using Eureka Server, centralized configuration using Spring Cloud Config Server, and API routing with Spring Cloud Gateway (WebFlux).

Integrated authentication and authorization using Keycloak, synchronizing internal user IDs with Keycloak IDs and storing mappings in PostgreSQL. Implemented reactive communication using WebClient with load balancing and event-driven communication between Activity and AI services using Kafka.

The system provides personalized activity and user recommendations while ensuring scalability, security, and loose coupling between services.

[Features](#-key-features) • [Architecture](#-system-architecture) • [Tech Stack](#-tech-stack) • [Getting Started](#-getting-started) • [API Documentation](#-api-documentation)

</div>

---

## 📋 Table of Contents

- [Overview](#-overview)
- [Key Features](#-key-features)
- [System Architecture](#-system-architecture)
- [Tech Stack](#-tech-stack)
- [Microservices Overview](#-microservices-overview)
- [Project Structure](#-project-structure)
- [Getting Started](#-getting-started)
- [API Documentation](#-api-documentation)
- [Configuration](#-configuration)
- [Development Workflow](#-development-workflow)
- [Future Enhancements](#-future-enhancements)
- [Contributing](#-contributing)
- [Author](#-author)

---

## 🌟 Overview

The **AI-Powered Fitness Application** is a comprehensive fitness management system that leverages artificial intelligence to deliver personalized workout plans, activity tracking, and calorie recommendations. Built using **Java Spring Boot** and following **Microservices Architecture** principles, this application demonstrates industry-standard backend development practices with a focus on scalability, maintainability, and service isolation.

### 🎯 Project Goals

- Demonstrate production-ready microservices architecture
- Implement AI-driven personalized fitness recommendations
- Showcase clean code practices and REST API design
- Build a scalable, maintainable, and extensible system
- Follow enterprise-level development workflows

---

## ✨ Key Features

### 🤖 AI-Powered Intelligence
- **Personalized Workout Plans** - AI-generated exercise routines based on user goals and fitness levels
- **Smart Calorie Recommendations** - Dynamic calorie intake suggestions aligned with user objectives
- **Adaptive Activity Suggestions** - Real-time recommendations that evolve with user progress
- **Rule-Based Engine** - Intelligent logic ready for ML model integration

### 🏗️ Enterprise Architecture
- **Microservices-Based Design** - Independent, loosely coupled services for maximum scalability
- **Service Discovery** - Dynamic service registration and discovery using Netflix Eureka
- **RESTful APIs** - Clean, well-documented API endpoints following REST principles
- **Bearer Token Authentication** - Secure API access with token-based authentication
- **Centralized Configuration** - Config server for managing application properties

### 📊 Core Functionality
- **User Management** - Registration, authentication, and profile management
- **Activity Tracking** - Comprehensive workout and exercise logging
- **Progress Monitoring** - Track fitness journey with detailed history
- **BMI Calculation** - Automatic body mass index computation
- **Goal Setting** - Define and track personalized fitness objectives

### 🔧 Technical Excellence
- **Service Isolation** - Each service operates independently
- **Database per Service** - PostgreSQL instances for data isolation
- **Event-Driven Architecture** - Kafka integration for asynchronous communication
- **External API Integration** - Google's Gemini AI for enhanced intelligence
- **Centralized Service Registry** - Eureka for seamless inter-service communication

---

## 🏗️ System Architecture

```
┌─────────────────────────────────────────────────────────────────────────┐
│                          Bearer Token Authentication                      │
└─────────────────────────────────────────────────────────────────────────┘
                                      │
                                      ▼
┌──────────────┐          ┌─────────────────────┐
│   Postman    │────────▶ │      Gateway        │
│      OR      │          │    (Entry Point)    │
│   Frontend   │          └─────────────────────┘
└──────────────┘                     │
                    ┌────────────────┼────────────────┐
                    │                │                │
                    ▼                ▼                ▼
         ┌─────────────────┐ ┌──────────────┐ ┌──────────────┐
         │  User Service   │ │   Activity   │ │ AI Service   │
         │    ┌──────┐     │ │   Service    │ │   ┌──────┐   │
         │    │  🐘  │     │ │    ┌──────┐  │ │   │  🐘  │   │
         │    └──────┘     │ │    │  🐘  │  │ │   └──────┘   │
         └─────────────────┘ └──────────────┘ └──────────────┘
                    │                │                │
                    └────────────────┼────────────────┘
                                     │
                         ┌───────────┴───────────┐
                         │                       │
                         ▼                       ▼
                  ┌──────────────┐      ┌───────────────┐
                  │    Kafka     │      │  Google's     │
                  │  (Message    │      │    Gemini     │
                  │   Broker)    │      │   (AI API)    │
                  └──────────────┘      └───────────────┘
                         │
         ┌───────────────┼───────────────┐
         │               │               │
         ▼               ▼               ▼
┌──────────────┐ ┌──────────────┐ ┌──────────────┐
│   Keycloak   │ │Config Server │ │Eureka Server │
│  (Identity)  │ │ (Centralized │ │  (Service    │
│              │ │    Config)   │ │  Discovery)  │
└──────────────┘ └──────────────┘ └──────────────┘
```

### 🔄 Architecture Highlights

| Component | Purpose | Technology |
|-----------|---------|------------|
| **Gateway** | API Gateway & Routing | Spring Cloud Gateway + Keycloak |
| **User Service** | User management & authentication | Spring Boot + PostgreSQL |
| **Activity Service** | Workout & activity tracking | Spring Boot + MongoDB |
| **AI Service** | Intelligent recommendations | Spring Boot + MongoDB + Gemini AI |
| **Eureka Server** | Service discovery & registration | Netflix Eureka |
| **Keycloak** | Identity & access management (Gateway only) | Keycloak |
| **Config Server** | Centralized configuration | Spring Cloud Config |
| **Kafka** | Event streaming (Activity ↔ AI Service) | Apache Kafka |
| **WebClient** | Sync communication (User ↔ Activity Service) | Spring WebFlux |

### 🌊 Data Flow

1. **Client Request** → Postman/Frontend sends authenticated request with Bearer token
2. **Gateway Authentication** → Keycloak validates token at gateway level
3. **Service Routing** → Gateway routes request to appropriate microservice
4. **User-Activity Communication** → User Service communicates with Activity Service using **WebClient** (synchronous REST)
5. **Activity-AI Communication** → Activity Service publishes events to **Kafka**, AI Service consumes and processes
6. **AI Integration** → AI Service calls Google's Gemini API for enhanced intelligence
7. **Response Flow** → Processed data returned through Gateway to client

### 🔗 Communication Patterns

| Communication Type | From → To | Protocol | Use Case |
|-------------------|-----------|----------|----------|
| **Synchronous** | User Service → Activity Service | WebClient (REST) | Fetch user's activity data, real-time queries |
| **Asynchronous** | Activity Service → AI Service | Kafka | Activity logged events, batch processing |
| **Asynchronous** | AI Service → Activity Service | Kafka | AI-generated recommendations, suggestions |
| **External API** | AI Service → Google Gemini | REST API | AI-powered recommendations |
| **Authentication** | Gateway → Keycloak | OAuth 2.0 | Token validation |

---

## 🛠️ Tech Stack

### Backend Framework
```
Java 17+ (LTS)
├── Spring Boot 3.x
├── Spring Cloud
│   ├── Eureka Server (Service Discovery)
│   ├── Spring Cloud Gateway (API Gateway)
│   └── Spring Cloud Config (Configuration)
├── Spring Data JPA
├── Spring Security
└── Spring Web
```

### Database & Storage
- **PostgreSQL** - User Service relational database
- **MongoDB** - Activity Service and AI Service NoSQL database (separate instances)
- **H2** - In-memory database for development and testing

### Messaging & Integration
- **Apache Kafka** - Event streaming between Activity Service and AI Service
- **Spring WebClient** - Synchronous REST communication between User Service and Activity Service
- **Google Gemini AI** - External AI API for enhanced intelligence

### Security
- **Keycloak** - Identity and access management (connected to Gateway only)
- **JWT** - Token-based authentication
- **Spring Security** - Authorization framework
- **OAuth 2.0** - Authentication protocol

### Development Tools
- **IntelliJ IDEA** - Primary IDE
- **Maven** - Dependency management and build tool
- **Git & GitHub** - Version control
- **Postman** - API testing and documentation

### DevOps (Planned)
- **Docker** - Containerization
- **Kubernetes** - Container orchestration
- **Jenkins/GitHub Actions** - CI/CD pipeline

---

## 🧩 Microservices Overview

### 🔹 User Service
**Port**: `8081` | **Database**: PostgreSQL

**Key Responsibilities:**
- User registration and authentication
- Profile and fitness goal management
- BMI calculation
- **Communicates with Activity Service via WebClient**

**Technology:**
- Spring Boot + Spring Data JPA
- PostgreSQL
- Spring WebClient

**Key Endpoints:**
```
POST   /api/users/register
POST   /api/users/login
GET    /api/users/profile
PUT    /api/users/profile
GET    /api/users/activities   - Fetches from Activity Service via WebClient
```

---

### 🔹 Activity Service
**Port**: `8082` | **Database**: MongoDB

**Key Responsibilities:**
- Workout and activity tracking
- **Receives requests from User Service via WebClient**
- **Publishes activity events to Kafka**
- **Consumes AI recommendations from Kafka**

**Technology:**
- Spring Boot + Spring Data MongoDB
- MongoDB
- Spring Kafka (Producer & Consumer)

**Key Endpoints:**
```
POST   /api/activities
GET    /api/activities
GET    /api/activities/user/{id}
GET    /api/activities/stats
```

**Kafka Topics:**
- **Publishes to**: `activity-events`
- **Consumes from**: `ai-recommendations`

---

### 🔹 AI Service
**Port**: `8083` | **Database**: MongoDB | **External**: Google Gemini

**Key Responsibilities:**
- AI-powered fitness recommendations
- **Consumes activity events from Kafka**
- **Publishes recommendations to Kafka**
- Integration with Google Gemini API

**Technology:**
- Spring Boot + Spring Data MongoDB
- MongoDB
- Spring Kafka (Producer & Consumer)
- Google Gemini AI API

**Key Endpoints:**
```
POST   /api/ai/recommend/workout
POST   /api/ai/recommend/calories
POST   /api/ai/analyze
```

**Kafka Topics:**
- **Consumes from**: `activity-events`
- **Publishes to**: `ai-recommendations`

---

### 🔹 Gateway Service
**Port**: `8080`

**Key Responsibilities:**
- Single entry point for all requests
- **Keycloak OAuth 2.0 authentication**
- Request routing to services

**Routes:**
```
/api/users/**      → User Service
/api/activities/** → Activity Service
/api/ai/**         → AI Service
```

---

### 🔹 Eureka Server
**Port**: `8761`

Service discovery and registration.

---

### 🔹 Config Server
**Port**: `8888`

Centralized configuration management.

---

### 🔹 Keycloak
**Port**: `9090`

**Connected only to Gateway** for OAuth 2.0 authentication and JWT token validation.

---

## 📂 Project Structure

```
AI-Powered-Fitness-Application/
│
├── gateway-service/
│   ├── src/main/java/com/fitness/gateway/
│   │   ├── config/
│   │   │   ├── SecurityConfig.java
│   │   │   └── GatewayConfig.java
│   │   └── GatewayApplication.java
│   ├── src/main/resources/
│   │   └── application.yml
│   └── pom.xml
│
├── user-service/
│   ├── src/main/java/com/fitness/user/
│   │   ├── controller/
│   │   │   └── UserController.java
│   │   ├── service/
│   │   │   ├── UserService.java
│   │   │   └── impl/UserServiceImpl.java
│   │   ├── repository/
│   │   │   └── UserRepository.java
│   │   ├── model/
│   │   │   ├── User.java
│   │   │   ├── UserDTO.java
│   │   │   └── LoginRequest.java
│   │   ├── exception/
│   │   │   └── UserNotFoundException.java
│   │   └── UserServiceApplication.java
│   ├── src/main/resources/
│   │   ├── application.yml
│   │   └── data.sql
│   └── pom.xml
│
├── activity-service/
│   ├── src/main/java/com/fitness/activity/
│   │   ├── controller/
│   │   │   ├── ActivityController.java
│   │   │   └── RoutineController.java
│   │   ├── service/
│   │   │   ├── ActivityService.java
│   │   │   └── impl/ActivityServiceImpl.java
│   │   ├── repository/
│   │   │   ├── ActivityRepository.java
│   │   │   └── RoutineRepository.java
│   │   ├── model/
│   │   │   ├── Activity.java
│   │   │   ├── Exercise.java
│   │   │   └── Routine.java
│   │   ├── dto/
│   │   │   └── ActivityDTO.java
│   │   └── ActivityServiceApplication.java
│   ├── src/main/resources/
│   │   └── application.yml
│   └── pom.xml
│
├── ai-service/
│   ├── src/main/java/com/fitness/ai/
│   │   ├── controller/
│   │   │   └── AIController.java
│   │   ├── service/
│   │   │   ├── RecommendationService.java
│   │   │   ├── impl/RecommendationServiceImpl.java
│   │   │   └── GeminiIntegrationService.java
│   │   ├── repository/
│   │   │   └── RecommendationRepository.java
│   │   ├── model/
│   │   │   └── Recommendation.java
│   │   ├── dto/
│   │   │   ├── WorkoutRecommendationDTO.java
│   │   │   └── CalorieRecommendationDTO.java
│   │   ├── engine/
│   │   │   └── RuleBasedEngine.java
│   │   └── AIServiceApplication.java
│   ├── src/main/resources/
│   │   └── application.yml
│   └── pom.xml
│
├── eureka-server/
│   ├── src/main/java/com/fitness/eureka/
│   │   └── EurekaServerApplication.java
│   ├── src/main/resources/
│   │   └── application.yml
│   └── pom.xml
│
├── config-server/
│   ├── src/main/java/com/fitness/config/
│   │   └── ConfigServerApplication.java
│   ├── src/main/resources/
│   │   └── application.yml
│   └── pom.xml
│
├── config-repo/                    # Git repository for configurations
│   ├── user-service.yml
│   ├── activity-service.yml
│   ├── ai-service.yml
│   └── gateway-service.yml
│
├── .gitignore
├── README.md
├── docker-compose.yml              # Docker composition file
└── pom.xml                         # Parent POM
```

---

## 🚀 Getting Started

### Prerequisites

Before running the application, ensure you have the following installed:

- **Java 17+** - [Download](https://www.oracle.com/java/technologies/downloads/)
- **Maven 3.8+** - [Download](https://maven.apache.org/download.cgi)
- **PostgreSQL 14+** - [Download](https://www.postgresql.org/download/)
- **MongoDB 6.0+** - [Download](https://www.mongodb.com/try/download/community)
- **Apache Kafka** - [Download](https://kafka.apache.org/downloads)
- **Keycloak 23+** - [Download](https://www.keycloak.org/downloads)
- **Git** - [Download](https://git-scm.com/downloads)
- **IntelliJ IDEA** (recommended) - [Download](https://www.jetbrains.com/idea/)

### Optional
- **Docker & Docker Compose** - [Download](https://www.docker.com/products/docker-desktop/)
- **Postman** - [Download](https://www.postman.com/downloads/)

---

### 📥 Installation

#### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/AI-Powered-Fitness-Application.git
cd AI-Powered-Fitness-Application
```

#### 2. Setup Databases

**PostgreSQL Setup (User Service):**
```sql
-- User Service Database
CREATE DATABASE fitness_user_db;
```

**MongoDB Setup (Activity Service & AI Service):**
```bash
# Start MongoDB
mongod --dbpath /path/to/data

# Create databases (MongoDB creates on first use)
# Activity Service will use: fitness_activity_db
# AI Service will use: fitness_ai_db
```

Or using MongoDB Compass/Shell:
```javascript
// No explicit creation needed - MongoDB creates databases automatically
// Just ensure MongoDB is running on localhost:27017
```

#### 3. Configure Application Properties

**User Service** (`user-service/src/main/resources/application.yml`):
```yaml
spring:
  application:
    name: user-service
  datasource:
    url: jdbc:postgresql://localhost:5432/fitness_user_db
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}

server:
  port: 8081

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

**Activity Service** (`activity-service/src/main/resources/application.yml`):
```yaml
spring:
  application:
    name: activity-service
  data:
    mongodb:
      uri: mongodb://localhost:27017/fitness_activity_db

server:
  port: 8082

kafka:
  bootstrap-servers: localhost:9092
```

**AI Service** (`ai-service/src/main/resources/application.yml`):
```yaml
spring:
  application:
    name: ai-service
  data:
    mongodb:
      uri: mongodb://localhost:27017/fitness_ai_db

server:
  port: 8083

kafka:
  bootstrap-servers: localhost:9092

gemini:
  api:
    key: ${GEMINI_API_KEY}
```

**Gateway Service** (`gateway-service/src/main/resources/application.yml`):
```yaml
spring:
  application:
    name: gateway-service
  security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: http://localhost:9090/realms/fitness-realm

server:
  port: 8080
```

#### 4. Build the Project
```bash
# Build all services
mvn clean install

# Or build individual services
cd user-service
mvn clean install
```

---

### ▶️ Running the Application

#### Option 1: Manual Start (Recommended for Development)

Start services in the following order:

```bash
# 1. Start Eureka Server
cd eureka-server
mvn spring-boot:run

# 2. Start Config Server (if using)
cd config-server
mvn spring-boot:run

# 3. Start User Service
cd user-service
mvn spring-boot:run

# 4. Start Activity Service
cd activity-service
mvn spring-boot:run

# 5. Start AI Service
cd ai-service
mvn spring-boot:run

# 6. Start Gateway Service
cd gateway-service
mvn spring-boot:run
```

#### Option 2: Using Docker Compose (Planned)

```bash
# Start all services
docker-compose up

# Stop all services
docker-compose down
```

---

### 🧪 Verify Installation

1. **Eureka Dashboard**: http://localhost:8761
   - Verify all services are registered

2. **Test User Service**:
```bash
curl -X POST http://localhost:8080/api/users/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "email": "test@example.com",
    "password": "password123"
  }'
```

3. **Test Activity Service**:
```bash
curl -X GET http://localhost:8080/api/activities \
  -H "Authorization: Bearer YOUR_TOKEN"
```

---

## 📚 API Documentation

### Authentication

All requests require Bearer token authentication via Gateway.

**Get Token from Keycloak:**
```bash
curl -X POST http://localhost:9090/realms/fitness-realm/protocol/openid-connect/token \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "client_id=fitness-app" \
  -d "client_secret=YOUR_CLIENT_SECRET" \
  -d "username=user@example.com" \
  -d "password=password123" \
  -d "grant_type=password"
```

**Use Token:**
```bash
Authorization: Bearer <TOKEN>
```

---

### User Service API

```http
POST   /api/users/register
POST   /api/users/login
GET    /api/users/profile
PUT    /api/users/profile
```

---

### Activity Service API

```http
POST   /api/activities
GET    /api/activities
GET    /api/activities/user/{id}
GET    /api/activities/stats
```

---

### AI Service API

```http
POST   /api/ai/recommend/workout
POST   /api/ai/recommend/calories
POST   /api/ai/analyze
```

---

## ⚙️ Configuration

### Environment Variables

Create a `.env` file in the root directory:

```bash
# Database Configuration
POSTGRES_USER=your_username
POSTGRES_PASSWORD=your_password
MONGO_URI=mongodb://localhost:27017

# Service Ports
GATEWAY_PORT=8080
USER_SERVICE_PORT=8081
ACTIVITY_SERVICE_PORT=8082
AI_SERVICE_PORT=8083
EUREKA_PORT=8761
KEYCLOAK_PORT=9090

# Keycloak
KEYCLOAK_REALM=fitness-realm
KEYCLOAK_CLIENT_ID=fitness-app
KEYCLOAK_CLIENT_SECRET=your_client_secret

# APIs
GEMINI_API_KEY=your_gemini_api_key

# Kafka
KAFKA_BOOTSTRAP_SERVERS=localhost:9092
```

### Service Configuration Priority

1. Environment Variables (Highest)
2. Config Server
3. application.yml
4. Default Values (Lowest)

---

## 👨‍💻 Development Workflow

### Git Workflow

```bash
# Create feature branch
git checkout -b feature/your-feature-name

# Make changes and commit
git add .
git commit -m "feat: add new feature"

# Push to remote
git push origin feature/your-feature-name

# Create Pull Request on GitHub
```
feat: add new workout recommendation algorithm
fix: resolve NPE in user service
docs: update API documentation
refactor: optimize activity query performance
test: add unit tests for AI service
```

### Code Quality Standards

- **Clean Code**: Follow SOLID principles
- **Documentation**: JavaDoc for public APIs
- **Testing**: Minimum 80% code coverage
- **Code Review**: All PRs require review
- **Formatting**: Use IntelliJ default formatter

---

## 🔮 Future Enhancements

### Phase 1: Core Improvements
- [ ] Complete Kafka event-driven communication
- [ ] Implement JWT refresh tokens
- [ ] Add API rate limiting
- [ ] Centralized logging with ELK Stack
- [ ] Distributed tracing with Zipkin

### Phase 2: AI & ML Enhancement
- [ ] Machine Learning model integration
- [ ] Real-time activity analysis
- [ ] Exercise form detection using computer vision
- [ ] Nutrition API integration

### Phase 3: DevOps & Deployment
- [ ] Docker containerization
- [ ] Kubernetes deployment
- [ ] CI/CD pipeline with GitHub Actions
- [ ] Monitoring with Prometheus & Grafana

### Phase 4: Advanced Features
- [ ] Mobile application (React Native/Flutter)
- [ ] Social features and challenges
- [ ] Wearable device integration
- [ ] Video streaming for workouts
- [ ] Real-time coaching

### Phase 5: Scale & Performance
- [ ] Redis caching layer
- [ ] Database sharding
- [ ] CDN integration
- [ ] GraphQL API
- [ ] WebSocket for real-time updates

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. **Fork the repository**
2. **Create a feature branch** (`git checkout -b feature/AmazingFeature`)
3. **Commit your changes** (`git commit -m 'feat: add amazing feature'`)
4. **Push to the branch** (`git push origin feature/AmazingFeature`)
5. **Open a Pull Request**

### Contribution Guidelines

- Write clean, maintainable code
- Follow existing code style and conventions
- Add unit tests for new features
- Update documentation as needed
- Ensure all tests pass before submitting PR

---

## 👨‍💻 Author

**Kiran Daud**

Java Backend Developer | Microservices Architect

Passionate about building scalable, production-ready backend systems with clean architecture and best practices.

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Connect-blue?style=for-the-badge&logo=linkedin)](https://linkedin.com/in/kirandaud)
[![GitHub](https://img.shields.io/badge/GitHub-Follow-black?style=for-the-badge&logo=github)](https://github.com/kirandaud)
[![Email](https://img.shields.io/badge/Email-Contact-red?style=for-the-badge&logo=gmail)](mailto:kiran.daud@example.com)

---

## 🙏 Acknowledgments

- Spring Boot & Spring Cloud documentation
- Netflix OSS (Eureka)
- Google Gemini AI API
- Apache Kafka community
- All contributors and supporters

---

## 📞 Support

If you have any questions or need help, feel free to:

- Open an issue on GitHub
- Contact me via email
- Connect on LinkedIn

---

<div align="center">

**⭐ Star this repository if you find it helpful!**

Made with ❤️ by Kiran Daud

</div>
