FROM eclipse-temurin:17-jdk-focal
LABEL authors="Omar"

COPY  target/examen_gonzalez-0.0.1-SNAPSHOT.jar examen_gonzalez-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/examen_gonzalez-0.0.1-SNAPSHOT.jar"]