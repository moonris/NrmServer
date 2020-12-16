package cn.nroma;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket serverSocket;
	private boolean isRunning;
	private int port=8080;
	public static void main(String[] args) {
		Server server=new Server();
		server.start();
	}
	private void start() {
		// TODO Auto-generated method stub
		try {
			serverSocket=new ServerSocket(port);
			isRunning=true;
			receive();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			stop();
		}
	}
	private void receive() {
		// TODO Auto-generated method stub
		while(isRunning) {
			try {
				
				Socket socket=serverSocket.accept();
				
				new Thread(new Dispatcher(socket)).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void stop() {
		isRunning=false;
		try {
			this.serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
