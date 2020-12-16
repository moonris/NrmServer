package cn.nroma;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import cn.nroma.model.Servlet;
import cn.nroma.model.ServletMapping;

public class WebHandler extends DefaultHandler{
	private List<Servlet> servlets=new ArrayList<Servlet>();
	private List<ServletMapping> servletMappings=new ArrayList<ServletMapping>();
	private Servlet servlet;
	private ServletMapping servletMapping;
	private String tag;
	private boolean isServletMapping=false;
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		if(qName!=null) {
			tag=qName;
			if(tag.equals("servlet")) {
				servlet=new Servlet();
				isServletMapping=false;
			}else if(tag.equals("servlet-mapping")) {
				servletMapping=new ServletMapping();
				isServletMapping=true;
			}
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		if(qName!=null) {
			if(qName.equals("servlet")) {
				servlets.add(servlet);
			}else if(qName.equals("servlet-mapping")) {
				servletMappings.add(servletMapping);
			}
		}
		tag=null;
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		String contents=new String(ch,start,length).trim();
		if(tag!=null) {
			if(isServletMapping) {
				if(tag.equals("servlet-name")) {
					servletMapping.setServletName(contents);
				}else if(tag.equals("url-pattern")) {
					servletMapping.addUrlPattern(contents);
				}
			}else {
				if(tag.equals("servlet-name")) {
					servlet.setServletName(contents);
				}else if(tag.equals("servlet-class")){
					servlet.setServletClass(contents);
				}
			}
		}
	}
	
	public List<Servlet> getServlets(){
		return servlets;
	}
	
	public List<ServletMapping> getServletMappings(){
		return servletMappings;
	}
}
