package serverapp.isaBack.DTO.entities;

import java.util.UUID;



public class UnitDTO {

	private String name;
		
	private byte[] image;
	
	private int percentOfCancel;

	public UnitDTO( String name, byte[] image,int percentOfCancel) {
		super();
		
		this.name = name;
		this.image = image;
		this.percentOfCancel = percentOfCancel;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}


	public int getPercentOfCancel() {
		return percentOfCancel;
	}


	public void setPercentOfCancel(int percentOfCancel) {
		this.percentOfCancel = percentOfCancel;
	}
	
	
	
	
}
