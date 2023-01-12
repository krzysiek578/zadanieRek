# define base docker image
FROM openjdk:17
EXPOSE 8080
ADD target/weather-0.0.1-SNAPSHOT.jar weather.jar
ENTRYPOINT ["java", "-jar", "/weather.jar"]