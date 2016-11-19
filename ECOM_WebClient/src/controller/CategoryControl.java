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
import entities.Category;

@Path("/category")
@Api(value="/category", description = "Toutes les requêtes concernant les categories")

public class CategoryControl {
	
    @Context
    private UriInfo context;
    
	@EJB	
	private CategoryFacade categoryFacade =new CategoryFacade();
	
	public CategoryControl(){
		
	}
	
    @GET
    @ApiOperation(
    value = "Liste de toutes les images des annonces", 
    notes = "Liste de toutes les images des annonces"
    )
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/getAllCategory")
    public List<Category> getAllCategory() {
        // TODO return proper representation object
        // throw new UnsupportedOperationException();
    	List<Category> category = this.categoryFacade.findAll();
    	return category;
    }
}
