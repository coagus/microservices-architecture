FROM openjdk:17-jdk-slim AS build

WORKDIR /app

COPY . .

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY --from=build /app/target/cuenta-movimientos-0.0.1-SNAPSHOT.jar /app/cuenta-movimientos.jar

EXPOSE 8080

CMD ["java", "-jar", "cuenta-movimientos.jar"]