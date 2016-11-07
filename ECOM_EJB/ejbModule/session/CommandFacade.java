package session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}