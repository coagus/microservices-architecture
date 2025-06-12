FROM openjdk:17-jdk-slim AS build

WORKDIR /app

COPY . .

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY --from=build /app/target/discovery-service-0.0.1-SNAPSHOT.jar /app/discovery-service.jar

EXPOSE 8761

CMD ["java", "-jar", "discovery-service.jar"]