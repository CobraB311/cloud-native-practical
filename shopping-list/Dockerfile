FROM maven:3.5.2-jdk-8-alpine AS build
COPY src /tmp/src
COPY pom.xml /tmp
RUN mvn -f /tmp/pom.xml clean install package -Dspring.profiles.active=hsqldb -T 1C

FROM openjdk:8-jdk-alpine
COPY --from=build /tmp/target/*.jar shopping-list.jar
EXPOSE 8082
ENTRYPOINT ["java", "-Dspring.profiles.active=hsqldb" ,"-Dserver.port=8082", "-jar", "shopping-list.jar"]

# docker container run -p 8082:8082