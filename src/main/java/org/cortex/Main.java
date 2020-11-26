package org.cortex;

import org.cortex.config.CoreProperties;
import org.cortex.module.CommonModule;
import org.cortex.verticle.SimpleVerticle;

import com.google.inject.Guice;
import com.google.inject.Injector;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

	public static void main(String...strings) throws Exception {
		
		
		CoreProperties.setProperties("application.properties");
		
		System.out.println(System.getProperty("application.port"));
		
		Injector injector = Guice.createInjector(new CommonModule());
		
		SimpleVerticle simpleVerticle = injector.getInstance(SimpleVerticle.class);

		
		
		simpleVerticle.start();
		
		
	}
}
