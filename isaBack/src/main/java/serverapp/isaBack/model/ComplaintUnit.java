package serverapp.isaBack.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class ComplaintUnit {
	@Id
	private UUID id;
	
	@Column
	private ComplaintUnitId complaintUnitId;
	
    @Column(name = "date")
	private Date date;
    
    @Column(name="unit_name")
	private String unitName;
    
	@Column(name="text")
	private String text;
	
	@Column(name="reply")
	private String reply;
	
	@Column(name="email")
	private String email;
	
	@Column(name="active")
	private boolean active;
	

	
	
	public ComplaintUnit() {}
	
	public ComplaintUnit(Unit unit, Client client, String text,String unitName,String email) {
		this(UUID.randomUUID(), unit, "", client, text,unitName,email, new Date());
	}
	
	public ComplaintUnit(UUID id,Unit unit, String reply, Client client,String text,String unitName,String email, Date date) {
		super();
		this.id = id;
		this.complaintUnitId = new ComplaintUnitId(unit, client);
		this.date=date;
		this.unitName=unitName;
		this.email=email;
		this.text=text;
		this.reply = reply;
		this.active = true;
		
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

	public ComplaintUnitId getComplaintUnitId() {
		return complaintUnitId;
	}

	public void setComplaintUnitId(ComplaintUnitId complaintUnitId) {
		this.complaintUnitId = complaintUnitId;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Unit getUnit() {
		return complaintUnitId.getUnit();
	}

	public void setPharmacy(Unit unit) {
		complaintUnitId.setUnit(unit);
	}
	
	public Client getClient() {
		return complaintUnitId.getClient();
	}

	public void setClient(Client client) {
		complaintUnitId.setClient(client);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	
}
