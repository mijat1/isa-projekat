package serverapp.isaBack.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class ComplaintUser {
	@Id
	private UUID id;
	
	@Column
	private ComplaintUserId complaintUserId;
	
    @Column(name = "date")
	private Date date;
    
    @Column(name="user_name")
	private String userName;
    
	@Column(name="text")
	private String text;
	
	@Column(name="reply")
	private String reply;
	
	@Column(name="email")
	private String email;
	
	@Column(name="active")
	private boolean active;
	
	@Column(name="profession")
	private String profession;
	
	
	public ComplaintUser() {}
	
	public ComplaintUser(User user, Client client, String text, String name, String profession, String email, boolean active) {
		this(UUID.randomUUID(),user, client, text, new Date(), "", name, profession, email, active);
	}
	
	public ComplaintUser(UUID id,User user, Client client, String text, Date date, String reply, String name, String profession, String email, boolean active) {
		super();
		this.id = id;
		this.complaintUserId = new ComplaintUserId(user, client);
		this.date=date;
		this.text=text;
		this.reply=reply;
		this.userName=name;
		this.profession = profession;
		this.email = email;
		this.active = active;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public UUID getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}


	public void setReply(String reply) {
		this.reply = reply;
	}
	
	public String getReply() {
		return reply;
	}



	public ComplaintUserId getComplaintUserId() {
		return complaintUserId;
	}

	public void setComplaintUserId(ComplaintUserId complaintUserId) {
		this.complaintUserId = complaintUserId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public User getUser() {
		return complaintUserId.getUser();
	}

	public void setPharmacy(User user) {
		complaintUserId.setUser(user);
	}
	
	public Client getClient() {
		return complaintUserId.getClient();
	}

	public void setClient(Client client) {
		complaintUserId.setClient(client);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
}
