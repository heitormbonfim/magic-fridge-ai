# 🧠 Magic Frige AI

Use AI to suggest recipes based on what’s already in your fridge.

---

## 📋 Prerequisites

- **Java**: 21
- **Maven**: For local builds and dev
- **Docker & Docker Compose**: For containerized runtime
- **.env file**: For runtime secrets (not build-time)

---

## ⚙️ Project Configuration

### ✅ `.env-example`

```env
# Database
POSTGRES_USER=postgres
POSTGRES_PASSWORD=mysecretpassword
DB_URL=jdbc:postgresql://127.0.0.1:5432/postgres

# AI
GEMINI_API_KEY=your_gemini_key_here
````

> Copy this file to `.env` and replace the values with your real credentials.

---

## 🚀 How to Run the App

### 🧪 Option 1: Local Dev (No Docker)

#### 📍 1. Use `.env` with Maven

```bash
# Load env without exporting
set -a
. .env
set +a

mvn spring-boot:run
```

Or, manually inject:

```bash
DB_URL=jdbc:postgresql://127.0.0.1:5432/postgres \
POSTGRES_USER=postgres \
POSTGRES_PASSWORD=mysecretpassword \
GEMINI_API_KEY=your_gemini_key_here \
mvn spring-boot:run
```

---

#### 📍 2. Run Packaged JAR (after build)

```bash
mvn clean package
```

Then:

```bash
set -a
. .env
set +a

java -jar target/magic-frige-0.0.1-SNAPSHOT.jar
```

Or:

```bash
DB_URL=jdbc:postgresql://127.0.0.1:5432/postgres \
POSTGRES_USER=postgres \
POSTGRES_PASSWORD=mysecretpassword \
GEMINI_API_KEY=your_gemini_key_here \
java -jar target/magic-frige-0.0.1-SNAPSHOT.jar
```

---

## 🐳 Docker-Based Execution

### 📁 1. Build & Run with Compose

Make sure your `.env` is in the root project directory.

```bash
docker-compose up --build
```

This:

* Builds the Spring Boot image
* Connects to PostgreSQL in another container
* Mounts database volume
* Reads your `.env` (only `GEMINI_API_KEY` is passed at runtime)

📍 Web app: [http://localhost:8080](http://localhost:8080)
🐘 DB: PostgreSQL at `localhost:5432`, DB name `postgres`

---

### 🔁 Restart Only App Container (e.g., code change)

```bash
docker-compose up --build app
```

To rebuild everything:

```bash
docker-compose down -v
docker-compose up --build
```

---

## 🗂️ Project Structure

```text
.
├── Dockerfile
├── docker-compose.yml
├── .env
├── .env-example
├── pom.xml
└── target/
    └── magic-frige-0.0.1-SNAPSHOT.jar
```

---

## 🧠 Tech Stack

* **Java 21**
* **Spring Boot 3.4.4**

    * Web, WebFlux, JPA, DevTools
* **PostgreSQL** (Dockerized)
* **Flyway**: DB migrations
* **H2**: Optional in-memory DB
* **Gemini AI API**
* **Lombok**, **Spring Test**

---

## ❌ Security Warning

Do **NOT** use `ARG` to inject secrets like API keys into your Docker builds.
Always inject secrets **at runtime** via:

* `--env` or `.env`
* `docker-compose` (uses `.env` automatically)
* `--env-file .env` in `docker run`

---

## ✅ Sample Command (No `.env` used)

```bash
docker build \
  --build-arg POSTGRES_USER=postgres \
  --build-arg POSTGRES_PASSWORD=mysecretpassword \
  --build-arg DB_URL=jdbc:postgresql://db:5432/magicfrige \
  -t magic-frige .

docker run -d \
  -e GEMINI_API_KEY=your_actual_key \
  -p 8080:8080 \
  --name magic-frige \
  magic-frige
```

> This avoids `.env` and avoids shell pollution (no `export` needed).

---

## 🧼 Cleanup

```bash
docker-compose down -v
```

Removes containers and volumes (including the DB volume).
