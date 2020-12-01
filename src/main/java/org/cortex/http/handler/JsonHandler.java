package org.cortex.http.handler;

import java.lang.reflect.Method;

import org.cortex.annotation.ResponseType;
import org.cortex.services.rest.RestHelper;

import io.vertx.core.json.JsonObject;

public class JsonHandler extends ResponseHandler{

	@Override
	protected boolean handle(Method method, Object methodInvokeResult) {
		// TODO Auto-generated method stub
		
		RestHelper.getResponse().putHeader("Content-Type", org.cortex.http.MimeType.APPLICATION_JSON.getType());
		RestHelper.getResponse().send(JsonObject.mapFrom(methodInvokeResult).encode());
		
		return true;
	}

}
