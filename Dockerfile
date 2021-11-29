FROM openjdk:17
RUN mkdir target
COPY ${JAR_FILE} target/app.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "/target/app.jar"]