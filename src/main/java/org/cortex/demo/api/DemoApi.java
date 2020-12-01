package org.cortex.demo.api;

import org.cortex.annotation.Controller;
import org.cortex.annotation.Path;
import org.cortex.annotation.PathParam;
import org.cortex.annotation.ResponseType;
import org.cortex.annotation.method.GET;
import org.cortex.http.ResponseResult;

@Controller
public class DemoApi {
	
	@GET
	@Path("/demo")
	@ResponseType(mimeType = org.cortex.http.MimeType.APPLICATION_JSON)
	public ResponseResult<String> test(@PathParam("demo") String str) {
		return new ResponseResult<String>().setData(str);
	}
	
	@GET
	@Path("/vedio")
	@ResponseType(mimeType = org.cortex.http.MimeType.VIDEO_MP4, isFile = true)
	public String test() {
		return "D:\\星蝶公主\\星蝶公主.Star.vs.The.Forces.of.Evil.S02E01.WEB-HR.Chs.Eng-Deefun迪幻字幕组.mp4";
	}
}
