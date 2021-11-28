package serverapp.isaBack.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;

public class OtherTag {
	@Id
	private UUID id;
	
	@Column(name = "name", nullable = false, unique = true)
	private String name;

	public OtherTag() {}
	
	public OtherTag(String name) {
		this(UUID.randomUUID(), name);
	}
	public OtherTag(UUID id, String name) {
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
