FROM openjdk:17.0
VOLUME /tmp
EXPOSE 8600
ADD target/review-service-0.0.1-SNAPSHOT.jar review-service.jar
ENTRYPOINT ["java","-jar","review-service.jar"]