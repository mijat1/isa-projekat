package serverapp.isaBack.DTO.users;

import java.util.UUID;
import java.util.Date;

public class ComplaintUnitDTO {

	private UUID unitId;
	
	private String unitName;
	
	private String clientEmail;
	
	private Date Date;
    
	private String text;
	
	private String reply;
	
	public ComplaintUnitDTO() {}
		
	public ComplaintUnitDTO(UUID unitId, Date date, String text,String reply, String clientEmail,String unitName) {
		this.unitId= unitId;
		this.Date=date;
		this.text=text;
		this.reply = reply;
		this.clientEmail = clientEmail;
		this.unitName= unitName;
	}

	
	
	public UUID getUnitId() {
		return unitId;
	}

	public void setUnitId(UUID unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public Date getDate() {
		return Date;
	}

	public void setDate(Date date) {
		Date = date;
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
	

}
