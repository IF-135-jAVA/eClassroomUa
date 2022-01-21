FROM adoptopenjdk/openjdk11:jdk-11-alpine as builder
ARG JAR_FILE=*.jar
COPY --from=builder /src/target/better-learning-room-0.0.1-SNAPSHOT.jar belero.jar
ENTRYPOINT ["java", "-jar", "belero.jar"]