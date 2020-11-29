package org.cortex.services;

import org.cortex.services.impl.BootServiceImpl;

import com.google.inject.ImplementedBy;
import com.google.inject.Module;

/**
 * @author 86135
 *
 */
@ImplementedBy(BootServiceImpl.class)
public interface BootService {

	public void startOn(Class<?> mainClass, Module...modules);
	
}
