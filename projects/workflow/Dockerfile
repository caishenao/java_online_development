FROM openjdk:17-jdk-alpine
WORKDIR /app
ARG JAR_FILE=target/workflow-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005","-jar","/app.jar"]
EXPOSE 8080
EXPOSE 5005