package org.cortex.http.handler;

import java.lang.reflect.Method;
import java.util.Objects;

import org.cortex.annotation.ResponseType;
import org.cortex.services.rest.RestHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author shiyi
 *
 */
@Slf4j
public class FileHandler extends ResponseHandler{

	@Override
	protected boolean handle(Method method, Object methodInvokeResult) {
		
		ResponseType responseType = method.getAnnotation(ResponseType.class);
		if(!Objects.isNull(responseType)) {
			RestHelper.getResponse().putHeader("Content-Type", responseType.mimeType().getType());
			
			if(responseType.isFile()) {
				
				
				RestHelper.getResponse().sendFile((String)methodInvokeResult, handler -> {
					if(handler.succeeded()) {
						
					}
					if(handler.failed()) {
						log.error(handler.cause().getMessage());
					}
				});
				return true;
			}
		}
		
		return false;
	}

}
