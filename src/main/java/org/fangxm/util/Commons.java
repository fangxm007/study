package org.fangxm.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

public class Commons {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(StringUtils.isBlank("111"));
		try {
			/**
FileUtils.writeStringToFile(new File(path), "UTF-8");
FileUtils.copyDirectory(oldFile, newFile);
FileUtils.deleteDirectory(newFile);oldFile.renameTo(newFile);
			 */
			String text = FileUtils.readFileToString(new File("e:/down/singlepsguidedatasource.json"), "UTF-8");
			System.out.println(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
