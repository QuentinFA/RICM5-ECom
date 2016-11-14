package controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import session.CommandFacade;
import entities.Command;

@Path("/commande")
@Api(value="/commande", description = "Toutes les requêtes concernant les commandes")
public class CommandControl {
	
    @Context
    private UriInfo context;
    
	@EJB	
	private CommandFacade commandFacade =new CommandFacade();
	
	public CommandControl(){
		
	}
	
    @GET
    @ApiOperation(
    value = "Liste de toutes les images des annonces", 
    notes = "Liste de toutes les images des annonces"
    )
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/getAllCommand")
    public List<Command> getAllCommand() {
        // TODO return proper representation object
        // throw new UnsupportedOperationException();
    	List<Command> command = this.commandFacade.findAll();
    	return command;
    }
    
	@POST
	@ApiOperation(
			value = "Rechercher les commandes par idUser et idProduct", 
			notes = "Rechercher les commandes par idUser et idProduct"
			)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_FORM_URLENCODED})
	@Path("/findCommandUrlByUserAndProduct")
	public List<Command> findCommandUrlByUserAndProduct(@FormParam("idProduct") String idProduct, @FormParam("idUser") String idUser) {
		// throw new UnsupportedOperationException();
		List<Command> image = this.commandFacade.findCommandUrlByUserAndProduct(idProduct, idUser);
		return image;
	}
	
	@POST 
	@ApiOperation(
			value = "Ajouter une nouvelle Command",
			notes = "Ajouter une nouvelle Command"
			)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_FORM_URLENCODED})
	@Path("/createCommand")
	public Response createCommand(@FormParam("idProduct") String idProduct, 
			@FormParam("idUser") String idUser, @FormParam("state") String state ) {

		Command nouveau = new Command();
		nouveau.setIdProduct(Integer.valueOf(idProduct));
		nouveau.setIdUser(idUser);
		nouveau.setState(state); 
		this.commandFacade.create(nouveau); 
		return Response.status(Response.Status.CREATED).build();
	}
	
	@POST 
	@ApiOperation(
			value = "modifier commande",
			notes = "modifier commande"
			)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_FORM_URLENCODED})
	@Path("/modifyCommand")
	public Response modifyCommand(@FormParam("idProduct") String idProduct, 
			@FormParam("idUser") String idUser, @FormParam("state") String state ) {

		Command nouveau = (Command) this.commandFacade.findCommandUrlByUserAndProduct(idProduct, idUser);
		nouveau.setIdProduct(Integer.valueOf(idProduct));
		nouveau.setIdUser(idUser);
		nouveau.setState(state);
		this.commandFacade.edit(nouveau); 
		return Response.status(Response.Status.CREATED).build();
	}
	
	@POST 
	@ApiOperation(
			value = "supprimer une commande",
			notes = "supprimer une commande"
			)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_FORM_URLENCODED})
	@Path("/deleteCommand")
	public Response deleteCommand(@FormParam("idProduct") String idProduct, 
			@FormParam("idUser") String idUser) {

		Command nouveau = (Command) this.commandFacade.findCommandUrlByUserAndProduct(idProduct, idUser);
		this.commandFacade.remove(nouveau); 
		return Response.status(Response.Status.CREATED).build();
	}
}
