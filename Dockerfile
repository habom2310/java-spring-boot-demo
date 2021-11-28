FROM openjdk:17
ADD target/demo-1.jar demo-1.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "demo-1.jar"]