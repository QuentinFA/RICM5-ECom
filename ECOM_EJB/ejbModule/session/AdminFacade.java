package session;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.Admin;

@Stateless
public class AdminFacade extends AbstractFacade<Admin>{
	
    @PersistenceContext(unitName = "ECOM_EJB")
    protected EntityManager em;
    
	public AdminFacade() {
		super(Admin.class);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}
    @SuppressWarnings("unchecked")
	public List<Admin> findUserByFirstName(String FirstName) {
        Query query = em.createQuery("SELECT u FROM Admin u where u.firstname = '"+FirstName+"' ");
        return (List<Admin>) query.getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public List<Admin> findUserByLastName(String LasttName) {
        Query query = em.createQuery("SELECT u FROM Admin u where u.lastname = '"+LasttName+"' ");
        return (List<Admin>) query.getResultList();
    }
    
    public Admin findUserByLastNameAndFirst(String LasttName, String FirstName) {
        Query query = em.createQuery("SELECT u FROM Admin u where u.lastname = '"+LasttName+"' AND u.firstname = '"+FirstName+"' ");
        return (Admin) query.getSingleResult();
    }
}