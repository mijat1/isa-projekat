package serverapp.isaBack.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UnitFeedback {
	@Id
    @Column(name = "id")
	private UUID id;
	
		
	@ManyToOne(optional = false)
	private Unit unit;
	
    @ManyToOne(optional = false)
	private Client client;
	
	@Column(name = "date")
	private Date date;
    
	@Column(name="grade")
	private int grade;
	
	

	public UnitFeedback() {
	}

	public UnitFeedback(UUID id, Unit unit, Client client, Date date, int grade) {
		super();
		this.id = id;
		this.unit = unit;
		this.client = client;
		this.date = date;
		this.grade = grade;
	}
	
	public UnitFeedback(Unit unit, Client client, Date date, int grade) {
		super();
		this.id=UUID.randomUUID();
		this.unit = unit;
		this.client = client;
		this.date = date;
		this.grade = grade;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	
	
}
