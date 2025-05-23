FROM eclipse-temurin:21
LABEL maintainer="heitormbonfim@gmail.com"
WORKDIR /app

# Declare build-time args (for DB config only, not secrets)
ARG POSTGRES_USER
ARG POSTGRES_PASSWORD
ARG DB_URL

# Runtime environment variables
ENV POSTGRES_USER=${POSTGRES_USER} \
    POSTGRES_PASSWORD=${POSTGRES_PASSWORD} \
    DB_URL=${DB_URL} \
    GEMINI_API_KEY=""

COPY /target/magic-frige-0.0.1-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080
