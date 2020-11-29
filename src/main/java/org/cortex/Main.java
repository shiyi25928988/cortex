package org.cortex;

import org.cortex.annotation.PropertiesFile;
import org.cortex.config.CoreProperties;
import org.cortex.module.CommonModule;
import org.cortex.services.BootService;
import org.cortex.verticle.SimpleVerticle;

import com.google.inject.Guice;
import com.google.inject.Injector;

import lombok.extern.slf4j.Slf4j;

@PropertiesFile(files = { "application.properties" })
public class Main {

	public static void main(String...strings) throws Exception {
		ApplicationStarter.on(Main.class);
	}
}
