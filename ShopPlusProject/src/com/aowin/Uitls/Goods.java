package com.aowin.Uitls;

public class Goods {
	int id;
	String name;
	int num;
	double price;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Goods(int id, String name, int num, double price) {
		this.id = id;
		this.name = name;
		this.num = num;
		this.price = price;
	}
	public Goods() {}
}
