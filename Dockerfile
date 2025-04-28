FROM eclipse-temurin:21
LABEL maintainer="wallacevilela"
WORKDIR /app
COPY target/movieflix-2-0.0.1-SNAPSHOT.jar /app/movieflix.jar
ENTRYPOINT ["java", "-jar", "movieflix.jar"]