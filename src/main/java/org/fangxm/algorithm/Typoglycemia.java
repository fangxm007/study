package org.fangxm.algorithm;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * code from: https://gist.github.com/4044530
 * http://blog.zhaojie.me/2012/11/how-to-generate-typoglycemia-text.html
 * 如何生成一段Typoglycemia文本？
 * @author Administrator
 *
 */
public class Typoglycemia {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//ThreadLocalRandom random = ThreadLocalRandom.current();
		//String text = "I couldn't believe that I could actually understand what I was reading: the phenomenal power of the human mind. According to a research team at Cambridge University, it doesn't matter in what order the letters in a word are, the only important thing is that the first and last letter be in the right place. The rest can be a total mess and you can still read it without a problem. This is because the human mind does not read every letter by itself, but the word as a whole. Such a condition is appropriately called Typoglycemia.Amazing, huh? Yeah and you always thought spelling was important.";
		//https://gist.github.com/4044530
		//Java 8/lambda 
/*		String result = String.join(" ",
				text.splitAsStream(" ")
					.map(
						s -> s.length() <= 3
						? s : s.charAt(0)
						+ s.substring(1, s.length() - 1)
						.asChars()
						.sorted(comparing((Character _) -> random.nextInt()))
						.fold(
							() -> new StringBuilder(s.length() - 2),
							(sb, c) -> sb.append(c.charValue()),
							(a, b) -> a)
						+ s.charAt(s.length() - 1)
					).into(new ArrayList<String>()));*/
		//System.out.println(result);
		String text = "couldn't";
		//char aa = 62;
		//System.out.println(aa);
		System.out.println(getRandomArray(text));
	}
	
	public static char[] getRandomArray(String text) {
		char[] sequence = text.toCharArray();
		int no = 0;
		if(sequence[sequence.length-1]>= 61 && sequence[sequence.length-1] <= 122)
			no = sequence.length;
		else
			no = sequence.length-1;
		if (no > 3) {
			Random random = new Random();
			for (int i = 1; i < no - 1; i++) {
				int p = random.nextInt(no - 1);
				if (p != 0 && p != no) {
					char tmp = sequence[i];
					if (tmp >= 61 && tmp <= 122 && sequence[p] >= 61
						&& sequence[p] <= 122) {
						sequence[i] = sequence[p];
						sequence[p] = tmp;
					}
				}
			}
			random = null;
		}
		return sequence;
	}

}
