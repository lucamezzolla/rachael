FROM openjdk:21
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8900
ENTRYPOINT ["java", "-jar", "app.jar"]
