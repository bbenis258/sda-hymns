FROM openjdk:17-jdk-alpine
RUN apk update
RUN apk add --no-cache tzdata
ENV TZ=Africa/Kigali

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} sda-hymns.jar
EXPOSE 10500
ENTRYPOINT ["java","-jar","sda-hymns.jar","--server.port=10500"]
