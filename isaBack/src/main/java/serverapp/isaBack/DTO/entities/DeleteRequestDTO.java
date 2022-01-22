package serverapp.isaBack.DTO.entities;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.OneToOne;

import serverapp.isaBack.model.User;

public class DeleteRequestDTO {

	private UUID id;
	
	private String user;
		
	private String text;
	
	private String reply;

	
	public DeleteRequestDTO(String user, String text, String reply) {
		super();
		this.user = user;
		this.text = text;
		this.reply = reply;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
	
}
