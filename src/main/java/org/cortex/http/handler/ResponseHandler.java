package org.cortex.http.handler;

import java.lang.reflect.Method;
import java.util.Objects;

import org.cortex.exception.NoSpecificResponseHandlerFoundException;

public abstract class ResponseHandler {
	
	private ResponseHandler nextHandler;
	
	protected boolean hasNext() {
		if(Objects.isNull(nextHandler)) {
			return false;
		} else {
			return true;
		}
	}
	
	protected ResponseHandler getNext() {
		return nextHandler;
	}
	
	protected void addNextHandler(ResponseHandler nextHandler) {
		if(hasNext()) {
			this.nextHandler.addNextHandler(nextHandler);
		}else {
			this.nextHandler = nextHandler;
		}
	}
	
	public void submit(Method method, Object methodInvokeResult) throws NoSpecificResponseHandlerFoundException {
		
		if(getNext().handle(method, methodInvokeResult)) {
			return;
		}else {
			if(getNext().hasNext()) {
				getNext().getNext().handle(method, methodInvokeResult);
			} else {
				throw new NoSpecificResponseHandlerFoundException();
			}
		}
		

	}
	
	protected abstract boolean handle(Method method, Object methodInvokeResult);
}
