package cn.nroma;

public class LoginServlet implements Servlet{

	@Override
	public void service(Request request, Response response) {
		// TODO Auto-generated method stub
		response.print("<html>"); 
		response.print("<head>"); 
		response.print("<meta http-equiv=\"content-type\" content=\"text/html;charset=utf-8\">" ); 
		response.print("<title>");
		response.print("LoginServlet");
		response.print("</title>");
		response.print("</head>");
		response.print("<body>");
		response.print("LoginServlet Page Test");
		response.print("</body>");
		response.print("</html>");
	}
	

}
