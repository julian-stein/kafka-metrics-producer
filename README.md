# Kafka Metrics Producer
This repository contains the source code for a kafka producer continously sending the current CPU and memory usage of its host system to a Kafka topic.

You can run the producer using docker building an image with the provide Dockerfile and executable jar.

Run the producer using the following command:
"""
docker run --name metrics-demo-producer -e SPRING_APPLICATION_JSON='{\"metrics\":{\"topic\":{\"name\":\"<topicName>\"}}}' --network kafka-network metrics-producer
"""
Make sure to supply a topic name using the SPRING_APPLICATION_JSON environment variable.
Depending on your CLI you may have to adjust the escaping of the JSON-String and/or remove the outer single quotation marks.
