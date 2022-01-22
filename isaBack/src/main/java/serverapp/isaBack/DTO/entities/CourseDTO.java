package serverapp.isaBack.DTO.entities;

import java.util.List;

import serverapp.isaBack.unspecifiedDTO.UnspecifiedDTO;

public class CourseDTO {
	private String name;

	private String address;

	private String description;
		
	private byte[] image;
	
	private List<UnspecifiedDTO<OtherTagDTO>> services;
	
	private double avgGrade;
	
	private String instructor;
	
	private String biography;
	
	private double price;
	
	public CourseDTO(String name, String address, String description, byte[] image,
			List<UnspecifiedDTO<OtherTagDTO>> services, double avgGrade,String instructor,String biography,double price) {
		super();
		this.name = name;
		this.address = address;
		this.description = description;
		this.image = image;
		this.setServices(services);
		this.avgGrade = avgGrade;
		this.instructor=instructor;
		this.biography=biography;
		this.price = price;
	}


	public CourseDTO() {}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}



	public double getAvgGrade() {
		return avgGrade;
	}

	public void setAvgGrade(double avgGrade) {
		this.avgGrade = avgGrade;
	}


	public List<UnspecifiedDTO<OtherTagDTO>> getServices() {
		return services;
	}


	public void setServices(List<UnspecifiedDTO<OtherTagDTO>> services) {
		this.services = services;
	}


	public String getInstructor() {
		return instructor;
	}


	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}


	public String getBiography() {
		return biography;
	}


	public void setBiography(String biography) {
		this.biography = biography;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
