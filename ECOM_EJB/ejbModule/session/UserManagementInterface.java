package session;

import java.util.List;

import javax.ejb.Local;

import entities.User;

@Local
public interface UserManagementInterface {
	void connexion(User c);
	void deconnexion();
	List<User> getClient();
}
