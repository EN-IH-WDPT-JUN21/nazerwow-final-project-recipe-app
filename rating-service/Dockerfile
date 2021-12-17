FROM openjdk:17.0
VOLUME /tmp
EXPOSE 8400
ADD target/rating-service-0.0.1-SNAPSHOT.jar rating-service.jar
ENTRYPOINT ["java","-jar","rating-service.jar"]