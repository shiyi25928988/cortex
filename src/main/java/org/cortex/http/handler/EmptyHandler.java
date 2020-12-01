package org.cortex.http.handler;

import java.lang.reflect.Method;

public class EmptyHandler extends ResponseHandler{

	@Override
	protected boolean handle(Method method, Object methodInvokeResult) {
		// TODO Auto-generated method stub
		return false;
	}

}
