package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the USER database table.
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String idUser;

	private boolean actived;

	private String email;

	private String firstname;

	private String lastname;

	private String password;

	private int telephone;

	public User() {
	}

	public String getIdUser() {
		return this.idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public boolean getActived() {
		return this.actived;
	}

	public void setActived(boolean actived) {
		this.actived = actived;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTelephone() {
		return this.telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

}