FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
ADD target/Libreria-0.0.1-SNAPSHOT.jar Libreria-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/Libreria-0.0.1-SNAPSHOT.jar"]