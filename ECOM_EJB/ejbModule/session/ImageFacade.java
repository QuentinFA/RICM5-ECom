package session;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
	
    @SuppressWarnings("unchecked")
	public List<Image> findImageUrlByUserAndProduct(String idProduct, String idUser) {
        Query query = em.createQuery("SELECT p FROM Image p where p.idProduct = '"+idProduct+"' AND p.idUser = '"+idUser+"'");
        return (List<Image>) query.getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public List<Image> findImageUrlByProduct(String idProduct) {
        Query query = em.createQuery("SELECT p FROM Image p where p.idProduct = '"+idProduct+"'");
        return (List<Image>) query.getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public List<Image> findImageUrlByUser( String idUser) {
        Query query = em.createQuery("SELECT p FROM Image p where p.idUser = '"+idUser+"'");
        return (List<Image>) query.getResultList();
    }
}
