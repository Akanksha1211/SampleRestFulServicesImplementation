package org.akanksha.rest.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.akanksha.rest.messenger.model.ErrorMessage;

@Provider
public class DataNotFoundMapper implements ExceptionMapper<DataNotFoundException>{

	@Override
	public Response toResponse(DataNotFoundException ex) {
		ErrorMessage error=new ErrorMessage(ex.getMessage(),404,"Httpsdgdg");
		return Response.status(Status.NOT_FOUND).entity(error).build();
	}

}
