FROM eclipse-temurin:21
LABEL maintainer="heitormbonfim@gmail.com"
WORKDIR /app

# 1) Declare build-time args (no default needed if you’ll always pass them)
ARG DB_USERNAME
ARG DB_PASSWORD
ARG DB_URL
ARG GEMINI_API_KEY

# 2) Assign them to ENV in one instruction (use backslashes, no spaces around '=')
ENV DB_USERNAME=${DB_USERNAME} \
    DB_PASSWORD=${DB_PASSWORD} \
    DB_URL=${DB_URL} \
    GEMINI_API_KEY=${GEMINI_API_KEY}

COPY /target/magic-frige-0.0.1-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080
