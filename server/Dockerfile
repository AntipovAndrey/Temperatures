FROM openjdk:8

RUN mkdir -p /server/app
WORKDIR /server/app

ADD /build/libs/temperaturesensor-0.0.1-SNAPSHOT.jar server.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=docker","-jar", "server.jar"]
