package haierUH;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ReadFromFile {

	/**
	 * 按行读取
	 */
	public List readFileByLines(String fileName) {
		List infoLine = new ArrayList();
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			// System.out.println("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// һ�ζ���һ�У�ֱ������nullΪ�ļ�����
			while ((tempString = reader.readLine()) != null) {
				// ��ʾ�к�
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

	public static void saveFile(File fileName, String content)
			throws IOException {
		Writer writer = new FileWriter(fileName, false);
		writer.write(content);
		writer.close();
	}

}
