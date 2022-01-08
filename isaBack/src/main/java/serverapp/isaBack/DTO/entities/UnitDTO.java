package serverapp.isaBack.DTO.entities;

import java.util.UUID;



public class UnitDTO {

	private UUID id;
	
	
	private String name;
	

	private String address;
	

	private String description;
	
	private String fileName;
	
	private byte[] image;

	public UnitDTO(UUID id, String name, String address, String description, String fileName, byte[] image) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.description = description;
		this.fileName = fileName;
		this.image = image;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
	
	
	
}
