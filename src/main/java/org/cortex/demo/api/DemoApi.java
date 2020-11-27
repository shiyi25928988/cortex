package org.cortex.demo.api;

import org.cortex.annotation.Controller;
import org.cortex.annotation.Path;
import org.cortex.annotation.ResponseType;
import org.cortex.annotation.method.GET;
import org.cortex.http.ResponseResult;

@Controller
public class DemoApi {
	
	@GET
	@Path("/demo")
	@ResponseType(type = org.cortex.http.MimeType.APPLICATION_JSON)
	public ResponseResult<String> test() {
		return new ResponseResult<String>().setData("DEMO");
	}
}
