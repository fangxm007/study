package org.fangxm.util;

import static java.lang.System.out;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.StopWatch;

public class Commons {
	
	public void fileutil() {
		try {
			/**
FileUtils.writeStringToFile(new File(path), "UTF-8");
FileUtils.copyDirectory(oldFile, newFile);
FileUtils.deleteDirectory(newFile);oldFile.renameTo(newFile);
			 */
			//String text = FileUtils.readFileToString(new File("e:/down/singlepsguidedatasource.json"), "UTF-8");
			//System.out.println(text);
			//String baidu = IOUtils.toString(new URL("http://www.baidu.com/").openStream(), "GBK");
			//System.out.println(baidu);
			long freeSpace = FileSystemUtils.freeSpaceKb("d:/");
			System.out.println(freeSpace);
			
			/*org.apache.commons.io.ThreadMonitor
			启动一个线程监控主线程的执行时间，避免主线程执行时间过长，超时直接将主线程interrupt
			Thread monitor = ThreadMonitor.start(timeout);
			Process proc = Runtime.getRuntime().exec(cmdAttribs);
			proc.waitFor();
			ThreadMonitor.stop(monitor);*/
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		//IOUtils.closeQuietly(in);
	}
	
	public void string() {
		System.out.println(StringUtils.isBlank("111"));
		String[] aa = StringUtils.split("11.22", ".");
		for (String a : aa) {
			System.out.println(a);
		}
	}
	
	public void base64() {
		/*String data = "中国";
		try {
			String str = new String(Base64.encodeBase64(data.getBytes("UTF-8")));
			System.out.println(str);
			String str2 = new String(Base64.decodeBase64(str.getBytes()), "UTF-8");
			System.out.println(str2);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}*/
		try {
			byte[] bs = IOUtils.toByteArray(new FileInputStream(new File("E:/down/Facebook新广告产品原理.jpg")));
			String str = new String(Base64.encodeBase64(bs));
			System.out.println(str);
			byte[] bs2 = Base64.decodeBase64(str.getBytes());
			FileUtils.writeByteArrayToFile(new File("E:/down/Facebook新广告产品原理111.jpg"), bs2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void random() {
		System.out.println(RandomStringUtils.randomNumeric(6));
		System.out.println(RandomStringUtils.random(20, true, false));
	}
	
	public void watch() {
		StopWatch watch = new StopWatch();
    	watch.start();
    	StringBuilder b = new StringBuilder();
    	for (int i = 0; i < 10000; i++) {
    		//System.out.println( "Hello World!" );
    		b.append("1");
    	}
    	watch.stop();  
        out.printf("%10d", watch.getTime());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Commons c = new Commons();
		//c.watch();
		//c.string();
		//c.random();
		//c.base64();
		c.fileutil();
	}

}
