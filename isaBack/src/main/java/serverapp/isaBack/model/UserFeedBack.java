package serverapp.isaBack.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UserFeedBack {
	@Id
    @Column(name = "id")
	private UUID id;
	
		
	@ManyToOne(optional = false)
	private User user;
	
    @ManyToOne(optional = false)
	private Client client;
	
	@Column(name = "date")
	private Date date;
    
	@Column(name="grade")
	private int grade;
	
	

	public UserFeedBack() {
	}

	public UserFeedBack(UUID id, User user, Client client, Date date, int grade) {
		super();
		this.id = id;
		this.user = user;
		this.client = client;
		this.date = date;
		this.grade = grade;
	}
	
	public UserFeedBack(User user, Client client, Date date, int grade) {
		super();
		this.id=UUID.randomUUID();
		this.user = user;
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

	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
	
	