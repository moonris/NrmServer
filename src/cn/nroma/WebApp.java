package cn.nroma;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import cn.nroma.*;


public class WebApp {
	private static WebContext webContext;
	static {
		try {
			SAXParserFactory saxParserFactory=SAXParserFactory.newInstance();
			SAXParser saxParser=saxParserFactory.newSAXParser();
			WebHandler webHandler=new WebHandler();
			saxParser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("web.xml"), webHandler);
			webContext=new WebContext(webHandler.getServlets(), webHandler.getServletMappings());
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static cn.nroma.Servlet getServletFromUrlPattern(String urlPattern) {
		String className = webContext.getClass("/"+urlPattern);
		Class class1;
		try {
			class1=Class.forName(className);
			cn.nroma.Servlet servlet=(cn.nroma.Servlet) class1.getConstructor().newInstance();
			return servlet;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
}
