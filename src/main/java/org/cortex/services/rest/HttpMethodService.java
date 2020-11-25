package org.cortex.services.rest;

import com.google.inject.ImplementedBy;

@ImplementedBy(HttpMethodServiceImpl.class)
public interface HttpMethodService {
	/**
	 * HTTP GET
	 */
	void doGet();
	
	/**
	 * HTTP PUT
	 */
	void doPut();
	
	/**
	 * HTTP POST
	 */
	void doPost();
	
	/**
	 * HTTP DELETE
	 */
	void doDelete();
	
	/**
	 * HTTP HEAD
	 */
	void doHead();
	
	/**
	 * HTTP OPTIONS
	 */
	void doOptions();
	
	/**
	 * HTTP TRACE
	 */
	void doTrace();
}
