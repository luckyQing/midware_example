package io.github.luckyqing.midware.example.standalone.kafka.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@ConfigurationProperties(prefix = "kafka")
public class KafkaTopicProperties {

    private Set<Topic> topics = new LinkedHashSet<>();

    @Data
    public static class Topic {
        /**
         * topic名称
         */
        private String name;
        /**
         * 分区数
         */
        private Integer numPartitions = 1;
        /**
         * 副本
         */
        private Short replicationFactor = 1;
    }

}