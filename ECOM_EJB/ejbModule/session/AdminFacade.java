package session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}