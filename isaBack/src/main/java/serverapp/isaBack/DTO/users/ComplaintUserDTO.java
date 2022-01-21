package serverapp.isaBack.DTO.users;

import java.util.Date;
import java.util.UUID;


public class ComplaintUserDTO {
	
	private UUID ownerId;
	
	private Date date;
    
	private String text;

	private String userName;

	private String profession;
	
	private String reply;
	
	private String email;
	
	

	public ComplaintUserDTO() {}
		
	public ComplaintUserDTO(UUID ownerId, Date date, String text, String name, String profession, String reply, String email) {
		this.ownerId= ownerId;
		this.date=date;
		this.text=text;
		this.userName=name;
		this.profession = profession;
		this.reply = reply;
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UUID getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(UUID ownerId) {
		this.ownerId = ownerId;
	}

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


}
