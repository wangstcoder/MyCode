package haierUH;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

public class TestMain {

	static GetCountryCityByIp getCounty = new GetCountryCityByIp();
	static List countries = new ArrayList();
	static ReadFromFile readInfo = null;
	static TestMain test = new TestMain();

	/**
	 * @param args
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List infoLine = new ArrayList();
		readInfo = new ReadFromFile();
		infoLine = readInfo.readFileByLines("D:\\macIP4.txt");
		// System.out.println(infoLine.get(0));
		// String test = (String) infoLine.get(0);
		// String[] strArray = test.split("\t");
		// System.out.println(strArray[0]);

		String resultInfo = null;
		HTTPSend httpSend = new HTTPSend();
		List<HTTPParam> list = null;
		HTTPParam ipParam = null;
		String typeId = null;
		String mac = null;
		String ip = null;
		String url = "http://ip.taobao.com//service/getIpInfo.php";
		for (int i = 0; i < infoLine.size(); i++) {
			System.out.println(infoLine.get(i) + " ************ ");
			String line = (String) infoLine.get(i);
			String[] strArray = line.split("\t");
			typeId = strArray[0];
			mac = strArray[1];
			ip = strArray[2];
			list = new ArrayList<HTTPParam>();
			ipParam = new HTTPParam("ip", ip);
			list.add(ipParam);
			try {
				Thread.sleep(300);
				String result = httpSend.sendGet(url, list);
				System.out.println(jsonObject(result));
				resultInfo = jsonObject(result);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				try {
					Thread.sleep(300);
					String result = httpSend.sendGet(url, list);
					resultInfo = jsonObject(result);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					// 写入到异常文件
					writeFile("D:\\exceRresultIP4.txt", typeId + "\t" + mac
							+ "\t" + ip + "\r\n");
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			writeFile("D:\\succeRresultIP4.txt", typeId + "\t" + mac + "\t"
					+ resultInfo + "\r\n");// succeRresultIP
		}

		System.out.println("ok");

		// test.readIP();
		// test.getCountryInfo("89.64.35.242");
		// for (int i = 0; i < countries.size(); i++) {
		// System.out.println(countries.get(i));
		// }
		// System.out.println(countries.size());

	}

	/**
	 * 追加文件：使用FileWriter
	 * 
	 * @param fileName
	 * @param content
	 */
	public static void writeFile(String fileName, String content) {
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

	// jsonData就是对象格式的json数据
	public static String jsonObject(String jsonData) {
		String country = null;
		String ip = null;
		try {
			JSONObject josonObject = new JSONObject(jsonData);
			JSONObject dataObject = josonObject.getJSONObject("data");
			country = dataObject.getString("country");
			ip = dataObject.getString("ip");
			// System.out.print(value);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ip + "\t" + country;

	}

	// 读取文件里的ip获取到国家信息
	public void readIP() {
		List infoLine = new ArrayList();
		readInfo = new ReadFromFile();
		infoLine = readInfo.readFileByLines("D:\\testIP1.txt");

		for (int i = 0; i < infoLine.size(); i++) {
			System.out.println(infoLine.get(i));
			// test.getCountryInfo(infoLine.get(i).toString());

		}

	}

	// 将ip获取的国家信息逐一添加到countries中
	public void getCountryInfo(String ip) {
		String[] infoLine = new String[4];
		infoLine[3] = "\r\n";
		System.out.println("ip: " + ip);
		String info = getCounty.getCountryCityByIp(ip);// 62.98.13.36,89.64.35.242
		String information = StringUtils.strip(info, "[]");
		String[] infos = StringUtils.split(information, ",");
		for (int i = 0; i < infos.length; i++) {
			switch (i) {
			case 0:
				infoLine[i] = infos[i] + ",";
				// System.out.println(infos[i]);
				continue;
			case 1:
				String[] infos2 = StringUtils.split(infos[i], " ");
				for (int j = 0; j < infos2.length; j++) {
					infoLine[i + j] = infos2[j] + ",";
					// System.out.println(infos2[j]);
				}
			}

		}
		// countries.add(Arrays.toString(infoLine));
		// System.out.println(Arrays.toString(infoLine));
		for (int i = 0; i < infoLine.length; i++) {
			System.out.print(infoLine[i]);
		}
	}
}
