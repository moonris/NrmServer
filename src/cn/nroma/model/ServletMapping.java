package cn.nroma.model;

import java.util.HashSet;
import java.util.Set;

public class ServletMapping {
	private String servletName;
	private Set<String> urlPatterns;
	
	public ServletMapping() {
		urlPatterns=new HashSet<String>();
	}

	public String getServletName() {
		return servletName;
	}

	public void setServletName(String servletName) {
		this.servletName = servletName;
	}

	public Set<String> getUrlPatterns() {
		return urlPatterns;
	}

	public void setUrlPatterns(Set<String> urlPatterns) {
		this.urlPatterns = urlPatterns;
	}
	
	public void addUrlPattern(String urlPattern) {
		this.urlPatterns.add(urlPattern);
	}

}
