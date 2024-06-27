package com.ecom.dto;

import org.springframework.web.multipart.MultipartFile;

public class ProductDTO {

	private long id;
	private String name;
	private long price;
	private String description;
	private byte[] byteimg;
	private long categoryid;
	private MultipartFile img;
	
	public ProductDTO() {
	
	}

	public ProductDTO(long id, String name, long price, String description, byte[] byteimg, long categoryid,
			MultipartFile img) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.byteimg = byteimg;
		this.categoryid = categoryid;
		this.img = img;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getByteimg() {
		return byteimg;
	}

	public void setByteimg(byte[] byteimg) {
		this.byteimg = byteimg;
	}

	public long getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(long categoryid) {
		this.categoryid = categoryid;
	}

	public MultipartFile getImg() {
		return img;
	}

	public void setImg(MultipartFile img) {
		this.img = img;
	}
	
}
