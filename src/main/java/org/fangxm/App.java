package org.fangxm;

import static java.lang.System.out;
import org.apache.commons.lang.time.StopWatch; 
/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
    	StopWatch watch = new StopWatch();
    	watch.start();
    	for (int i = 0; i < 10000; i++) {
    		System.out.println( "Hello World!" );
    	}
    	watch.stop();  
        out.printf("%10d", watch.getTime());
    }
}
