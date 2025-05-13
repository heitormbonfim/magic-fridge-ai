## Summary of Steps

1. **Prerequisites**: Java 21, Maven, Docker (optional).
2. **Configure your environment**: Copy `.env-example` to `.env` and fill in real credentials.
3. **Run locally**:

   - **IDE**: Launch the `main()` method.
   - **Maven CLI**: `mvn spring-boot:run` ([Home][1]).
   - **Executable JAR**: `java -jar target/magic-frige-0.0.1-SNAPSHOT.jar` ([Home][1]).

4. **Run in Docker**: `docker build` + `docker run --env-file .env …`.

---

## Project Details (from `pom.xml`)

- **Group/Artifact**: `com.heitormbonfim.www:magic-frige`
- **Version**: `0.0.1-SNAPSHOT`
- **Java**: 21
- **Description**: “Create recipes using AI with what you already have in the fridge.”
- **Core Dependencies**:

  - Spring Boot 3.4.4: Web, WebFlux, Data JPA, DevTools
  - Flyway Core for DB migrations
  - H2 for embedded runtime DB
  - Lombok (compile-time only)
  - Spring Boot Test & Reactor Test for tests

---

## `.env-example`

```env
# Database (H2 file-based)
DB_USERNAME=magic_fridge_ai
DB_PASSWORD=magic_fridge_ai
DB_URL=jdbc:h2:file:./data/MagicFridgeAi

# AI / External APIs
GEMINI_API_KEY=YOUR_GEMINI_API_KEY_HERE
```

_Copy this file to `.env` and replace `YOUR_GEMINI_API_KEY_HERE` (and any other defaults) with your real secrets._

---

## Running Locally

### 1. In Your IDE

Most IDEs (IntelliJ, Eclipse, VS Code) detect Spring Boot’s `@SpringBootApplication` and let you hit “Run” on the `main()` method. Your app will start on `http://localhost:8080` by default ([Medium][2]).

### 2. Via Maven CLI

From the project root (where `pom.xml` lives), run:

```bash
mvn spring-boot:run
```

The Spring Boot Maven plugin compiles and launches your app in one step ([Home][1]).

> **Tip**: You can pass JVM or Spring profiles via `-D` flags, e.g.
> `mvn spring-boot:run -Dspring-boot.run.profiles=dev` ([Home][3]).

### 3. As a Packaged JAR

First build:

```bash
mvn clean package
```

Then run:

```bash
java -jar target/magic-frige-0.0.1-SNAPSHOT.jar
```

This starts the same Spring Boot app from the fat JAR ([Home][1]).

---

## Running with Docker

1. **Build the image** (assuming your `Dockerfile` is set up as before):

   ```bash
   docker build -t magic-frige .
   ```

2. **Start the container**, binding port 8080 and loading your `.env`:

   ```bash
   docker run -d \
     --env-file .env \
     -p 8080:8080 \
     --name magic-frige \
     magic-frige
   ```

   or

   ```bash
   docker build \
   --build-arg DB_USERNAME=magic_fridge_ai \
   --build-arg DB_PASSWORD=magic_fridge_ai \
   --build-arg DB_URL="jdbc:h2:file:./data/MagicFridgeAi" \
   --build-arg GEMINI_API_KEY=AIfaSaBL6ww4J5C5AAXM3C-j5OOTmY21HjAjisE \
   -t magic-fridge .
   ```

No changes to the Dockerfile are needed—Docker will inject each `KEY=VALUE` from your `.env` at runtime ([Stack Overflow][4]).
