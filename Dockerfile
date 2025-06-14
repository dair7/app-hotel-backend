#etapa1: construccion del jar
#DockerFile esta divididido en dos capas


FROM maven:3.9.10-open-jdk-22 AS build

WORKDIR /app

COPY pom.xml /

RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

RUN ls -la /app/target

#etapa2: construccion final

FROM openjdk:22-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/PhegonHotel-0.0.1-SNAPSHOT.jar /app/PhegonHotel.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/PhegonHotel.jar"]