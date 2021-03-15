FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package

FROM openjdk:12

WORKDIR /app

COPY --from=MAVEN_BUILD /build/target/*.jar /app/application.jar

ENTRYPOINT ["java", "-jar", "application.jar"]
