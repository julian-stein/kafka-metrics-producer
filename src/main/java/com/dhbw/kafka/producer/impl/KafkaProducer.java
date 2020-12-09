package com.dhbw.kafka.producer.impl;

import com.dhbw.kafka.producer.metrics.SystemMetricsMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, SystemMetricsMessage> metricsTemplate;

    public void send(String topic, SystemMetricsMessage payload) {
        metricsTemplate.send(topic, payload);
    }

}