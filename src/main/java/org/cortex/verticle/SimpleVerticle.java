package org.cortex.verticle;

import org.cortex.services.HttpRequestDispatcherService;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

public class SimpleVerticle extends AbstractVerticle {
	
	@Inject
	@Named("application.port")
	private int port;
	
	@Inject
	private HttpRequestDispatcherService httpRequestDispatcherService;
	
	@Override
	public void start() throws Exception {
		// Create a Router

		Vertx vertx = Vertx.vertx(new VertxOptions().setWorkerPoolSize(40));

		HttpServer httpServer = vertx.createHttpServer();
		
		Router router = Router.router(vertx);
		
		Route route_a = router.route();
		
		route_a.handler(requestHandler ->{
			
			requestHandler.request().body().onComplete(handler->{
				System.out.println(handler.result());
			});
			httpRequestDispatcherService.dispatch(requestHandler);
		});

		httpServer.requestHandler(router).listen(port);

	}
}
