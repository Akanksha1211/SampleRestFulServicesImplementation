package org.akanksha.rest.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.akanksha.rest.messenger.model.ErrorMessage;

public class GenericException implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable ex) {
		ErrorMessage error=new ErrorMessage(ex.getMessage(),500,"Httpsdgdg");
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
	}
}
