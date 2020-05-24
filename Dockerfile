FROM adoptopenjdk/openjdk11:alpine-slim
COPY target/spring-session-sample-0.0.1-SNAPSHOT.jar app.jar
#RUN apk add --no-cache mysql-client
#COPY wait.sh /wait.sh
EXPOSE 8080

# TODO: when use "-Djava.security.egd=file:/dev/./urandom" option, error occurred.
ENTRYPOINT ["java", "-jar","/app.jar", "--spring.profiles.active=devdocker"]