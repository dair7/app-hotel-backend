# Etapa de construcción
FROM maven:3.8.6-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Variables de entorno
ENV PORT=8080
EXPOSE $PORT

ENTRYPOINT ["java", "-jar", "app.jar"]