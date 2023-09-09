FROM maven:3.8.5-openjdk-17 AS builder
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/ReviewsBlog-0.0.1-SNAPSHOT.jar reviewblog.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/java-be.jar"]