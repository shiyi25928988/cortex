package org.cortex.services;

import com.google.inject.name.Named;

public class BootServiceImpl implements BootService{
	
	@Named("application.port")
	private String port;
	
	@Named("application.context")
	private String context;

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
