package org.cortex.services;

import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;

public class HttpRequestDispatcherServiceImpl implements HttpRequestDispatcherService{

	@Override
	public void dispatch(RoutingContext context) {
		dispatch(context.request());
	}

	@Override
	public void dispatch(HttpServerRequest request) {
		
		
	}

}
