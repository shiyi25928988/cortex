package org.cortex.services.rest;

import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;

public class RestHelper {

	private static ThreadLocal<RoutingContext> local = new ThreadLocal<>();
	
	public static void putContext(RoutingContext context) {
		local.set(context);
	}
	
	public static RoutingContext getContext() {
		return local.get();
	}
	
	public static HttpServerRequest getRequest() {
		return local.get().request();
	}
	
	public static HttpServerResponse getResponse() {
		return local.get().request().response();
	}
}
