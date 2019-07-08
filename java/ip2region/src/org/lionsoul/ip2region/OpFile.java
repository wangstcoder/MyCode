package org.lionsoul.ip2region;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class OpFile {

	/**
	 * 按行读取
	 */
	public List readFileByLines(String fileName) {
		List infoLine = new ArrayList();
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
		
			while ((tempString = reader.readLine()) != null) {
				// System.out.println("line " + line + ": " + tempString);
				infoLine.add(tempString);
				line++;

			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}

		/*
		 * for (int i = 0; i < infoLine.size(); i++) {
		 * System.out.println(infoLine.get(i)); }
		 */
		return infoLine;
	}

	public void saveFile(File fileName, String content)
			throws IOException {
		Writer writer = new FileWriter(fileName, false);
		writer.write(content);
		writer.close();
	}
	
	
	/**
	 * 追加文件：使用FileWriter
	 * 
	 * @param fileName
	 * @param content
	 */
	public void writeFile(String fileName, String content) {
		FileWriter writer = null;
		try {
			// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			writer = new FileWriter(fileName, true);
			writer.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
