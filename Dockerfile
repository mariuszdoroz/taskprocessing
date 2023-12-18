FROM openjdk:17-oracle
EXPOSE 8080
ADD target/task-processing.jar task-processing.jar
ENTRYPOINT ["java", "-jar", "/task-processing.jar"]