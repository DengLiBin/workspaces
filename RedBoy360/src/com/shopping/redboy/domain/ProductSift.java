package com.shopping.redboy.domain;

public class ProductSift {
	private String brand;
	private String price;
	private String function;
	private String number;
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "ProductSift [brand=" + brand + ", price=" + price
				+ ", function=" + function + ", number=" + number + "]";
	}
	
}
