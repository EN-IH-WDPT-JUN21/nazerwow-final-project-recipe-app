FROM openjdk:17.0
VOLUME /tmp
EXPOSE 7000
ADD target/user-service-0.0.1-SNAPSHOT.jar user-service.jar
ENTRYPOINT ["java","-jar","user-service.jar"]