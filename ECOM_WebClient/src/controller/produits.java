package controller;

import java.util.List;

import entities.Image;

public class produits {
	
	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Images> getImages() {
		return images;
	}

	public void setImages(List<Images> images2) {
		this.images = images2;
	}

	private int idProduct;

	private String description;

	private String idUser;

	private int price;

	private String title;

	private String type;
	
	private List<Images> images;
}
