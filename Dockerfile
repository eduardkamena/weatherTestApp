FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/weatherTest-0.0.1-SNAPSHOT.jar app.jar
COPY target/classes/META-INF /app/META-INF/resources
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]