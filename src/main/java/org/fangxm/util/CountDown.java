package org.fangxm.util;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * code from: 网络
 * concurrent包里面的CountDownLatch其实可以把它看作一个计数器，
 * 只不过这个计数器的操作是原子操作，同时只能有一个线程去操作这个计数器，也就是同时只能有一个线程去减这个计数器里面的值。
   CountDownLatch的一个非常典型的应用场景是：有一个任务想要往下执行，但必须要等到其他的任务执行完毕后才可以继续往下执行。
         假如我们这个想要继续往下执行的任务调用一个CountDownLatch对象的await()方法，
         其他的任务执行完自己的任务后调用同一个CountDownLatch对象上的countDown()方法，
         这个调用await()方法的任务将一直阻塞等待，直到这个CountDownLatch对象的计数值减到0为止。
 * @author Administrator
 *
 */
public class CountDown {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();  
		  
        ExecutorService executor = Executors.newCachedThreadPool();  
  
        CountDownLatch latch = new CountDownLatch(3);  
        CountDownLatch doneLatch = new CountDownLatch(1);  
        Worker w1 = new Worker(latch, "w1");  
        Worker w2 = new Worker(latch, "w2");  
        Worker w3 = new Worker(latch, "w3");  
  
        Boss boss = new Boss(latch, doneLatch);  
  
        executor.execute(w1);  
        executor.execute(w2);  
        executor.execute(w3);  
        executor.execute(boss);  
  
        executor.shutdown();  
        try {  
            doneLatch.await();  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
        System.out.println(System.currentTimeMillis() - startTime);  
	}

}


class Worker implements Runnable {  
    private CountDownLatch downLatch;  
    private String name;  
  
    public Worker(CountDownLatch downLatch, String name) {  
        this.downLatch = downLatch;  
        this.name = name;  
    }  
  
    public void run() {  
        this.doWork();  
        try {  
            TimeUnit.SECONDS.sleep(new Random().nextInt(10));  
        } catch (InterruptedException ie) {  
        }  
        System.out.println(this.name + "活干完了！");  
        this.downLatch.countDown();  
  
    }  
  
    private void doWork() {  
        System.out.println(this.name + "正在干活!");  
    }  
}  
  
class Boss implements Runnable {  
  
    private CountDownLatch downLatch;  
    private CountDownLatch doneLatch;  
    public Boss(CountDownLatch downLatch,CountDownLatch doneLatch) {  
        this.downLatch = downLatch;  
        this.doneLatch = doneLatch;  
    }  
  
    public void run() {  
        System.out.println("老板正在等所有的工人干完活...");  
        try {  
            this.downLatch.await();  
        } catch (InterruptedException e) {  
        }  
        System.out.println("工人活都干完了，老板开始检查了！");  
        doneLatch.countDown();  
    }  
  
} 