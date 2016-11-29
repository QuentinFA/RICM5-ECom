package controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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

	@POST
	@ApiOperation(
			value = "Rechercher les Utilisateur par email", 
			notes = "Rechercher les Utilisateur par email"
			)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_FORM_URLENCODED})
	@Path("/getAdminByfirstnameAndlastname")
	public Admin getAdminByfirstnameAndlastname(
			@FormParam("firstname") String firstname,
			@FormParam("lastname") String lastname) {
		// TODO return proper representation object
		// throw new UnsupportedOperationException();
		Admin user = this.adminFacade.findUserByLastNameAndFirst(lastname, firstname);
		return user;
	}

	@POST
	@ApiOperation(
			value = "Rechercher les Utilisateur par prénom", 
			notes = "Rechercher les Utilisateur par prénom"
			)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_FORM_URLENCODED})
	@Path("/getAdminByFirstName")
	public List<Admin> getAdminByFirstName(@FormParam("firstName") String firstName) {
		// TODO return proper representation object
		// throw new UnsupportedOperationException();
		List<Admin> user = this.adminFacade.findUserByFirstName(firstName);
		return user;
	}

	@POST
	@ApiOperation(
			value = "Rechercher les Admin par Nom de famille", 
			notes = "Rechercher les Admin par Nom de famille"
			)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_FORM_URLENCODED})
	@Path("/getAdminByLastName")
	public List<Admin> getAdminByLastName(@FormParam("lastName") String lastName) {
		// TODO return proper representation object
		// throw new UnsupportedOperationException();
		List<Admin> user = this.adminFacade.findUserByLastName(lastName); 
		return user;
	}


	@POST 
	@ApiOperation(
			value = "Ajouter un nouvel Admin",
			notes = "Ajouter un nouvel Admin"
			)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_FORM_URLENCODED})
	@Path("/createAdmin")
	public Response createAdmin(  @FormParam("firstname") String firstname,
			@FormParam("lastname") String lastname,
			@FormParam("password") String password) {

		Admin nouveau = new Admin();
		nouveau.setFirstname(firstname);
		nouveau.setLastname(lastname);
		nouveau.setPassword(password); 
		this.adminFacade.create(nouveau); 
		return Response.status(Response.Status.CREATED).build();
	}

	@POST 
	@ApiOperation(
			value = "Ajouter un nouvel Admin",
			notes = "Ajouter un nouvel Admin"
			)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_FORM_URLENCODED})
	@Path("/modifyAdmin")
	public Response modifyAdmin(
			@FormParam("firstname") String firstname,
			@FormParam("lastname") String lastname,
			@FormParam("password") String password ) {

		Admin nouveau = (Admin) adminFacade.findUserByLastNameAndFirst(lastname, firstname);;
		nouveau.setFirstname(firstname);
		nouveau.setLastname(lastname);
		nouveau.setPassword(password); 
		this.adminFacade.edit(nouveau); 
		return Response.status(Response.Status.CREATED).build();
	}

	@POST 
	@ApiOperation(
			value = "Ajouter un nouvel Utilisateur",
			notes = "Ajouter un nouvel Utilisateur"
			)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_FORM_URLENCODED})
	@Path("/deleteAdmin")
	public Response deleteAdmin(
			@FormParam("firstname") String firstname,
			@FormParam("lastname") String lastname) {

		Admin nouveau = (Admin) adminFacade.findUserByLastNameAndFirst(lastname, firstname);
		this.adminFacade.remove(nouveau); 
		return Response.status(Response.Status.CREATED).build();
	}
}
