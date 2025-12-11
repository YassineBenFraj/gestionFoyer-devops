# ---------- BUILD ----------
FROM maven:3.9.3-eclipse-temurin-17-alpine AS build
WORKDIR /app

COPY pom.xml .

# Télécharge toutes les dépendances AVANT le build
RUN mvn -B dependency:go-offline

COPY src ./src

# Build du projet
RUN mvn -B -DskipTests clean package

# ---------- RUNTIME ----------
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
