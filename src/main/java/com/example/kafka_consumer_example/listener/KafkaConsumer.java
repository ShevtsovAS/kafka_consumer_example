package com.example.kafka_consumer_example.listener;

import com.example.kafka_consumer_example.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer {
    @KafkaListener(
            topics = "${kafka.topic}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "studentListenerContainerFactory")
    public void consume(Student student) {
        log.info("Consumed student {}", student);
    }
}
