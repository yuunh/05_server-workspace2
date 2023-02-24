package com.kh.water.model.vo;

public class Water {

	private int waterNo;
	private String brand;
	private int price;
	
	public Water() {}

	public Water(int waterNo, String brand, int price) {
		super();
		this.waterNo = waterNo;
		this.brand = brand;
		this.price = price;
	}
	
	public Water(String brand, int price) {
		super();
		this.brand = brand;
		this.price = price;
	}

	public int getWaterNo() {
		return waterNo;
	}

	public void setWaterNo(int waterNo) {
		this.waterNo = waterNo;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Water [waterNo=" + waterNo + ", brand=" + brand + ", price=" + price + "]";
	}
	
}
