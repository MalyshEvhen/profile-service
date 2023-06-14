FROM openjdk:17-alpine
ADD target/profile-service-*.jar /app/app.jar
ENTRYPOINT [ "java", "-jar", "/app/app.jar" ]