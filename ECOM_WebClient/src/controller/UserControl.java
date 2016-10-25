package controller;

import java.net.URISyntaxException; 

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import entities.User;
import session.ProductFacade;
import session.UserFacade;

@Path("/user")
public class UserControl {
    @SuppressWarnings("unused")
    @Context
    private UriInfo context;
    
    @EJB
	private UserFacade userfacade = new UserFacade();


    /**
     * Default constructor. 
     */
    public UserControl() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Retrieves representation of an instance of Login
     * @return an instance of String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        // TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of Login
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
    
    @POST
	@Path("/check")
	public Response check(
			@FormParam("name") String name,
			@FormParam("password") String password) throws URISyntaxException {
    	
    	
//    	System.out.println(name);

    	User user = userfacade.find(name);
    	String pass = user.getPassword();
    	
    	if(pass.equals(password)){
    		return Response.status(200)
    				.entity("Welcome "+name)
    				.build();
    	}
    	
    	else
		return Response.status(200)
			.entity("Name or password incorrect, please try again! ")
			.build();

	}

}