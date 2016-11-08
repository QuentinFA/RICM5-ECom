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


import entities.Product;

@Path("/product")
@Api(value="/product", description = "Toutes les requêtes concernant les produits")
public class ProductControl {
   
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

    	
    @POST
    @ApiOperation(
    value = "Rechercher les annonces par titre", 
    notes = "Rechercher les annonces par titre"
    )
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_FORM_URLENCODED})
    @Path("/getProductByTitle")
    public List<Product> getProductByTitle(@FormParam("title") String title) {
        // TODO return proper representation object
        // throw new UnsupportedOperationException();
    	List<Product> produits = this.productfacade.findProductByTitle(title);
    	return produits;
    }
    
    @POST
    @ApiOperation(
    value = "Rechercher les annonces par Type", 
    notes = "Rechercher les annonces par Type"
    )
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_FORM_URLENCODED})
    @Path("/getProductByType")
    public List<Product> getProductByType(@FormParam("type") String type) {
        // TODO return proper representation object
        // throw new UnsupportedOperationException();
    	List<Product> produits = this.productfacade.findProductByType(type);
    	return produits;
    }
    
    @POST
    @ApiOperation(
    value = "Rechercher les annonces qui sont dans un range de prix", 
    notes = "Rechercher les annonces qui sont dans un range de prix"
    )
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_FORM_URLENCODED})
    @Path("/getProductByPriceRange")
    public List<Product> getProductByPriceRange(@FormParam("startPrice") int startPrice,@FormParam("endPrice") int endPrice) {
        // TODO return proper representation object
        // throw new UnsupportedOperationException();
    	List<Product> produits = this.productfacade.findProductPriceRange(startPrice, endPrice);
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
    	
    	Product nouveau = new Product();
		    	nouveau.setDescription(description);
		    	nouveau.setIdUser(idUser);
		    	nouveau.setPrice(price);
		    	nouveau.setTitle(title);
		    	nouveau.setType(type);
    	this.productfacade.create(nouveau); 
    	System.out.println("Annonce créé : derscription: "+description +"\n idUser :"+idUser +"\n price: "+price+" \n title: "+title+" \n type:"+type+" \n");
    	return Response.status(Response.Status.CREATED).build();
    }

}