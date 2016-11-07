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

import session.AdminFacade;
import entities.Admin;

@Path("/admin")
@Api(value="/admin", description = "Toutes les requêtes concernant utilisateurs administrateurs")
public class AdminControl {

    @Context
    private UriInfo context;
    
	@EJB	
	private AdminFacade adminFacade =new AdminFacade();
	
	public AdminControl(){
		
	}
	
    @GET
    @ApiOperation(
    value = "Liste de toutes les images des annonces", 
    notes = "Liste de toutes les images des annonces"
    )
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/getAllAdmin")
    public List<Admin> getAllAdmin() {
        // TODO return proper representation object
        // throw new UnsupportedOperationException();
    	List<Admin> admin = this.adminFacade.findAll();
    	return admin;
    }
}
