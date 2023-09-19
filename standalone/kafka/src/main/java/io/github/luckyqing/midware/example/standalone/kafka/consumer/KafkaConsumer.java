package io.github.luckyqing.midware.example.standalone.kafka.consumer;

import io.github.luckyqing.midware.example.standalone.kafka.constants.Topics;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = {Topics.TEST})
    public void onMessage(String message) {
        System.out.println(String.format("接收到消息：%s", message));
    }

}