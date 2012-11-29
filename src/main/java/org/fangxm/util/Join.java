package org.fangxm.util;

/**
 * 用join也可以实现等待多个线程一起完成再往下执行的效果。concurrent包里面的CountDownLatch利用计数也可实现类似功能
 * @author Administrator
 *
 */
public class Join {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AThread aa = new AThread("aa", 3000);
		AThread bb = new AThread("bb", 2000);
		AThread cc = new AThread("cc", 1000);
		aa.start();
		bb.start();
		cc.start();
		//System.out.println("main start over");
		try {
			aa.join();
			System.out.println("aa over");
			bb.join();
			System.out.println("bb over");
			cc.join();
			System.out.println("cc over");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("main run over");
	}

}

class AThread extends Thread {
	private String name;
	private long ms;
	public AThread(String name, long ms) {
		this.name = name;
		this.ms = ms;
	}
	public void run() {
		System.out.println("run begin:" + name);
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("run end:" + name);
	}
}