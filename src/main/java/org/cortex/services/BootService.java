package org.cortex.services;

/**
 * @author 86135
 *
 */
public interface BootService {

	/**
	 * 
	 */
	public void startOn();
	
	public void startOn(Class<?> mainClass);
	
	public void startOn(Class<?> mainClass, Module...modules);
	
}
