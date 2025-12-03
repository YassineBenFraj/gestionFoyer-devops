# ---------- BUILD ----------
FROM maven:3.9.3-eclipse-temurin-17-alpine AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src

# Build du projet
RUN mvn -B -DskipTests clean package

# ---------- RUNTIME ----------
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
