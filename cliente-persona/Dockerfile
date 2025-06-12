FROM openjdk:17-jdk-slim AS build

WORKDIR /app

COPY . .

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY --from=build /app/target/cliente-persona-0.0.1-SNAPSHOT.jar /app/cliente-persona.jar

EXPOSE 8080

CMD ["java", "-jar", "cliente-persona.jar"]