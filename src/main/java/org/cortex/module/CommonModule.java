package org.cortex.module;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class CommonModule extends AbstractModule {

	@Override
	protected void configure() {
		Names.bindProperties(binder(), System.getProperties());
		install(new VertxModule());
	}
}
