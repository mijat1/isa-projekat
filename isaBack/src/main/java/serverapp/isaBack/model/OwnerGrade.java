package serverapp.isaBack.model;


import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class OwnerGrade {

	
	@Id
    @Column(name = "id")
	private UUID id;
	
	
	@ManyToOne(optional = false)
	private User owner;
	
    @ManyToOne(optional = false)
	private Client client;
    
	private int grade;
	
	private Date date;

	public OwnerGrade() {
		super();
	}

	public OwnerGrade(User owner,Client client, int grade, Date date) {
		super();
		this.id=UUID.randomUUID();
		this.owner = owner;
		this.client = client;
		this.grade = grade;
		this.date = date;
	}

	
	
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	


	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
