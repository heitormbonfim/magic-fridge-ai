FROM eclipse-temurin:21
LABEL maintainer="heitormbonfim@gmail.com"
WORKDIR /app

# 1) Declare build-time args (no default needed if you’ll always pass them)
ARG POSTGRES_USER
ARG POSTGRES_PASSWORD
ARG DB_URL
ARG GEMINI_API_KEY

# 2) Assign them to ENV in one instruction (use backslashes, no spaces around '=')
ENV POSTGRES_USER=${POSTGRES_USER} \
    POSTGRES_PASSWORD=${POSTGRES_PASSWORD} \
    DB_URL=${DB_URL} \
    GEMINI_API_KEY=${GEMINI_API_KEY}

COPY /target/magic-frige-0.0.1-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080
