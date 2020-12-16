package cn.nroma;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.nroma.model.Servlet;
import cn.nroma.model.ServletMapping;

public class WebContext {
	private List<Servlet> servlets=null;
	private List<ServletMapping>servletMappings=null;
	
	private Map<String, String> servletMap=new HashMap<String, String>();
	private Map<String, String> servletMappingMap=new HashMap<String, String>();
	
	public WebContext(List<Servlet> servlets, List<ServletMapping> servletMappings) {
		this.servlets=servlets;
		this.servletMappings=servletMappings;
		
		for(Servlet servlet : servlets) {
			servletMap.put(servlet.getServletName(), servlet.getServletClass());
		}
		for(ServletMapping servletMapping : servletMappings) {
			for(String urlPattern : servletMapping.getUrlPatterns()) {
				servletMappingMap.put(urlPattern, servletMapping.getServletName());
			}
		}	
	}
	
	public String getClass(String urlPattern) {
		return servletMap.get(servletMappingMap.get(urlPattern));
	}
	
}
