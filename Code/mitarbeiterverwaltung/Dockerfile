FROM openjdk:17-jdk-slim as build

WORKDIR /app

COPY pom.xml .

RUN ./mvnw dependency:go-offline

COPY src src
COPY mvnw .
COPY .mvn .mvn

RUN ./mvnw package -DskipTests

FROM openjdk:17-jre-slim

COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]

EXPOSE 8080