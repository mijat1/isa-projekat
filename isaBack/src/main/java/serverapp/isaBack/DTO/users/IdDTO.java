package serverapp.isaBack.DTO.users;

import java.util.UUID;

public class IdDTO {

private UUID id;
	
	
	public IdDTO() {
	}	

	
	
	public IdDTO(UUID id) {
		super();
		this.id = id;
	}



	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
	
}
