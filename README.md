# RBIH Loan Evaluation Service

A Spring Boot backend service that evaluates loan applications and determines approval or rejection based on predefined financial rules such as credit score, EMI ratio, and age-tenure eligibility.

---

## Tech Stack

* **Java**: JDK 21
* **Framework**: Spring Boot 4.0.3
* **Build Tool**: Maven
* **Database**: H2 (In-memory database)
* **API Documentation**: Swagger / OpenAPI
* **Architecture**: RESTful API

---

# Project Setup

## Prerequisites

Make sure the following are installed:

* Java 21
* Maven 3.8+
* Git (optional)

Verify installation:

```bash
java -version
mvn -version
```

---

# Running the Application

Clone the repository:

```bash
git clone https://github.com/VaibhavBedarkar/loan-eligibility-service
cd loan-eligibility-service
```

Run the application:

```bash
mvn spring-boot:run
```

Or build and run:

```bash
mvn clean install
java -jar target/*.jar
```

The application will start on:

```
http://localhost:8888/v1/api/rbih
```

---

# API Documentation

Swagger UI is available at:

```
http://localhost:8888/v1/api/rbih/swagger-ui/index.html#
```

---

# H2 Database Console

The project uses an **in-memory H2 database** for development.

Access the console:

```
http://localhost:8888/v1/api/rbih/h2-console
```

Typical connection settings:

```


JDBC URL: jdbc:h2:mem:loanDB
Username: sa
Password: (leave empty unless configured)
```


---

# Testing

Run tests using:

```bash
mvn test
```

---

# Author

Vaibhav Vinod Bedarkar

---
