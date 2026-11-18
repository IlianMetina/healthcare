FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu
LABEL maintainer="owner"
WORKDIR /app
COPY /target/doctor-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "app.jar"]
