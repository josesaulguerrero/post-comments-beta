FROM amazoncorretto:11-alpine-jdk
COPY /target/beta.jar beta.jar
EXPOSE 8080/tcp
ENTRYPOINT ["java","-jar","/beta.jar"]
