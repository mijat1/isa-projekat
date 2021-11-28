package serverapp.isaBack.model;

import java.util.UUID;

import javax.persistence.Entity;

@Entity
public class FishingInstructor extends User{

private static final long serialVersionUID = 1L;
	
	public FishingInstructor() {
		super();
	}

	public FishingInstructor(String email, String password, String name, String surname, String address, String phoneNumber) {
		super(email, password, name, surname, address, phoneNumber, false);


	}



	public FishingInstructor(UUID id, String email, String password, String name, String surname, String address,
			String phoneNumber, boolean active) {
		super(id, email, password, name, surname, address, phoneNumber, active);


	}
}
