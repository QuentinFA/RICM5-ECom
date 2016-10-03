package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Image database table.
 * 
 */
@Embeddable
public class ImagePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="product_id")
	private int productId;

	@Column(name="user_id")
	private int userId;

	@Column(name="img_name")
	private String imgName;

	public ImagePK() {
	}
	public int getProductId() {
		return this.productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getUserId() {
		return this.userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getImgName() {
		return this.imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ImagePK)) {
			return false;
		}
		ImagePK castOther = (ImagePK)other;
		return 
			(this.productId == castOther.productId)
			&& (this.userId == castOther.userId)
			&& this.imgName.equals(castOther.imgName);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.productId;
		hash = hash * prime + this.userId;
		hash = hash * prime + this.imgName.hashCode();
		
		return hash;
	}
}