package org.cortex.services.impl;

import org.cortex.config.CoreProperties;
import org.cortex.module.CommonModule;
import org.cortex.services.BootService;
import org.cortex.verticle.SimpleVerticle;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.google.inject.Module;

public class BootServiceImpl implements BootService{
	
	@Override
	public void startOn(Class<?> mainClass, Module... modules) {
		CoreProperties.setProperties(mainClass);
		
		Injector injector = Guice.createInjector(Stage.DEVELOPMENT, new CommonModule());
		SimpleVerticle simpleVerticle = injector.getInstance(SimpleVerticle.class);
		try {
			simpleVerticle.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
