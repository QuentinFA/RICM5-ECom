package session;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.User;

@Stateful
@StatefulTimeout(unit = TimeUnit.MINUTES, value = 30)
public class UserManagement implements UserManagementInterface{
	User Client = null;
	List<User> liste;
	int compteur = 0;

	  @PersistenceContext
	  private EntityManager em;

	@Override
	public void connexion(User c) {
		Client =c;
		liste.add(c);
		System.out.println("Client enregistré "+liste.get(0).getLastname());
	}
	
	  @PostConstruct
	  private void init(){
			System.out.println("Client constructeur ");
			Client = null;
			liste =  new ArrayList<User>();
	  }

	@Override
	public void deconnexion() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> getClient() {
		// TODO Auto-generated method stub
		compteur++;
		System.out.println("Client restitué "+liste+" "+compteur);
		return liste; 
	}

}
