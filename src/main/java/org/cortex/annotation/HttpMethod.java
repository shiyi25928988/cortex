package org.cortex.annotation;

import com.google.common.base.Strings;

public enum HttpMethod {
	GET,
	POST,
	PUT,
	DELETE,
	PATCH,
	HEAD,
	OPTIONS;
	
	public static HttpMethod getHttpMethod(String method) {
		if(Strings.isNullOrEmpty(method)) {
			return GET;
		}
		String var = method.trim();
		if(var.equalsIgnoreCase("GET")) return GET;
		if(var.equalsIgnoreCase("POST")) return POST;
		if(var.equalsIgnoreCase("PUT")) return PUT;
		if(var.equalsIgnoreCase("DELETE")) return DELETE;
		if(var.equalsIgnoreCase("PATCH")) return PATCH;
		if(var.equalsIgnoreCase("HEAD")) return HEAD;
		if(var.equalsIgnoreCase("OPTIONS")) return OPTIONS;
		
		return GET;
	}
}
