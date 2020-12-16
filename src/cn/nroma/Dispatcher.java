package cn.nroma;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Dispatcher implements Runnable {
	
	private Socket socket;
	private Request request;
	private Response response;
	private cn.nroma.Servlet servlet;
	public Dispatcher(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket=socket;
		
		try {
			request=new Request(socket);
			response=new Response(socket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.release();
		}
	}
	@Override
	public void run(){
		// TODO Auto-generated method stub
		//没有指定url，默认访问到index
		if(request.getUrl()==null||request.getUrl().equals("")) {
			try {
				InputStream inputStream=Thread.currentThread().getContextClassLoader().getResourceAsStream("index.html");
				response.print(new String(inputStream.readAllBytes()));
				response.pushToBrowser(200);
				inputStream.close();
				release();
				return ;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			servlet=WebApp.getServletFromUrlPattern(request.getUrl());
		}catch (NullPointerException e) {
			// TODO: handle exception
		}
		if(servlet!=null) {
			servlet.service(request, response);
			response.pushToBrowser(200);
		}else {
			try {
				InputStream inputStream=Thread.currentThread().getContextClassLoader().getResourceAsStream("error.html");
				response.print(new String(inputStream.readAllBytes()));
				response.pushToBrowser(404);
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				response.pushToBrowser(500);
				e.printStackTrace();
			}

		}
		release();
	}
	
	//释放资源
	private void release() {
		// TODO Auto-generated method stub
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
