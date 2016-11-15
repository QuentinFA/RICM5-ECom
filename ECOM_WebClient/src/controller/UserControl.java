package controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.net.URISyntaxException;
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

import session.UserFacade;
import entities.User;

@Path("/user")
@Api(value="/user", description = "Toutes les requêtes concernant les Utilisateurs")
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
    @ApiOperation(
    value = "Liste de toutes les utilisateurs du site", 
    notes = "Liste de toutes les utilisateurs du site"
    )
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/getAllUsers")
    public List<User> getAllUsers() {
        // TODO return proper representation object
        // throw new UnsupportedOperationException();
    	List<User> produits = this.userfacade.findAll();
    	return produits; 
    }
    
    @POST
    @ApiOperation(
    value = "Rechercher les Utilisateur par email", 
    notes = "Rechercher les Utilisateur par email"
    )
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_FORM_URLENCODED})
    @Path("/getUserByEmail")
    public List<User> getProductByType(@FormParam("email") String email) {
        // TODO return proper representation object
        // throw new UnsupportedOperationException();
    	List<User> user = this.userfacade.findUserByEmail(email);
    	return user;
    }
    
    @POST
    @ApiOperation(
    value = "Rechercher les Utilisateur par prénom", 
    notes = "Rechercher les Utilisateur par prénom"
    )
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_FORM_URLENCODED})
    @Path("/getUserByFirstName")
    public List<User> getUserByFirstName(@FormParam("firstName") String firstName) {
        // TODO return proper representation object
        // throw new UnsupportedOperationException();
    	List<User> user = this.userfacade.findUserByFirstName(firstName);
    	return user;
    }
    
    @POST
    @ApiOperation(
    value = "Rechercher les Utilisateur par Nom de famille", 
    notes = "Rechercher les Utilisateur par Nom de famille"
    )
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_FORM_URLENCODED})
    @Path("/getUserByLastName")
    public List<User> getUserByLastName(@FormParam("lastName") String lastName) {
        // TODO return proper representation object
        // throw new UnsupportedOperationException();
    	List<User> user = this.userfacade.findUserByLastName(lastName); 
    	return user;
    }
    
    @POST
    @ApiOperation(
    value = "Rechercher les Utilisateur par Nom de famille", 
    notes = "Rechercher les Utilisateur par Nom de famille"
    )
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_FORM_URLENCODED})
    @Path("/getUserByTelephone")
    public List<User> getUserByTelephone(@FormParam("telephone") String telephone) {
        // TODO return proper representation object
        // throw new UnsupportedOperationException();
    	List<User> user = this.userfacade.findUserByTelephone(telephone); 
    	return user;
    }
    
    @POST
    @ApiOperation(
    value = "Retourne tous les utilisateurs qui ont activer leurs compte", 
    notes = "Retourne tous les utilisateurs qui ont activer leurs compte"
    )
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_FORM_URLENCODED})
    @Path("/getAllUserActivated")
    public List<User> getAllUserActivated(@FormParam("activated") boolean activated) {
        // TODO return proper representation object
        // throw new UnsupportedOperationException();
    	
    	return  this.userfacade.getAllUserActivated(activated);
    }
    
    @POST 
    @ApiOperation(
            value = "Ajouter un nouvel Utilisateur",
            notes = "Ajouter un nouvel Utilisateur"
    )
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_FORM_URLENCODED})
    @Path("/createUser")
    public Response createUser(	  @FormParam("idUser") String idUser,
    							  @FormParam("activated") boolean activated, 
    							  @FormParam("email") String email,
    							  @FormParam("firstname") String firstname,
    							  @FormParam("lastname") String lastname,
    							  @FormParam("password") String password,
    							  @FormParam("telephone") int telephone ) {
    	
    		User nouveau = new User();
    			nouveau.setIdUser(idUser);
		    	nouveau.setActived(activated);
		    	nouveau.setEmail(email);
		    	nouveau.setFirstname(firstname);
		    	nouveau.setLastname(lastname);
		    	nouveau.setPassword(password); 
		    	nouveau.setTelephone(telephone);
    	this.userfacade.create(nouveau); 
    	return Response.status(Response.Status.CREATED).build();
    }
    
    @POST 
    @ApiOperation(
            value = "Ajouter un nouvel Utilisateur",
            notes = "Ajouter un nouvel Utilisateur"
    )
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_FORM_URLENCODED})
    @Path("/modifyUser")
    public Response modifyUser(	  @FormParam("idUser") String idUser,
    							  @FormParam("activated") boolean activated, 
    							  @FormParam("email") String email,
    							  @FormParam("firstname") String firstname,
    							  @FormParam("lastname") String lastname,
    							  @FormParam("password") String password,
    							  @FormParam("telephone") int telephone ) {
    	
    		User nouveau = (User) userfacade.findUserByEmail(email);
    			nouveau.setIdUser(idUser);
		    	nouveau.setActived(activated);
		    	nouveau.setEmail(email);
		    	nouveau.setFirstname(firstname);
		    	nouveau.setLastname(lastname);
		    	nouveau.setPassword(password); 
		    	nouveau.setTelephone(telephone);
    	this.userfacade.edit(nouveau); 
    	return Response.status(Response.Status.CREATED).build();
    }
    
    @POST 
    @ApiOperation(
            value = "Ajouter un nouvel Utilisateur",
            notes = "Ajouter un nouvel Utilisateur"
    )
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_FORM_URLENCODED})
    @Path("/deleteUser")
    public Response deleteUser(	@FormParam("email") String email) {
    	
    	User nouveau = (User) userfacade.findUserByEmail(email);
    	this.userfacade.remove(nouveau); 
    	return Response.status(Response.Status.CREATED).build();
    }
    

    @POST
    @ApiOperation(
    value = "Permet l'authentification des utilisateurs", 
    notes = "Permet l'authentification des utilisateurs"
    )
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_FORM_URLENCODED})
	@Path("/check")
	public Response check(
			@FormParam("name") String name,
			@FormParam("password") String password) throws URISyntaxException {

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