package org.cortex.module;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.servicediscovery.ServiceDiscovery;
import io.vertx.servicediscovery.ServiceDiscoveryOptions;

/**
 * @author shiyi
 *
 */
public class VertxModule extends AbstractModule {

	public VertxModule() {
	}

	@Override
	protected void configure() {
		bind(Vertx.class).toProvider(VertxProvider.class);
		bind(HttpServer.class).toProvider(HttpServerProvider.class);
		bind(Router.class).toProvider(RouterProvider.class);
		bind(ServiceDiscoveryOptions.class).toProvider(ServiceDiscoveryOptionsProvider.class);
		bind(ServiceDiscovery.class).toProvider(ServiceDiscoveryProvider.class);
	}

	public static class VertxProvider implements Provider<Vertx> {

		@Override
		public Vertx get() {
			return Vertx.vertx(new VertxOptions().setWorkerPoolSize(40));
		}

	}

	public static class HttpServerProvider implements Provider<HttpServer> {
		
		@Inject
		Vertx vertx;
		
		@Override
		public HttpServer get() {
			return vertx.createHttpServer();
		}
	}
	
	public static class RouterProvider implements Provider<Router> {
		
		@Inject
		Vertx vertx;
		
		@Override
		public Router get() {
			return Router.router(vertx);
		}
	}
	
	public static class ServiceDiscoveryOptionsProvider implements Provider<ServiceDiscoveryOptions> {
		
		@Named("eventbus.address")
		String eventbusAddr;
		
		@Named("service.name")
		String serviceName;
		
		@Override
		public ServiceDiscoveryOptions get() {
			return new ServiceDiscoveryOptions()
                    .setAnnounceAddress(eventbusAddr)
                    .setName("serviceName");
		}
		
	}
	
	public static class ServiceDiscoveryProvider implements Provider<ServiceDiscovery> {

		@Inject
		Vertx vertx;
		
		@Inject
		ServiceDiscoveryOptions serviceDiscoveryOptions;
		
		@Override
		public ServiceDiscovery get() {
		    return ServiceDiscovery.create(vertx,serviceDiscoveryOptions);
		}
		
	}

}
