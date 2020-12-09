FROM openjdk:15
COPY ./target/producer-0.0.1-SNAPSHOT.jar /var
WORKDIR /var
ENTRYPOINT ["java", "-jar","producer-0.0.1-SNAPSHOT.jar"]