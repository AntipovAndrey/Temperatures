FROM openjdk:8
VOLUME /tmp
EXPOSE 8080
ADD /build/libs/temperaturesensor-0.0.1-SNAPSHOT.jar server.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=docker","-jar", "server.jar"]
