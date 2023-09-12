package io.github.luckyqing.midware.example.standalone.zookeeper.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * zookeeper配置
 *
 * @author collin
 * @date 2023-09-11
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "midware.example.zk")
public class ZookeeperProperties {

    /**
     * 地址server地址
     */
    private String host;
    private String latchPath;

}