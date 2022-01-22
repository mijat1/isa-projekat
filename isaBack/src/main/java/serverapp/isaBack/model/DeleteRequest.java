package serverapp.isaBack.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class DeleteRequest {
	@Id
	private UUID id;
	
	@OneToOne(optional = false)
	private User user;
	
	private Date date;
	
	private String text;
	
	private String reply;
	
	private boolean active;

	public DeleteRequest(User user, Date current, String text) {
		super();
		this.id = UUID.randomUUID();
		this.user = user;
		this.date = current;
		this.text = text;
	}
	
	

	public DeleteRequest() {
		super();
		// TODO Auto-generated constructor stub
	}



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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



	public UUID getId() {
		return id;
	}



	public void setId(UUID id) {
		this.id = id;
	}



	public String getReply() {
		return reply;
	}



	public void setReply(String reply) {
		this.reply = reply;
	}



	public boolean isActive() {
		return active;
	}



	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	
}
