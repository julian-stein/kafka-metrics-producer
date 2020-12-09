package com.dhbw.kafka.producer.metrics;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class SystemMetricsSerializer implements Serializer<SystemMetricsMessage> {
    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, SystemMetricsMessage systemMetricsMessage) {
        byte[] serialized = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            serialized = objectMapper.writeValueAsBytes(systemMetricsMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return serialized;
    }

    @Override
    public byte[] serialize(String topic, Headers headers, SystemMetricsMessage data) {
        byte[] serialized = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            serialized = objectMapper.writeValueAsBytes(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return serialized;
    }

    @Override
    public void close() {

    }
}
