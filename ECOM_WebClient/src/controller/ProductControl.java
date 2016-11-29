package controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.File;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
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
import session.ProductFacade;
import tools.AmazonS3ClientInstance;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

import entities.Image;
import entities.Product;

@Path("/product")
@Api(value="/product", description = "Toutes les requêtes concernant les produits")
public class ProductControl {
   
//    @SuppressWarnings("unused")
    @Context
    private UriInfo context;
     
    @EJB
	private ProductFacade productfacade = new ProductFacade();
    @EJB
    private ImageFacade imagefacade = new ImageFacade();

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
    							  @CookieParam(value = "user") String idUser,
    							  @FormParam("price") int price,
    							  @FormParam("title") String title,
    							  @FormParam("type") String type,
    							  @FormParam("image0") File image0,
    							  @FormParam("path") String path) {
    	
    	System.out.println("Userrrrrrrrrrrrr " +idUser);
		List<Product> produits = this.productfacade.findProductByIdUserAndTitle("zhaozilong", title);
		if(produits.size() != 0){
			return Response.status(200)
					.entity("You have already published an announce with this title before! Try another!")
					.build();
		}
		upLoadImage(path+image0.getName(), image0.getName());
		
		Product nouveau = new Product();
    	nouveau.setDescription(description);
    	nouveau.setIdUser("zhaozilong");
    	nouveau.setPrice(price);
    	nouveau.setTitle(title);
    	nouveau.setType(type);
    	this.productfacade.create(nouveau);
    	List<Product> newProduct = this.productfacade.findProductByIdUserAndTitle("zhaozilong", title);
    	
    	
    	   	
		Image newImage = new Image();
		newImage.setIdImage(15);
		newImage.setIdProduct(newProduct.get(0).getIdProduct());
		newImage.setIdUser(newProduct.get(0).getIdUser());
		newImage.setImgUrl("lien vers s3");
		this.imagefacade.create(newImage);
    	 
//    	System.out.println("Annonce créé : derscription: "+description +"\n idUser :"+idUser +"\n price: "+price+" \n title: "+title+" \n type:"+type+" \n");
    	System.out.println("Annonce créé : derscription: "+description +"\n price: "+price+" \n title: "+title+" \n type:"+type+" \n image: "+image0.getAbsolutePath());
    	
    	return Response.status(Response.Status.CREATED).build();
    }
    
    
    @POST 
    @ApiOperation(
            value = "Modifier un nouveau produit",
            notes = "Modifier un nouveau produit"
    )
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_FORM_URLENCODED})
    @Path("/modifyProduct")
    public Response modifyProduct(@FormParam("description") String description, 
    							  @FormParam("idUser") String idUser,
    							  @FormParam("price") int price,
    							  @FormParam("title") String title,
    							  @FormParam("type") String type) {
    	
    	Product nouveau = (Product) productfacade.findProductByTitle(title);
		    	nouveau.setDescription(description);
		    	nouveau.setIdUser(idUser);
		    	nouveau.setPrice(price);
		    	nouveau.setTitle(title);
		    	nouveau.setType(type);
	
    	this.productfacade.edit((Product) nouveau);  
    	System.out.println("Annonce modifié : derscription: "+description +"\n idUser :"+idUser +"\n price: "+price+" \n title: "+title+" \n type:"+type+" \n");
    	return Response.status(Response.Status.CREATED).build();
    }
    
    @POST 
    @ApiOperation(
            value = "Modifier un nouveau produit",
            notes = "Modifier un nouveau produit"
    )
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_FORM_URLENCODED})
    @Path("/deleteProduct")
    public Response deleteProduct(@FormParam("title") String title) {
    	
    	Product nouveau = (Product) productfacade.findProductByTitle(title);
    	this.productfacade.remove((Product) nouveau);  
    	return Response.status(Response.Status.CREATED).build();
    }
    
    
    
    public void upLoadImage(String file, String key){
    	System.out.println("uploadimage");
        AmazonS3 s3client = AmazonS3ClientInstance.getInstance();
        try {
            System.out.println("Uploading a new object to S3 from a file\n");
            //File file = new File(path);
            s3client.putObject(new PutObjectRequest(
            		                 "web-ecom", key, new File(file)));

         } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " +
            		"means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " +
            		"means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
    }
    
}