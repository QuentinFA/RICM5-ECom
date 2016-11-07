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

import entities.Image;
import entities.Product;
import session.ImageFacade;

@Path("/image")
@Api(value="/image", description = "Toutes les requêtes concernant les images")

public class ImageControl {

    @Context
    private UriInfo context;
    
	@EJB	
	private ImageFacade imageFacade =new ImageFacade();
	
	public ImageControl(){
		
	}
	
    @GET
    @ApiOperation(
    value = "Liste de toutes les images des annonces", 
    notes = "Liste de toutes les images des annonces"
    )
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/getAllimages")
    public List<Image> getAllimages() {
        // TODO return proper representation object
        // throw new UnsupportedOperationException();
    	List<Image> image = this.imageFacade.findAll();
    	return image;
    }
}
