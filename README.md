# TradeWise Nexus

TradeWise Nexus is a full-stack paper trading simulator built to demonstrate application development, backend API integration, database connectivity, CI/CD, containerization, monitoring, and Kubernetes deployment.

It allows users to register, log in, add virtual funds, view live-simulated stock prices, buy and sell stocks, track portfolio performance, manage a watchlist, and review transaction history.

This is a **paper trading simulator**, not a real-money trading platform.

---

## Features

### Application Features
- User registration and login
- Virtual wallet creation on registration
- Add virtual funds
- Live-simulated stock market updates
- Buy and sell stock simulation
- Portfolio valuation and profit/loss tracking
- Watchlist management
- Transaction history
- Unified dashboard with wallet, portfolio, movers, and recent activity

### DevOps Features
- Maven-based Spring Boot backend
- GitHub Actions CI pipeline
- Dockerized frontend and backend
- Docker Compose setup for full local stack
- Spring Boot Actuator health and metrics
- Prometheus monitoring
- Grafana dashboards

### Kubernetes Features
- Kubernetes deployment YAML files
- Services for frontend, backend, MySQL, Prometheus, and Grafana
- Verified pod and service deployment in cluster

---

## Tech Stack

### Frontend
- HTML
- CSS
- JavaScript

### Backend
- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Maven

### Database
- MySQL

### DevOps / Observability
- GitHub Actions
- Docker
- Docker Compose
- Kubernetes
- Spring Boot Actuator
- Prometheus
- Grafana

---

## Project Structure

```text
tradewise-nexus/
├── frontend/
│   ├── index.html
│   ├── login.html
│   ├── register.html
│   ├── dashboard.html
│   ├── market.html
│   ├── portfolio.html
│   ├── watchlist.html
│   ├── transactions.html
│   ├── funds.html
│   ├── live-track.html
│   └── Dockerfile
│
├── backend/
│   ├── src/main/java/com/tradewisenexus/
│   │   ├── controller/
│   │   ├── service/
│   │   ├── repository/
│   │   ├── model/
│   │   ├── dto/
│   │   └── TradeWiseNexusApplication.java
│   ├── src/main/resources/
│   │   └── application.properties
│   ├── src/test/
│   ├── Dockerfile
│   └── pom.xml
│
├── .github/workflows/
│   └── backend-ci.yml
│
├── monitoring/
│   └── prometheus.yml
│
├── k8s/
│   ├── backend-deployment.yaml
│   ├── backend-service.yaml
│   ├── frontend-deployment.yaml
│   ├── frontend-service.yaml
│   ├── mysql-deployment.yaml
│   ├── mysql-service.yaml
│   ├── prometheus-config.yaml
│   ├── prometheus-deployment.yaml
│   ├── prometheus-service.yaml
│   ├── grafana-deployment.yaml
│   └── grafana-service.yaml
│
├── docker-compose.yml
└── README.md
cd ~/tradewise-nexus

git status
git add README.md k8s
git commit -m "finalize README and Kubernetes deployment files"
git push origin main
cd ~/tradewise-nexus

git status
git add README.md k8s
git commit -m "finalize README and Kubernetes deployment files"
git push origin main
