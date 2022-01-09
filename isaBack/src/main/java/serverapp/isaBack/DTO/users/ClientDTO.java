package serverapp.isaBack.DTO.users;

import java.util.List;

import serverapp.isaBack.model.Authority;

public class ClientDTO extends UserDTO{
	
	public ClientDTO(String email, String name, String surname, String address, String phoneNumber, boolean active,
			List<Authority> authorities) {
		super(email, name, surname, address, phoneNumber, active, authorities);
		
	}

}
