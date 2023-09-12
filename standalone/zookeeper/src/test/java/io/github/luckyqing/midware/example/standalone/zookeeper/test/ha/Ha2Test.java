package io.github.luckyqing.midware.example.standalone.zookeeper.test.ha;

import io.github.luckyqing.midware.example.standalone.zookeeper.service.ZkLeaderLatch;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class Ha2Test {

    @Autowired
    private ZkLeaderLatch zkLeaderLatch;

    @Test
    public void test1() throws InterruptedException {
        AtomicInteger ai = new AtomicInteger(0);
        while (true) {
            if (zkLeaderLatch.isLeader()) {
                System.out.println("exec count : " + ai.getAndIncrement());
                break;
            } else {
                TimeUnit.SECONDS.sleep(2);
            }
        }

        TimeUnit.SECONDS.sleep(3600);
    }

}