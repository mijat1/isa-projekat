package serverapp.isaBack.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;




@Entity
public class Client extends User {

	private static final long serialVersionUID = 1L;




	public Client() {
		super();
	}

	public Client(String email, String password, String name, String surname, String address, String phoneNumber) {
		super(email, password, name, surname, address, phoneNumber, false);


	}



	public Client(UUID id, String email, String password, String name, String surname, String address,
			String phoneNumber, boolean active) {
		super(id, email, password, name, surname, address, phoneNumber, active);


	}

}
