FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn dependency:go-offline && \
    mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk

RUN groupadd -r appgroup && useradd -r -g appgroup appuser

WORKDIR /app

COPY --from=build /app/target/inditex-0.0.1-SNAPSHOT.jar inditex.jar

RUN chown appuser:appgroup inditex.jar

USER appuser

EXPOSE 3000

ENTRYPOINT ["java", "-jar", "/app/inditex.jar"]