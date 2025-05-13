FROM eclipse-temurin:21
LABEL maintainer="heitormbonfim@gmail.com"
WORKDIR /app
COPY /target/magic-frige-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]