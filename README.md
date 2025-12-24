🏋️‍♂️ AI-Powered Fitness Application

An AI-powered fitness management system built using Java Spring Boot and a Microservices Architecture.
The application delivers personalized workout, activity, and calorie recommendations using AI-driven logic while strictly following industry-standard backend design principles.

This project is designed to demonstrate scalable backend development, service isolation, and real-world microservices communication patterns.

✨ Key Features

Microservices-based backend architecture

Independent, loosely coupled, and scalable services

Service discovery using Eureka Server

Clean and well-structured RESTful APIs

AI-based recommendation engine (rule-based, ML-ready)

Centralized service registration & discovery

Consistent development workflow with regular Git commits

🛠️ Tech Stack
Category	Technology
Language	Java 17+
Framework	Spring Boot
Microservices	Spring Cloud
Service Discovery	Eureka Server
API Communication	REST APIs
IDE	IntelliJ IDEA
Version Control	Git & GitHub
🧩 Microservices Overview
🔹 User Service

User registration and authentication

User profile management

BMI calculation

Fitness goal configuration

🔹 Activity Service

Workout and activity tracking

Exercise and routine management

Activity history storage

Integration with AI service for recommendations

🔹 AI Service

AI-based fitness recommendation logic

Personalized calorie and workout suggestions

Rule-based recommendation engine

Designed to be ML-ready for future model integration

🔹 Eureka Server

Centralized service registration

Dynamic service discovery

Enables seamless inter-service communication

🏗️ System Architecture
Client
  |
  |  (Future Scope)
  v
API Gateway
  |
-------------------------------------------------
|              |               |                |
User Service   Activity Service   AI Service   Eureka Server

Architecture Highlights

Each service is independently deployable

Loose coupling between services

REST-based inter-service communication

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

🚀 Future Enhancements

API Gateway implementation (Spring Cloud Gateway)

JWT-based authentication & authorization

Kafka for asynchronous inter-service communication

Machine Learning model integration for AI service

Docker & Kubernetes deployment

Centralized logging and monitoring

👨‍💻 Author

Kiran Daud
Java Backend Developer
Focused on building scalable, production-ready backend systems using Java and Spring ecosystem.
