package io.github.luckyqing.midware.example.standalone.kafka.configuration;

import io.github.luckyqing.midware.example.standalone.kafka.properties.KafkaTopicProperties;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.Set;

@Configuration
@EnableConfigurationProperties(KafkaTopicProperties.class)
@AutoConfigureBefore(KafkaAutoConfiguration.class)
public class KafkaAutoCreateTopicConfiguration {

    private final KafkaTopicProperties kafkaTopicProperties;

    public KafkaAutoCreateTopicConfiguration(KafkaTopicProperties kafkaTopicProperties) {
        this.kafkaTopicProperties = kafkaTopicProperties;
    }

    @Bean
    public KafkaAdmin.NewTopics newTopics() {
        Set<KafkaTopicProperties.Topic> topics = kafkaTopicProperties.getTopics();
        NewTopic[] newTopics = new NewTopic[topics.size()];
        int i = 0;
        for (KafkaTopicProperties.Topic topic : topics) {
            newTopics[i++] = new NewTopic(topic.getName(), topic.getNumPartitions(), topic.getReplicationFactor());
        }

        return new KafkaAdmin.NewTopics(newTopics);
    }

}