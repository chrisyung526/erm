FROM openjdk:8

VOLUME /tmp
ARG JAR_FILE=./*.jar
COPY ${JAR_FILE} app.jar
ENV JAVA_OPTS -Xmx512m
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar
EXPOSE 20110