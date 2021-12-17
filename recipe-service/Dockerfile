FROM openjdk:17.0
VOLUME /tmp
EXPOSE 8100
ADD target/recipe-service-0.0.1-SNAPSHOT.jar recipe-service.jar
ENTRYPOINT ["java","-jar","recipe-service.jar"]