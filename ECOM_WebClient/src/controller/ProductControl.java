package controller;

import java.util.List;

import session.*;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import entities.Product;

@Path("/product")
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
     * Retrieves representation of an instance of ProductControl
     * @return an instance of String
     */
    @GET
    @Path("/getjson")
    public String getJSON() {
        return "{msg: \"Hello World !\"}";
    }

    @GET
    @Produces("application/json")
    public List<Product> getproduct() {
        // TODO return proper representation object
        // throw new UnsupportedOperationException();
    	return this.productfacade.findAll();
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