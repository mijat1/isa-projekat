package serverapp.isaBack.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class ComplaintUnitId implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne(optional = false)
	private Unit unit;
	
	@ManyToOne(optional = false)
	private Client client;
	
	
    public ComplaintUnitId() {}
	
	public ComplaintUnitId(Unit unit, Client client) {
		super();
		this.unit = unit;
		this.client = client;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}



	
}
