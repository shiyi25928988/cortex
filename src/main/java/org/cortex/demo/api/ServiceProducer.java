package org.cortex.demo.api;

import org.cortex.annotation.Controller;
import org.cortex.annotation.Path;
import org.cortex.annotation.RequestBody;
import org.cortex.annotation.ResponseType;
import org.cortex.annotation.ServiceExpose;
import org.cortex.annotation.method.POST;
import org.cortex.http.ResponseResult;

@Controller
public class ServiceProducer {

	@POST
	@Path("/api2")
	@ResponseType(mimeType = org.cortex.http.MimeType.APPLICATION_JSON)
	@ServiceExpose
	public ResponseResult<String> api(@RequestBody User user) {
		return new ResponseResult<String>().setData(user.getName());
	}
}
