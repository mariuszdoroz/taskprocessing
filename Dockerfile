FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /taskprocessing

COPY pom.xml .
COPY src src
RUN mvn clean package

FROM openjdk:17-slim
EXPOSE 8080
WORKDIR /taskprocessing
COPY --from=build /taskprocessing/target/task-processing.jar task-processing.jar

CMD ["java", "-jar", "task-processing.jar"]
