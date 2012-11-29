package org.fangxm.util;

public class StackTrace {
	
	private void stackTrace() {
		StackTraceElement[] eles = Thread.currentThread().getStackTrace();
		for (StackTraceElement ele : eles) {
			System.out.println(ele.getClassName() + "." + ele.getMethodName());
		}
	}
	
	private void currentMethod() {
		StackTraceElement[] eles = Thread.currentThread().getStackTrace();
		if (eles.length > 2) {
			System.out.println(eles[1].getMethodName());
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StackTrace a = new StackTrace();
		a.stackTrace();
		a.currentMethod();
	}

}
