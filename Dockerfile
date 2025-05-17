FROM openjdk:17

COPY target/Human-app.jar  /usr/app/

WORKDIR /usr/app/

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "Human-app.jar"]