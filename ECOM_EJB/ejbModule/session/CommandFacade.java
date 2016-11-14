package session;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.Command;

@Stateless
public class CommandFacade extends AbstractFacade<Command>{
	
    @PersistenceContext(unitName = "ECOM_EJB")
    protected EntityManager em;
    
	public CommandFacade() {
		super(Command.class);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}
	
    @SuppressWarnings("unchecked")
	public List<Command> findCommandUrlByUserAndProduct(String idProduct, String idUser) {
        Query query = em.createQuery("SELECT p FROM Command p where p.idProduct = '"+idProduct+"' AND p.idUser = '"+idUser+"'");
        return (List<Command>) query.getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public List<Command> findCommandUrlByState(String state) {
        Query query = em.createQuery("SELECT p FROM Image p where p.state = '"+state+"'");
        return (List<Command>) query.getResultList();
    }
    
}