FROM maven:3.8.5-openjdk-17 AS builder
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/java-crud-3.2.0-SNAPSHOT.jar java-crud.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/java-crud.jar"]