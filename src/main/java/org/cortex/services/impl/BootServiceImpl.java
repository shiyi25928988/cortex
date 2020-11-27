package org.cortex.services.impl;

import org.cortex.services.BootService;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class BootServiceImpl implements BootService{
	
	@Inject
	@Named("application.port")
	private String port;
	
	@Override
	public void startOn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startOn(Class<?> mainClass) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startOn(Class<?> mainClass, Module... modules) {
		// TODO Auto-generated method stub
		
	}


}
