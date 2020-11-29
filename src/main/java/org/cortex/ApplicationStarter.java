package org.cortex;

import org.cortex.services.BootService;
import org.cortex.services.impl.BootServiceImpl;

import com.google.inject.Module;

public class ApplicationStarter {

	public static void on(Class<?> mainClass, Module...modules) {
		BootService bootService = new BootServiceImpl();
		bootService.startOn(mainClass, modules);
	}
}
