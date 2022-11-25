package com.demo.models;

public class Order {

	private int id;
	private String item;
	private double price;
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", item=" + item + ", price=" + price + "]";
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(int id, String item, double price) {
		super();
		this.id = id;
		this.item = item;
		this.price = price;
	}
	public Order(String item, double price) {
		super();
		this.item = item;
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
}
