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

import session.ImageFacade;
import entities.Image;

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

	@POST
	@ApiOperation(
			value = "Rechercher les annonces par idUser et idProduct", 
			notes = "Rechercher les annonces par idUser et idProduct"
			)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_FORM_URLENCODED})
	@Path("/findImageUrlByUserAndProduct")
	public List<Image> findImageUrlByUserAndProduct(@FormParam("idProduct") String idProduct, @FormParam("idUser") String idUser) {
		// throw new UnsupportedOperationException();
		List<Image> image = this.imageFacade.findImageUrlByUserAndProduct(idProduct, idUser);
		return image;
	}

	@POST
	@ApiOperation(
			value = "Rechercher les annonces par idUser et idProduct", 
			notes = "Rechercher les annonces par idUser et idProduct"
			)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_FORM_URLENCODED})
	@Path("/findImageUrlByProduct")
	public List<Image> findImageUrlByProduct(@FormParam("idProduct") String idProduct) {
		// TODO return proper representation object
		// throw new UnsupportedOperationException();
		List<Image> image = this.imageFacade.findImageUrlByProduct(idProduct);
		return image;
	}

	@POST
	@ApiOperation(
			value = "Rechercher les annonces par idUser et idProduct", 
			notes = "Rechercher les annonces par idUser et idProduct"
			)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_FORM_URLENCODED})
	@Path("/findImageUrlByUser")
	public List<Image> findImageUrlByUser(@FormParam("idUser") String idUser) {
		// TODO return proper representation object
		// throw new UnsupportedOperationException();
		List<Image> image = this.imageFacade.findImageUrlByUser( idUser);
		return image;
	}

	@POST 
	@ApiOperation(
			value = "Ajouter un nouvel Image",
			notes = "Ajouter un nouvel Image"
			)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_FORM_URLENCODED})
	@Path("/createImage")
	public Response createImage(@FormParam("idProduct") String idProduct, 
			@FormParam("idUser") String idUser,
			@FormParam("url") String imgUrl) {

		Image nouveau = new Image();
		nouveau.setIdProduct(Integer.valueOf(idProduct));
		nouveau.setIdUser(idUser);
		nouveau.setImgUrl(imgUrl);
		this.imageFacade.create(nouveau); 
		return Response.status(Response.Status.CREATED).build();
	}
	
	@POST 
	@ApiOperation(
			value = "Ajouter un nouvel Image",
			notes = "Ajouter un nouvel Image"
			)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_FORM_URLENCODED})
	@Path("/modifyImage")
	public Response modifyImage(@FormParam("idImage") String idImage, 
			@FormParam("idUser") String idUser,
			@FormParam("idProduct") int idProduct,
			@FormParam("url") String imgUrl) {

		Image nouveau = this.imageFacade.find(idImage);
		nouveau.setIdUser(idUser);
		nouveau.setImgUrl(imgUrl);
		nouveau.setIdProduct(idProduct);
		this.imageFacade.edit(nouveau); 
		return Response.status(Response.Status.CREATED).build();
	}
	
	@POST 
	@ApiOperation(
			value = "Ajouter un nouvel Image",
			notes = "Ajouter un nouvel Image"
			)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_FORM_URLENCODED})
	@Path("/deleteImage")
	public Response deleteImage(@FormParam("idProduct") String idProduct, 
			@FormParam("idUser") String idUser,
			@FormParam("url") String imgUrl) {

		Image nouveau = (Image) this.imageFacade.findImageUrlByUserAndProduct(idProduct, idUser);
		this.imageFacade.remove(nouveau); 
		return Response.status(Response.Status.CREATED).build();
	}
}
