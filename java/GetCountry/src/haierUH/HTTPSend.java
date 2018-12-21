package haierUH;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

public class HTTPSend {
	public String sendGet(String url, List<HTTPParam> list) throws IOException,
			URISyntaxException {
		StringBuffer buffer = new StringBuffer();
		StringBuffer result = new StringBuffer();
		URL httpUrl = null;
		URLConnection connection = null;
		BufferedReader bufferedReader = null;
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				buffer.append(list.get(i).getKey())
						.append("=")
						.append(URLEncoder.encode(list.get(i).getValue(),
								"utf-8"));
				if ((i + 1) < list.size()) {
					buffer.append("&");
				}
			}
			url = url + "?" + buffer.toString();
		}
		// httpUrl = new URL(url);
		httpUrl = new URL(new URI(url).toASCIIString());
		connection = httpUrl.openConnection();
		connection.setRequestProperty("accept", "application/json");
		connection.setRequestProperty("connection", "keep-alive");
		connection.setConnectTimeout(20000);
		// connection.setReadTimeout(3000);
		// connection.setRequestProperty("user-agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0");
		connection.connect();
		bufferedReader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			result.append(line);
		}
		bufferedReader.close();
		return result.toString();
	}

	public String sendPost(String url, List<HTTPParam> list) throws IOException {
		StringBuffer buffer = new StringBuffer();
		StringBuffer result = new StringBuffer();
		URL httpUrl = null;
		URLConnection connection = null;
		PrintWriter printWriter = null;
		BufferedReader bufferedReader;
		httpUrl = new URL(url);
		connection = httpUrl.openConnection();
		connection
				.setRequestProperty("accept",
						"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		connection.setRequestProperty("connection", "keep-alive");
		connection
				.setRequestProperty("user-agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0");
		connection.setDoOutput(true);
		connection.setDoInput(true);
		printWriter = new PrintWriter(connection.getOutputStream());
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				buffer.append(list.get(i).getKey())
						.append("=")
						.append(URLEncoder.encode(
								list.get(i).getValue() != null ? list.get(i)
										.getValue() : "", "utf-8"));
				if ((i + 1) < list.size()) {
					buffer.append("&");
				}
			}
		}
		printWriter.print(buffer.toString());
		printWriter.flush();
		connection.connect();
		bufferedReader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			result.append(line);
		}
		bufferedReader.close();
		return result.toString();
	}

	public String sendUploadPost(String url, List<HTTPParam> list)
			throws IOException {
		StringBuffer buffer = new StringBuffer();
		StringBuffer result = new StringBuffer();
		URL httpUrl = null;
		URLConnection connection = null;
		PrintWriter printWriter = null;
		BufferedReader bufferedReader;
		httpUrl = new URL(url);
		connection = httpUrl.openConnection();
		connection
				.setRequestProperty("accept",
						"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		connection.setRequestProperty("connection", "keep-alive");
		connection
				.setRequestProperty("user-agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0");
		connection.setDoOutput(true);
		connection.setDoInput(true);
		printWriter = new PrintWriter(connection.getOutputStream());
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				buffer.append(list.get(i).getKey())
						.append("=")
						.append(URLEncoder.encode(list.get(i).getValue(),
								"utf-8"));
				if ((i + 1) < list.size()) {
					buffer.append("&");
				}
			}
		}
		printWriter.print(buffer.toString());
		printWriter.flush();
		connection.connect();
		bufferedReader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			result.append(line);
		}
		bufferedReader.close();
		return result.toString();
	}

	public InputStream sendPostImg(String url, List<HTTPParam> list)
			throws IOException {
		StringBuffer buffer = new StringBuffer();
		URL httpUrl = null;
		URLConnection connection = null;
		PrintWriter printWriter = null;
		httpUrl = new URL(url);
		connection = httpUrl.openConnection();
		connection
				.setRequestProperty("accept",
						"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		connection.setRequestProperty("connection", "keep-alive");
		connection
				.setRequestProperty("user-agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0");
		connection.setDoOutput(true);
		connection.setDoInput(true);
		printWriter = new PrintWriter(connection.getOutputStream());
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				buffer.append(list.get(i).getKey())
						.append("=")
						.append(URLEncoder.encode(list.get(i).getValue(),
								"utf-8"));
				if ((i + 1) < list.size()) {
					buffer.append("&");
				}
			}
		}
		printWriter.print(buffer.toString());
		printWriter.flush();
		connection.connect();

		InputStream stream = connection.getInputStream();
		return stream;
	}

	// public static void main(String[] args) {
	// CommonParam common = new CommonParam();
	// List<HTTPParam> list = new ArrayList<HTTPParam> ();
	// HTTPParam userCodeParam = new HTTPParam("userCode","sa");
	// HTTPParam passwordParam = new HTTPParam("password","123");
	// list.add(userCodeParam);
	// list.add(passwordParam);
	// try {
	// String studentUrl = common.getString("studentUrl");
	// String result = HTTPSend.sendPost(studentUrl+"login.json?op=login",list);
	// System.err.println(result);
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
}
