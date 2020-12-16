package cn.nroma;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Request {
	private String request;
	private String method;
	private String url;
	private String queryStr;
	private Map<String, List<String>> parameterMap;
	private final String CRLF = "\r\n";

	public Request(Socket socket) throws IOException {
		this(socket.getInputStream());
	}

	public Request(InputStream inputStream) {
		// TODO Auto-generated constructor stub
		parameterMap = new HashMap<String, List<String>>();
		byte[] datas = new byte[1024*1024*10];
		int len;
		try {
			len = inputStream.read(datas);
			System.out.println(len);
			this.request = new String(datas, 0, len);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		parseRequestInfo();
	}

	// GET /login.html?uname=aaaa&pwd=shsxt HTTP/1.1
	// Host: localhost:8888
	// Connection: keep-alive
	// Upgrade-Insecure-Requests: 1
	// User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36
	// (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36
	// Accept:
	// text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
	// Accept-Encoding: gzip, deflate, sdch, br
	// Accept-Language: zh-CN,zh;q=0.8
	private void parseRequestInfo() {
		// TODO Auto-generated method stub
		this.method = this.request.substring(0, this.request.indexOf("/")).toLowerCase();
		this.method = this.method.trim();
		int startIdx = this.request.indexOf("/") + 1;
		int endIdx = this.request.indexOf("HTTP/");
		this.url = this.request.substring(startIdx, endIdx).trim();
		int queryIdx = this.url.indexOf("?");
		if (queryIdx >= 0) {
			String[] urlArray = this.url.split("\\?");
			this.url = urlArray[0];
			this.queryStr = urlArray[1];
		}
		// 如果是post，则请求参数可能在请求体中，那么就找最后一次出现的回车换行
		if (this.method.equals("post")) {
			String qStr = this.request.substring(this.request.lastIndexOf(CRLF)).trim();
			if (queryStr == null) {
				queryStr = qStr;
			} else {
				queryStr += "&" + qStr;
			}
		}
		queryStr = null == queryStr ? "" : queryStr;
		converMap();
	}

	private void converMap() {
		// TODO Auto-generated method stub
		String[] keyValues = this.queryStr.split("&");
		for (String queryStr : keyValues) {
			String[] kv = queryStr.split("=");
			// 拷贝生成一个新数组，下标0，1。
			kv = Arrays.copyOf(kv, 2);
			String key = kv[0];
			// 赋值并解码
			String value = kv[1] == null ? null : decode(kv[1], "utf-8");
			// 存储map
			// 判断值是否存在
			if (!parameterMap.containsKey(key)) {
				parameterMap.put(key, new ArrayList<String>());
			}
			parameterMap.get(key).add(value);
		}
	}

	private String decode(String value, String enc) {
		// TODO Auto-generated method stub
		try {
			return java.net.URLDecoder.decode(value, enc);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 通过传入key，获取多个value
	public String[] getParameterValues(String key) {
		List<String> values = this.parameterMap.get(key);
		if (values == null || values.size() < 1) {
			return null;
		}
		return values.toArray(new String[0]);
	}

	// 通过传入key，获取单个value
	public String getParameter(String key) {
		String[] value = getParameterValues(key);
		return value == null ? null : value[0];
	}

	public String getMethod() {
		return method;
	}

	public String getUrl() {
		return url;
	}

	public String getQueryStr() {
		return queryStr;
	}

}
