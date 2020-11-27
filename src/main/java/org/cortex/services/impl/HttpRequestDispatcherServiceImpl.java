package org.cortex.services.impl;

import org.cortex.annotation.HttpMethod;
import org.cortex.services.HttpRequestDispatcherService;
import org.cortex.services.rest.*;

import com.google.inject.Inject;

import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;

public class HttpRequestDispatcherServiceImpl implements HttpRequestDispatcherService {

	@Inject
	private HttpMethodService HttpMethodService;

	@Override
	public void dispatch(RoutingContext context) {
		RestHelper.putContext(context);
		dispatch(context.request());
	}

	@Override
	public void dispatch(HttpServerRequest request) {

		HttpMethod httpMethod = HttpMethod.getHttpMethod(request.method().name());

		switch (httpMethod) {
		case GET:
			HttpMethodService.doGet();
			break;
		case DELETE:
			HttpMethodService.doDelete();
			break;
		case HEAD:
			HttpMethodService.doHead();
			break;
		case OPTIONS:
			HttpMethodService.doOptions();
			break;
		case PATCH:
			HttpMethodService.doPatch();
			break;
		case POST:
			HttpMethodService.doPost();
			break;
		case PUT:
			HttpMethodService.doPut();
			break;
		default:
			break;
		}
	}

}
