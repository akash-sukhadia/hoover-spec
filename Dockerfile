FROM openjdk:8-jdk-alpine
COPY target/hooverspec.jar hooverspec.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/hooverspec.jar"]