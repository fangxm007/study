package org.fangxm.benchmark;
import java.util.concurrent.*;

/**
 * code from: http://my.oschina.net/wenshao/blog/90508
 * 乐观锁的在激烈竞争的时候性能都很糟糕，乐观锁应使用在非激烈竞争的场景，为乐观锁优化激烈竞争下的性能，是错误的方向，
 * 因为如果需要激烈竞争，就应该使用悲观锁。 
 * 
以下是一个JDK中内置乐观锁悲观锁的对照表： 
乐观锁           ----->  悲观锁 
AtomicInteger   ----->  Lock + volatile int 
AtomicLong      ----->  Lock + volatile long 
AtomicReference ----->  Lock + volatile 
LinkedTransferQueue -----> LinkedBlockingQueue 
 * @author Administrator
 * 刚刚用这个源码在JDK6跑了一下 跟你的结果相反 慢5-6倍 不过LinkedTransferQueue用的是BoneCP中的jsr166y.LinkedTransferQueue 
 *
 */
public class BlockingQueueTest {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 3; ++i) {
            loop();
        }       
    }
    private static void loop() throws InterruptedException {
        final BlockingQueue<Object> queue = new LinkedBlockingQueue<Object>();
//      final BlockingQueue<Object> queue = new LinkedTransferQueue<Object>();
        for (int i = 0; i < 10; ++i) {
            queue.put(i);
        }

        final int THREAD_COUNT = 50;
        final CountDownLatch startLatch = new CountDownLatch(1);
        final CountDownLatch endLatch = new CountDownLatch(THREAD_COUNT);
        for (int i = 0; i < THREAD_COUNT; ++i) {
            Thread thread = new Thread() {
                public void run() {
                    try {
                        startLatch.await();
                    } catch (InterruptedException e) { e.printStackTrace(); }
                    try {
                        for (int i = 0; i < 1000 * 20; ++i) {
                            Object item = queue.take();
                            queue.put(item);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        endLatch.countDown();
                    }
                }
            };
            thread.start();
        }
        long startMillis = System.currentTimeMillis();
        startLatch.countDown();
        endLatch.await();
        long millis = System.currentTimeMillis() - startMillis;
        System.out.println(queue.getClass().getName() + " : " + millis);
    }
}
