package org.akanksha.rest.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class InjectedDemoResource {

	
	@GET
	@Path("annotations")
	public String getParamUsingAnno(@MatrixParam("param") String param, 
									@HeaderParam("headerparam") String headerparam, 
									@CookieParam("cookie") String cookie){
		return "Text " + param + "Header "+ headerparam +"Cookie " +cookie;
		
	}
	
	@GET
	@Path("context")
	public String getParamUsingContext(@Context UriInfo uriinfo, HttpHeaders httpHeader){
		String path=uriinfo.getAbsolutePath().toString();
		//String header=httpHeader.getCookies().toString();
		return "URIPAth " +path +" Header " ;
	}
}
