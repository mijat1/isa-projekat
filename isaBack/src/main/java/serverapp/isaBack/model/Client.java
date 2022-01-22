package serverapp.isaBack.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;




@Entity
public class Client extends User {

	private static final long serialVersionUID = 1L;


	@ManyToMany
	@JoinTable(name = "client_unit_subscribe")
    private List<Unit> units;
	

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
	
	
	public List<Unit> getUnits() {
		return units;
	}

	public void setUnits(List<Unit> units) {
		this.units = units;
	}

	public void addSubscribeToUnit(Unit unit) {
		
		if(unit == null)
			this.units = new ArrayList<Unit>();
		
		this.units.add(unit);
	}

	public void removeSubscribeFromUnit(UUID unitId) {
		
		if(units == null)
			return;
		
		for (Unit unit : this.units) {
			System.out.println(unit.getId());
			if(unit.getId().equals(unitId)) {
				System.out.println("brisem" + unit.getId());
				this.units.remove(unit);
				break;
			}
		}
	}
	
	public boolean isClientSubscribedToUnit(UUID unitId) {
		if(units == null)
			return false;
		
		for (Unit unit : this.units) {
			if(unit.getId().equals(unitId)) 
				return true;
		}
		
		return false;
	}
}
