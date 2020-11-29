package org.cortex.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * @author shiyi
 *
 */
@Slf4j
public class ReduplicativeMathodPathException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ReduplicativeMathodPathException() {
		super();
	}

	public ReduplicativeMathodPathException(String message) {
		super(message);
//		log.error(message);
//		for(StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
//			System.out.println(stackTraceElement.toString());
//		}
//		
//		System.exit(1);
	}
}
