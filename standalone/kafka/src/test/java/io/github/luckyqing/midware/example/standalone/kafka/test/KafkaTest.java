package io.github.luckyqing.midware.example.standalone.kafka.test;

import io.github.luckyqing.midware.example.standalone.kafka.constants.Topics;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.TimeUnit;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@EmbeddedKafka(count = 1, ports = {9092})
public class KafkaTest {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Test
    public void testSend() throws InterruptedException {
        kafkaTemplate.send(Topics.TEST, "hello world!");
        TimeUnit.SECONDS.sleep(100);
    }

}