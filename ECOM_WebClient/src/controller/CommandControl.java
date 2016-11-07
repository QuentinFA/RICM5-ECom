package controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import session.CategoryFacade;
import session.CommandFacade;
import entities.Category;
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
}
