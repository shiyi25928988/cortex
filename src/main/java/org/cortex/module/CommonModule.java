package org.cortex.module;

import java.util.Objects;
import java.util.stream.Stream;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;

public class CommonModule extends AbstractModule {
	
	public static Injector injector;

	@Override
	protected void configure() {
		Names.bindProperties(binder(), System.getProperties());
		install(new VertxModule());
		install(new IocModule());
	}
	
	public static Injector getInjector() {
		if(Objects.isNull(injector)) {
			injector = Guice.createInjector(new CommonModule());
		}
		return injector;
	}
}
