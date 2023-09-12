package io.github.luckyqing.midware.example.standalone.zookeeper.configuration;

import io.github.luckyqing.midware.example.standalone.zookeeper.properties.ZookeeperProperties;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZookeeperAutoConfiguration {

    @Bean
    public CuratorFramework curatorFramework(final ZookeeperProperties zookeeperProperties) {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(zookeeperProperties.getHost())
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(500, 5))
                .build();
        curatorFramework.start();

        return curatorFramework;
    }

}