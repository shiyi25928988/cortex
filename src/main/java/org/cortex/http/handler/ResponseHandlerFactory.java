package org.cortex.http.handler;

public class ResponseHandlerFactory {

	public static ResponseHandler buildResponseHandler() {
		ResponseHandler handler = new EmptyHandler();
		handler.addNextHandler(new FileHandler());
		handler.addNextHandler(new JsonHandler());
		return handler;
	}
}
