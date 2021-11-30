FROM openjdk:17
RUN mkdir target
ARG JAR_FILE
COPY ${JAR_FILE} target/tax-calculator.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "/target/tax-calculator.jar"]