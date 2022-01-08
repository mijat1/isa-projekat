package serverapp.isaBack.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OtherTag {
	@Id
	private UUID id;
	
	@Column(name = "name", nullable = false, unique = true)
	private String name;
	
	@Column(name = "price", nullable = false)
	private double price;

	public OtherTag() {}
	
	public OtherTag(String name,double price) {
		super();
		this.id = UUID.randomUUID();
		this.name = name;
		this.price=price;
	}
	public OtherTag(UUID id, String name,double price) {
		super();
		this.id = id;
		this.name = name;
		this.price=price;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
