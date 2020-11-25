package org.cortex.services.rest;

import io.vertx.ext.web.RoutingContext;

public class RestHelper {

	private static ThreadLocal<RoutingContext> local = new ThreadLocal<>();
	
	public static void putContext(RoutingContext context) {
		local.set(context);
	}
	
	public static RoutingContext getContext() {
		return local.get();
	}
}
