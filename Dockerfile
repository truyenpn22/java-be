FROM maven:3.6.3-jdk-8 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:8-jre-slim
COPY --from=build /target/ReviewsBlog-0.0.1-SNAPSHOT.jar reviewblog.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/reviewblog.jar"]