package com.example.kafka_consumer_example.config;

import com.example.kafka_consumer_example.model.Student;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Map;

import static org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG;

@Getter
@Setter
@Configuration
@ConfigurationProperties("kafka")
@RequiredArgsConstructor
public class StudentConsumerProperties {

    private final KafkaProperties kafkaProperties;

    private String studentTopic = "student_topic";
    private JsonDeserializer<Student> studentDeserializer;

    {
        studentDeserializer = new JsonDeserializer<>(Student.class, false);
        studentDeserializer.addTrustedPackages("*");
    }

    public Map<String, Object> buildConsumerProperties() {
        Map<String, Object> properties = kafkaProperties.buildConsumerProperties();
        properties.put(VALUE_DESERIALIZER_CLASS_CONFIG, studentDeserializer);
        return properties;
    }
}