FROM adoptopenjdk/openjdk11:alpine-jre
EXPOSE 8080
ARG JAR_FILE=fw/target/*.jar
COPY ${JAR_FILE} app.jar
CMD java -jar app.jar