FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline
RUN mvn -q -DskipTests package
FROM eclipse-temurin:21-jre
WORKDIR /app
ENV JAVA_OPTS="-Xms128m -Xmx256m"
EXPOSE 8080
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]