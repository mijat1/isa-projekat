package serverapp.isaBack.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FishingEquipment {
	@Id
	private UUID id;
	
	@Column(name = "name", nullable = false, unique = true)
	private String name;

	public FishingEquipment() {}
	
	public FishingEquipment(String name) {
		this(UUID.randomUUID(), name);
	}
	public FishingEquipment(UUID id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	
}
