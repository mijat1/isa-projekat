package serverapp.isaBack.DTO.entities;

import serverapp.isaBack.model.UnitType;

public class UnitSDTO {
	private String name;	
	String description;
	private double price;
	private double grade;
	
	public UnitSDTO(String name, String description, double price, double grade) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.grade = grade;
	}
	public UnitSDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
