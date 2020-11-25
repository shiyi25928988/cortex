package org.cortex.verticle;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.Cookie;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

public class SimpleVerticle extends AbstractVerticle {
	
	@Inject
	@Named("application.port")
	public int port;
	
	@Override
	public void start() throws Exception {
		// Create a Router

		Vertx vertx = Vertx.vertx(new VertxOptions().setWorkerPoolSize(40));

		HttpServer httpServer = vertx.createHttpServer();
		
		Router router = Router.router(vertx);
		
		Route route_a = router.route();
		
		route_a.handler(requestHandler ->{
			System.out.println(requestHandler.getClass().getCanonicalName());
			
			System.out.println(requestHandler.normalizedPath());
			
			System.out.println(requestHandler.request().method().name());
			System.out.println(requestHandler.request().getParam("name"));
			
			requestHandler.end("a");
		});

		System.out.println(port);
		httpServer.requestHandler(router).listen(port);

//		// Mount the handler for all incoming requests at every path and HTTP method
//		router.route().handler(requestHandler ->{
//			//requestHandler.attachment("D:\\Downloads\\fangke.pdf");
//			//requestHandler.acceptableLanguages()
//			
//			requestHandler.addCookie(Cookie.cookie("test", "test"));
//			requestHandler.json("aaa");
//		}).method(HttpMethod.GET);
		
		

		// Create the HTTP server
//		vertx.createHttpServer()
//				// Handle every request using the router
//				.requestHandler(router)
//				// Start listening
//				.listen(8888)
//				// Print the port
//				.onSuccess(server -> System.out.println("HTTP server started on port " + server.actualPort()));
	}
}
