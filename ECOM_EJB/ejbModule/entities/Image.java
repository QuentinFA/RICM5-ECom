package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Image database table.
 * 
 */
@Entity
@NamedQuery(name="Image.findAll", query="SELECT i FROM Image i")
public class Image implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ImagePK id;

	public Image() {
	}

	public ImagePK getId() {
		return this.id;
	}

	public void setId(ImagePK id) {
		this.id = id;
	}

}