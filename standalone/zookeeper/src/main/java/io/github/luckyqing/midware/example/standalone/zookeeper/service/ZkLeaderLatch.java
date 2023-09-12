package io.github.luckyqing.midware.example.standalone.zookeeper.service;

import io.github.luckyqing.midware.example.standalone.zookeeper.properties.ZookeeperProperties;
import lombok.RequiredArgsConstructor;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.net.InetAddress;

@Service
@RequiredArgsConstructor
public class ZkLeaderLatch implements InitializingBean {

    private LeaderLatch leaderLatch;
    private final ZookeeperProperties zookeeperProperties;
    private final CuratorFramework curatorFramework;

    @Override
    public void afterPropertiesSet() throws Exception {
        final String id = String.format("zkLatchClient#%s", InetAddress.getLocalHost().getHostAddress());
        leaderLatch = new LeaderLatch(curatorFramework, zookeeperProperties.getLatchPath(), id);
        LeaderLatchListener leaderLatchListener = new LeaderLatchListener() {
            public void isLeader() {
                System.out.println("客户端：" + id + "成为主节点");
            }

            public void notLeader() {
                System.out.println("客户端：" + id + "不是主节点！");
            }
        };
        leaderLatch.addListener(leaderLatchListener);
        leaderLatch.start();
    }

    public boolean isLeader() {
        return leaderLatch.hasLeadership();
    }

}