package controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import session.UserFacade;
import session.UserManagement;
import session.UserManagementInterface;
import emailManagement.EmailSessionBean;
import entities.User;

@Path("/user")
@Api(value="/user", description = "Toutes les requêtes concernant les Utilisateurs")
public class UserControl {

	@Context
	private UriInfo context;

	@EJB
	private UserFacade userfacade = new UserFacade();

	@EJB
	private UserManagementInterface userManager=new UserManagement();
	
	@EJB
	private EmailSessionBean emailEJB =new EmailSessionBean();
	
	
	
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
			value = "Rechercher les Utilisateur par ID", 
			notes = "Rechercher les Utilisateur par ID"
			)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_FORM_URLENCODED})
	@Path("/getUserByID/{id}")
	public List<User> getProductByID(@PathParam("id") String id) {
		// TODO return proper representation object
		// throw new UnsupportedOperationException();
		List<User> user = this.userfacade.findUserByID(id);  
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
	public Response createUser(@FormParam("idUser") String idUser,
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
		emailEJB.sendMail("medewou@gmail.com","test","un test");
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
			@Context HttpServletResponse response,
			@Context HttpServletRequest request,
			@FormParam("name") String name,
			@FormParam("password") String password) throws URISyntaxException {

		User user = userfacade.find(name);
		if(user != null){
			
			String pass = user.getPassword();
			
			if( pass.equals(password)){
				Cookie loginCookie = new Cookie("user",name);
				//setting cookie to expiry in 30 mins
				loginCookie.setMaxAge(30*60);
				response.addCookie(loginCookie);
				//response.sendRedirect("#");
				return Response.status(Response.Status.ACCEPTED).build();
			}else{
				return Response.status(Response.Status.FORBIDDEN).build();
			}
		} else{
			return Response.status(Response.Status.FORBIDDEN).build();
		}

	}

	@GET
	@ApiOperation(
			value = "Retourne l'utilisateur courant", 
			notes = "Retourne l'utilisateur courant"
			)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/getClient")
	public List<User> getClient() {
		// TODO return proper representation object
		// throw new UnsupportedOperationException();
 
		return  this.userManager.getClient();
	}

}
