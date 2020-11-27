package org.cortex.demo.api;

import org.cortex.annotation.Controller;
import org.cortex.annotation.Path;
import org.cortex.annotation.method.GET;

@Controller
public class DemoApi {
	
	@GET
	@Path("/demo")
	public String test() {
		return "SDSD";
	}
}
