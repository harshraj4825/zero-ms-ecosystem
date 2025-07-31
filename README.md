# Microservices Learning Project

This repository demonstrates a basic microservices architecture using Spring Boot, Spring Cloud, Eureka Service Discovery, Spring Cloud Gateway, and centralized configuration with Spring Cloud Config Server. The project is designed for learning and experimentation with modern Java microservices patterns.

---

## Microservices Overview

### 1. **Eureka Server**
- Location: [`eureka-server`](eureka-server)
- Provides service discovery for all microservices.
- Runs on port `8761`.
- Configuration: [`eureka-server/src/main/resources/application.yml`](eureka-server/src/main/resources/application.yml)

### 2. **Config Server**
- Location: [`config-server`](config-server)
- Centralized configuration management for all services.
- Loads configs from the [`config`](config) directory.
- Runs on port `8888`.
- Configuration: [`config-server/src/main/resources/application.yml`](config-server/src/main/resources/application.yml)

### 3. **API Gateway**
- Location: [`api-gateway`](api-gateway)
- Uses Spring Cloud Gateway for routing requests to backend services.
- Service discovery via Eureka.
- Runs on port `8080`.
- Configuration: [`api-gateway/src/main/resources/application.yml`](api-gateway/src/main/resources/application.yml)
- Gateway routes defined in [`config/api-gateway.yml`](config/api-gateway.yml)

### 4. **User Service**
- Location: [`user-service`](user-service)
- Reactive Spring Boot service using WebFlux.
- Demonstrates inter-service communication with Product Service using `WebClient`.
- Implements resilience patterns with Resilience4j (Circuit Breaker, Retry).
- Distributed tracing and metrics enabled (Micrometer, Zipkin, Prometheus).
- Runs on port `8081`.
- Configuration: [`config/user-service.yml`](config/user-service.yml)

### 5. **Product Service**
- Location: [`product-service`](product-service)
- Simple Spring Boot REST service.
- Provides product data.
- Runs on port `8082`.
- Configuration: [`config/product-service.yml`](config/product-service.yml)

---

## Centralized Configuration

All service configurations are managed centrally in the [`config`](config) directory and served by the Config Server. Each service imports its configuration from the Config Server at startup.

- Example: [`config/user-service.yml`](config/user-service.yml)
- Example: [`config/product-service.yml`](config/product-service.yml)
- Example: [`config/api-gateway.yml`](config/api-gateway.yml)

---

## Running the Project

### Prerequisites

- Java 21+
- Maven 3.8+
- Docker & Docker Compose

### Build All Services

```sh
mvn clean package
```