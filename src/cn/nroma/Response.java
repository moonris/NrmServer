package cn.nroma;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

public class Response {
	private BufferedWriter bufferedWriter;
	private StringBuilder content;
	private StringBuilder headInfo;
	private int len;
	
	private final String BLANK=" ";
	private final String CRLF ="\r\n";
	
	public Response(){
		content =new StringBuilder();
		headInfo =new StringBuilder();
		len=0;
	}
	public Response(Socket socket) {
		this();
		try {
			bufferedWriter=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			headInfo=null;
		}
	}
	public Response(OutputStream outputStream) {
		this();
		bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream));
	}
	//动态添加内容,不带换行
	public Response print(String info) {
		content.append(info);
		len+=info.getBytes().length;
		return this;
	}
	//动态添加内容，带换行
	public Response println(String info) {
		content.append(info).append(CRLF);
		len+=info.getBytes().length;
		return this;
	}
	//推送响应信息
	public void pushToBrowser(int code) {
		if(headInfo==null) {
			code=500;
		}
		try {
			createHeadInfo(code);
			bufferedWriter.append(headInfo);
			bufferedWriter.append(content);
			bufferedWriter.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//传入状态码
	private void createHeadInfo(int code) {
		headInfo.append("HTTP/1.1").append(BLANK);
		headInfo.append(code).append(BLANK);
		switch (code) {
			case 200:
				headInfo.append("OK").append(CRLF);
				break;
			case 404:
				headInfo.append("NOT FOUND").append(CRLF);
				break;
			case 500:
				headInfo.append("SERVER ERROR").append(CRLF);
				break;
		}
		//2、响应头(最后一行存在空行):
		headInfo.append("Date:").append(new Date()).append(CRLF);
		headInfo.append("Server:").append("shsxt Server/0.0.1;charset=GBK").append(CRLF);
		headInfo.append("Content-type:text/html").append(CRLF);
		headInfo.append("Content-length:").append(len).append(CRLF);
		headInfo.append(CRLF);	
	}
	

}
