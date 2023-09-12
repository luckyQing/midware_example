package io.github.luckyqing.midware.example.standalone.zookeeper.test;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.charset.StandardCharsets;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ZookeeperTest {

    @Autowired
    private CuratorFramework curatorFramework;
    String node = "/data/midware/example";
    String data = "hello world!";

    /**
     * 判断znode是否存在
     *
     * @throws Exception
     */
    @Test
    public void testCheckExists() throws Exception {
        Stat stat = curatorFramework.checkExists().forPath(node);
        System.out.println(stat);
    }

    /**
     * 创建一个znode
     *
     * @throws Exception
     */
    @Test
    public void testCreate() throws Exception {
        curatorFramework.create()
                .creatingParentContainersIfNeeded()
                /*
                创建模式：常用的有
                    PERSISTENT：持久化节点，客户端与zookeeper断开连接后，该节点依旧存在，只要不手动删除，该节点就会永远存在。
                    PERSISTENT_SEQUENTIAL：持久化顺序编号目录节点，客户端与zookeeper断开连接后，该节点依旧存在，只是zookeeper给该节点名称进行顺序编号。
                    EPHEMERAL：临时目录节点，客户端与zookeeper断开连接后，该节点被删除。
                    EPHEMERAL_SEQUENTIAL：临时顺序编号目录节点，客户端与zookeeper断开连接后，该节点被删除，只是zookeeper给该节点名称进行顺序编号。
                */
                .withMode(CreateMode.PERSISTENT)
                .forPath(node);
    }

    /**
     * 设置节点数据
     *
     * @throws Exception
     */
    @Test
    public void testSetData() throws Exception {
        curatorFramework.setData().forPath(node, data.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 删除节点
     *
     * @throws Exception
     */
    @Test
    public void testDelete() throws Exception {
        // 删除节点
        curatorFramework.delete().forPath(node);

        // 删除节点及其子节点的数据
        curatorFramework.delete().deletingChildrenIfNeeded().forPath(node);
    }

    /**
     * 删除节点及其子节点的数据
     *
     * @throws Exception
     */
    @Test
    public void testDeletingChildrenIfNeeded() throws Exception {
        curatorFramework.delete().deletingChildrenIfNeeded().forPath(node);
    }

    /**
     * 获取节点的数据
     *
     * @throws Exception
     */
    @Test
    public void testGetData() throws Exception {
        byte[] bytes = curatorFramework.getData().forPath(node);
        System.out.println(new String(bytes));
    }

    /**
     * 获取当前节点的子节点数据
     *
     * @param node 节点名称
     */
    public void testGetChildren(String node) throws Exception {
        List<String> list = curatorFramework.getChildren().forPath(node);

        System.out.println(list);
    }

}