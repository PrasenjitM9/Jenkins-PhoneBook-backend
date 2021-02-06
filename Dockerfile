FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD

MAINTAINER huxingran1993@gmail.com

EXPOSE 8091

VOLUME /tmp

ADD phone-book-backend-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
