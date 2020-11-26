package org.cortex.services.rest;


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
	
	/**
	 * HTTP TRACE
	 */
	void doPatch();
}
