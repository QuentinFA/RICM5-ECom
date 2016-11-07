package session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Image;

@Stateless
public class ImageFacade extends AbstractFacade<Image>{
	
    @PersistenceContext(unitName = "ECOM_EJB")
    protected EntityManager em;
    
	public ImageFacade() {
		super(Image.class);
	}
	
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
}
