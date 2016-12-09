package entities;

import java.io.Serializable; 
import javax.persistence.*;


/**
 * The persistent class for the CATEGORY database table.
 * 
 */
@Entity
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCommand;

	private String type;

	public Category() { 
	}

	public int getidCategory() {
		return this.idCommand;
	}

	public void setidCategory(int idCategory) {
		this.idCommand = idCategory;
	}

	public String getType() {
		return this.type;
	} 

	public void setType(String type) {
		this.type = type;
	}

}