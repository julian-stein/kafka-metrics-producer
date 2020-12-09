package com.dhbw.kafka.producer;

import com.dhbw.kafka.producer.impl.KafkaProducer;
import com.dhbw.kafka.producer.metrics.SystemMetricsMessage;
import com.dhbw.loadGenerator.LoadGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class ProducerApplication {
    @Value(value = "${metrics.topic.name}")
    private String topicName;

    private static String TOPIC_NAME_STATIC;

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext appContext = SpringApplication.run(ProducerApplication.class, args);

        new Thread(new LoadGenerator("ssshhhhhhhhhhh??".getBytes(StandardCharsets.UTF_8))).start();
        new Thread(new LoadGenerator("ssshhhhhhhhhhh!!".getBytes(StandardCharsets.UTF_8))).start();

        KafkaProducer producer = appContext.getBean(KafkaProducer.class);

        OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        while(true) {
            SystemMetricsMessage message = new SystemMetricsMessage(operatingSystemMXBean.getCpuLoad(),
                    operatingSystemMXBean.getTotalMemorySize(), operatingSystemMXBean.getFreeMemorySize());
            producer.send(TOPIC_NAME_STATIC, message);
            Thread.sleep(500);
        }

        //appContext.close();
    }

    @Value("${metrics.topic.name}")
    public void setTopicNameStatic(String name){
        ProducerApplication.TOPIC_NAME_STATIC = name;
    }
}