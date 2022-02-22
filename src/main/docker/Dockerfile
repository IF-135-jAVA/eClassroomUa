FROM adoptopenjdk/openjdk11:alpine-jre
MAINTAINER Tryniak Igor
ARG JAR_FILE=target/*.jar
ENV MAIL="BetterLearningRoom@gmail.com"
ENV MAIL_PASSWORD="IF-135.Java"
ENV ACCESS_KEY="AKIAVK3R7XKYKMZLJNMU"
ENV SECRET_KEY="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"
COPY better-learning-room-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]