AI Powered Fitness Application

An AI-powered fitness management system built using Java Spring Boot and Microservices Architecture.
The application provides personalized workout and activity recommendations using AI-based logic while following industry-standard backend practices.

📌 Key Highlights

Microservices-based backend architecture

Service discovery using Eureka Server

Independent and scalable services

Clean REST API design

Daily Git commits for consistent development

🛠️ Tech Stack
Category	Technology
Language	Java 17+
Framework	Spring Boot
Microservices	Spring Cloud
Service Discovery	Eureka Server
API Communication	REST
IDE	IntelliJ IDEA
Version Control	Git & GitHub
🧩 Microservices Overview
🔹 User Service

User registration & profile management

BMI calculation

Fitness goal management

🔹 Activity Service

Workout & activity tracking

Exercise recommendations

Activity history management

🔹 AI Service

AI-based fitness logic

Personalized calorie & workout suggestions

Recommendation engine (rule-based / ML-ready)

🔹 Eureka Server

Service registration and discovery

Enables dynamic communication between services

🏗️ System Architecture
Client
  |
API Gateway (Future)
  |
------------------------------------------------
|            |                |               |
User Service  Activity Service  AI Service   Eureka


Each service is independently deployable

Services communicate using REST APIs

Centralized service discovery via Eureka

📂 Project Structure
AI-Powered-Fitness-Application
├── userservice
│   └── src/main/java
├── activityservice
│   └── src/main/java
├── aiservice
│   └── src/main/java
├── eureka
│   └── src/main/java
├── .gitignore
└── README.md
