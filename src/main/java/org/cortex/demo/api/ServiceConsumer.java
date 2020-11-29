package org.cortex.demo.api;

import org.cortex.annotation.Controller;
import org.cortex.annotation.Path;
import org.cortex.annotation.RequestBody;
import org.cortex.annotation.ResponseType;
import org.cortex.annotation.ServiceExpose;
import org.cortex.annotation.method.GET;
import org.cortex.annotation.method.POST;
import org.cortex.http.ResponseResult;

import com.google.inject.Inject;

@Controller
public class ServiceConsumer {

	@Inject
	ServiceProducer serviceProducer;
	
	@POST
	@Path("/api")
	@ResponseType(type = org.cortex.http.MimeType.APPLICATION_JSON)
	public ResponseResult<String> api(@RequestBody User user) {
		return serviceProducer.api(user);
	}
}
