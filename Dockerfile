FROM adoptopenjdk/openjdk11:alpine-slim
RUN mkdir /project
COPY ./target/demoapi-0.0.1-SNAPSHOT.jar /project/
WORKDIR /project
EXPOSE 8080
CMD java -jar demoapi-0.0.1-SNAPSHOT.jar