FROM maven:3.8.3 as build
ENV HOME=/app
WORKDIR $HOME
ADD . $HOME
RUN ls .
RUN --mount=type=cache,target=/root/.m2 mvn clean install -DskipTests
RUN ls .
RUN ls $HOME/target/

FROM openjdk:17-oracle
COPY --from=build /app/target/*.jar sda-hymns.jar
EXPOSE 10500
ENTRYPOINT ["java","-jar","sda-hymns.jar","--server.port=10500"]
