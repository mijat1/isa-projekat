package serverapp.isaBack.DTO.entities;

import java.util.List;
import java.util.UUID;

import serverapp.isaBack.unspecifiedDTO.UnspecifiedDTO;

public class BoatDTO {

	private String name;

	private String address;

	private String description;
		
	private byte[] image;
	
	private List<UnspecifiedDTO<OtherTagDTO>> services;
	
	private double avgGrade;

	
	public BoatDTO(String name, String address, String description, byte[] image,
			List<UnspecifiedDTO<OtherTagDTO>> services, double avgGrade) {
		super();
		this.name = name;
		this.address = address;
		this.description = description;
		this.image = image;
		this.services = services;
		this.avgGrade = avgGrade;
	}



	public BoatDTO() {}

	

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

	public List<UnspecifiedDTO<OtherTagDTO>> getServices() {
		return services;
	}

	public void setServices(List<UnspecifiedDTO<OtherTagDTO>> services) {
		this.services = services;
	}

	public double getAvgGrade() {
		return avgGrade;
	}

	public void setAvgGrade(double avgGrade) {
		this.avgGrade = avgGrade;
	}
	
	
 
 
}
