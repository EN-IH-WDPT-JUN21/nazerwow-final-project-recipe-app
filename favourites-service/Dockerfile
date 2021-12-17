FROM openjdk:17.0
VOLUME /tmp
EXPOSE 8300
ADD target/favourites-service-0.0.1-SNAPSHOT.jar favourites-service.jar
ENTRYPOINT ["java","-jar","favourites-service.jar"]