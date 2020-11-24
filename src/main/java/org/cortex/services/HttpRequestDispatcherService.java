package org.cortex.services;

import io.vertx.ext.web.RoutingContext;

public interface HttpRequestDispatcherService {

	void dispatch(RoutingContext context);
}
