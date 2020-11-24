package org.cortex.module;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provider;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.servicediscovery.ServiceDiscovery;
import io.vertx.servicediscovery.ServiceDiscoveryOptions;

public class VertxModule extends AbstractModule {

	public VertxModule() {
	}

	@Override
	protected void configure() {
		bind(Vertx.class).toProvider(VertxProvider.class);
		bind(HttpServer.class).toProvider(HttpServerProvider.class);
		bind(Router.class).toProvider(RouterProvider.class);
		bind(ServiceDiscovery.class).toProvider(ServiceDiscoveryProvider.class);
	}

	private static class VertxProvider implements Provider<Vertx> {

		@Override
		public Vertx get() {
			return Vertx.vertx(new VertxOptions().setWorkerPoolSize(40));
		}

	}

	private static class HttpServerProvider implements Provider<HttpServer> {
		
		@Inject
		Vertx vertx;
		
		@Override
		public HttpServer get() {
			return vertx.createHttpServer();
		}
	}
	
	private static class RouterProvider implements Provider<Router> {
		
		@Inject
		Vertx vertx;
		
		@Override
		public Router get() {
			return Router.router(vertx);
		}
	}
	
	private static class ServiceDiscoveryProvider implements Provider<ServiceDiscovery> {

		@Inject
		Vertx vertx;
		
		@Override
		public ServiceDiscovery get() {
		    return ServiceDiscovery.create(vertx,
                    new ServiceDiscoveryOptions()
                      .setAnnounceAddress("service-announce")
                      .setName("my-name"));
		}
		
	}

}
