package controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import session.*;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entities.Product;

@Path("/product")
@Api(value="/product", description = "Toutes les requêtes concernant les produits")
public class ProductControl {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductControl.class.getName());

//    @SuppressWarnings("unused")
    @Context
    private UriInfo context;
    
    @EJB
	private ProductFacade productfacade = new ProductFacade();

    /**
     * Default constructor. 
     */
    public ProductControl() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Retrieves representation of an instance of ProductControl
     * @return an instance of String
     */
    @GET
    @Path("/getjson")
    public String getJSON() {
        return "{msg: \"Hello World !\"}";
    }

    /**
     * Cette fonction renvoie toutes les annonces de la base de donnée.
     * @return an instance of String
     */
    @GET
    @ApiOperation(
    value = "Liste de toutes les annonces", 
    notes = "Liste de toutes les annonces"
    )
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/getAllproducts")
    public List<Product> getAllproducts() {
        // TODO return proper representation object
        // throw new UnsupportedOperationException();
    	List<Product> produits = this.productfacade.findAll();
    	return produits;
    }

    /**
     * POST for posting request
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    
    @POST
    @ApiOperation(
            value = "Ajouter un nouveau produit",
            notes = "Ajouter un nouveau produit"
    )
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_FORM_URLENCODED})
    @Path("/createProduct")
    public Response createProduct(@FormParam("description") String description, 
    							  @FormParam("idUser") String idUser,
    							  @FormParam("price") int price,
    							  @FormParam("title") String title,
    							  @FormParam("type") String type) {
    	
    	LOGGER.info("Annonce créé : derscription: "+description +"\n idUser :"+idUser +"\n price: "+price+" \n title: "+title+" \n type:"+type+" \n"); 
    	System.out.println("Annonce créé : derscription: "+description +"\n idUser :"+idUser +"\n price: "+price+" \n title: "+title+" \n type:"+type+" \n");
    	return Response.status(Response.Status.CREATED).build();
    }
    
    /**
     * PUT method for updating or creating an instance of ProductControl
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
    
    

}