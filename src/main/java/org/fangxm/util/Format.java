package org.fangxm.util;

import java.text.MessageFormat;
import java.util.Date;


/**
 * code from: 网络
 * @author Administrator
 *
 */
public class Format {

	/**
	 * @param args
关于MessageFormat.format方法： 
每调用一次MessageFormat.format方法，都会新创建MessageFormat的一个实例，相当于MessageFormat只使用了一次。
MessageFormat temp = new MessageFormat(pattern);
return temp.format(arguments);
如果要重复使用某个MessageFormat实例，可以:
MessageFormat messageFormat = new MessageFormat(message);
messageFormat.format(array);
	 * 
	 */
	public static void main(String[] args) {
		String message = "{0}{1}{2}{3}{4}{5}{6}{7}{8}{9}{10}{11}{12}{13}{14}{15}{16}";  
		Object[] array = new Object[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q"};  
		String value = MessageFormat.format(message, array);
		System.out.println(value); 
		
		float planet = 0.7f;
		int aa = 1;
		String event = "a disturbance in the Force";
		String result = MessageFormat.format("At {1,time} on {1,date}, there was {2} on planet {0,number,percent}.  {3,number,integer}  '{0}' ''11''", planet, new Date(), event, aa);
		System.out.println(result);

	}

}
