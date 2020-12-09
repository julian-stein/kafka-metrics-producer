# Kafka Metrics Producer
This repository contains the source code for a kafka producer continously sending the current CPU and memory usage of its host system to a Kafka topic.

You can run the producer using docker building an image with the provide Dockerfile and executable jar.
Set your Docker container environment up like the following:
- Zookeeper instance running and connected to your broker.
- Broker running in a container called 'openjdk-kafka' and advertising port 9092 in its config.
- Add the broker to a docker bridge network called 'kafka-network'.

Alternatively, set up your environment for your needs and adjust the 'kafka.bootstrapAddress' value in the application.properties file.
You can also supply the broker adress in the following docker run command to override the defaults set in the application.properties file.

Run the producer using the following command:
```
docker run --name metrics-demo-producer -e SPRING_APPLICATION_JSON='{\"metrics\":{\"topic\":{\"name\":\"<topicName>\"}}}' --network kafka-network metrics-producer
```
Make sure to supply a topic name using the SPRING_APPLICATION_JSON environment variable.
Depending on your CLI you may have to adjust the escaping of the JSON-String and/or remove the outer single quotation marks.
