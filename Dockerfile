FROM amazoncorretto:21

COPY ./build/libs/TaskManager-0.0.1-SNAPSHOT.jar /api.jar

ENTRYPOINT ["java", "-jar", "/api.jar"]