package org.akanksha.rest.messenger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.akanksha.rest.messenger.service.MessageService;
import org.akanksha.rest.messenger.model.Message;
import org.akanksha.rest.messenger.resources.beans.BeanFilter;

@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {
	
	MessageService messageService=new MessageService();
	
    @GET
    public List<Message> getMessages(@BeanParam BeanFilter beanFilter) {
    	if(beanFilter.getYear()>0){
    		return messageService.getAllMessagesForYear(beanFilter.getYear());
    	}
    	if(beanFilter.getStart()>=0 && beanFilter.getSize()>=0){
    		return messageService.getAllMessagesPaginated(beanFilter.getStart(), beanFilter.getSize());
    	}
    
    		return messageService.getAllMessages();
    	
        
    }
	
    @POST
    public Response addMessage(Message message, @Context UriInfo uriInfo){
    	Message newMessage= messageService.addMessage(message);
    	
    	String newId=String.valueOf(newMessage.getId());
    	URI uri=uriInfo.getAbsolutePathBuilder().path(newId).build();
    	return Response.created(uri)
    			.entity(newMessage)
    			.build();
    
    }
    
    @PUT
    @Path("/{messageId}")
    public Message updateMessage(@PathParam("messageId")long messageId, Message message){
    	message.setId(messageId);
    	return messageService.updateMessage(message);
    }
    
    @DELETE
    public void deleteMessages(@PathParam("messageId")long messageId){
    	 messageService.removeMessage(messageId);
    }
    
    @GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long id, @Context UriInfo uriInfo) {
		Message message = messageService.getMessage(id);
		message.addLink(getUriForSelf(uriInfo, message), "self");
		message.addLink(getUriForProfile(uriInfo, message), "profile");
		message.addLink(getUriForComments(uriInfo, message), "comments");
		
		return message;
		
	}
    
	private String getUriForComments(UriInfo uriInfo, Message message) {
		URI uri = uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
	       		.path(MessageResource.class, "getCommentResource")
	       		.path(CommentResource.class)
	       		.resolveTemplate("messageId", message.getId())
	            .build();
	    return uri.toString();
	}

	private String getUriForProfile(UriInfo uriInfo, Message message) {
		URI uri = uriInfo.getBaseUriBuilder()
       		 .path(ProfileResource.class)
       		 .path(message.getAuthor())
             .build();
        return uri.toString();
	}

	private String getUriForSelf(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
		 .path(MessageResource.class)
		 .path(Long.toString(message.getId()))
		 .build()
		 .toString();
		return uri;
	}
    
    @Path("/{messageId}/comments")
    public CommentResource getCommentResource(){
    	return new CommentResource();
    }
 
}
