FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/car_mgt_system-0.0.1-SNAPSHOT.jar car_mgt_system.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "car_mgt_system.jar"]