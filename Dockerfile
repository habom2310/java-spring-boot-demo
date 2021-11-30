FROM openjdk:17
RUN mkdir target
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} target/tax-calculator.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "/target/tax-calculator.jar"]