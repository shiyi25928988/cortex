package org.cortex.services;

import com.google.inject.ImplementedBy;

import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;

@ImplementedBy(HttpRequestDispatcherServiceImpl.class)
public interface HttpRequestDispatcherService {

	void dispatch(RoutingContext context);
	
	void dispatch(HttpServerRequest request);
}
