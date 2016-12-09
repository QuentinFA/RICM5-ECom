package controller;

import java.util.List; 

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import session.CategoryFacade;
import entities.Category;
import entities.Image;

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
    
	@POST 
	@ApiOperation(
			value = "Ajouter un nouvel Category",
			notes = "Ajouter un nouvel Category"
			)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_FORM_URLENCODED})
	@Path("/createCategory")
	public Response createCategory(Category nouveau) {


		this.categoryFacade.create(nouveau); 
		return Response.status(Response.Status.CREATED).build();
	}
}
