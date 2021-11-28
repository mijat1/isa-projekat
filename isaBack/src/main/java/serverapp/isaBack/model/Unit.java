package serverapp.isaBack.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Unit")
public class Unit {
	@Id
	private UUID id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "rules", nullable = false)
	private String rules;
	
    @OneToOne
    private Album album;
    
    private double price;
    
    @Enumerated(EnumType.STRING)
   	@Column(name="cancellation")
   	private Cancellation cancellation;
    
    private int percentOfCancel;



	public Unit() {
	}
	
	public Unit(String name, String address, String description, String rules, Album album, double price,
			Cancellation cancellation, int percentOfCancel) {
		super();
		this.id = UUID.randomUUID();
		this.name = name;
		this.address = address;
		this.description = description;
		this.rules = rules;
		this.album = album;
		this.price = price;
		this.cancellation = cancellation;
		if(cancellation==Cancellation.FREE)
		   this.percentOfCancel = 0;
	}

	public Unit(UUID id, String name, String address, String description, String rules, Album album, double price,
			Cancellation cancellation, int percentOfCancel) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.description = description;
		this.rules = rules;
		this.album = album;
		this.price = price;
		this.cancellation = cancellation;
		if(cancellation==Cancellation.FREE)
		  this.percentOfCancel = 0;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}
	
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Cancellation getCancellation() {
		return cancellation;
	}

	public void setCancellation(Cancellation cancellation) {
		this.cancellation = cancellation;
	}

	public int getPercentOfCancel() {
		return percentOfCancel;
	}

	public void setPercentOfCancel(int percentOfCancel) {
		this.percentOfCancel = percentOfCancel;
	}

}
