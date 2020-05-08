package com.example.kafka_consumer_example.config;

import com.example.kafka_consumer_example.model.Student;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@EnableKafka
@Configuration
@RequiredArgsConstructor
public class StudentConsumerConfiguration {

    private final StudentConsumerProperties properties;

    @Bean
    public ConsumerFactory<String, Student> studentConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(properties.buildConsumerProperties(), new StringDeserializer(), properties.getStudentDeserializer());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Student> studentListenerContainerFactory(ConsumerFactory<String, Student> studentConsumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, Student> listener = new ConcurrentKafkaListenerContainerFactory<>();
        listener.setConsumerFactory(studentConsumerFactory);
        return listener;
    }

}
