package serverapp.isaBack.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;




@Entity
public class Reservation {

	@Id
    @Column(name = "id")
	private UUID id;
	
	@ManyToOne
	private User owner;
	
	@ManyToOne
	private Unit unit;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "reservation_tags",
            joinColumns = @JoinColumn(name = "reservation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "drugOrder_id", referencedColumnName = "id"))
    private List<OtherTag> tags;
	
	
    @Column(name = "startDateTime")
	private Date startDateTime;
	    
	@Column(name = "endDateTime")
	private Date endDateTime;
	
    @Column(name = "price")
    private double price;
    
    @ManyToOne
	private Client client;
    
    @Enumerated(EnumType.STRING)
  	@Column(name = "reservationType", nullable = false)
  	private ReservationType reservationType;
      
    @Enumerated(EnumType.STRING)
  	@Column(name = "reservationStatus", nullable = false)
  	private ReservationStatus reservationStatus;

	
    
    
    public Reservation() {
		super();
		
	}


	public Reservation(User owner,Unit unit, Date startDateTime, Date endDateTime, double price, Client client,
			ReservationType reservationType, ReservationStatus reservationStatus) {
		super();
		this.id = UUID.randomUUID();
		this.owner = owner;
		this.unit=unit;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.price = price;
		this.client = client;
		this.reservationType = reservationType;
		this.reservationStatus = reservationStatus;
	}

	
	public UUID getId() {
		return id;
	}


	public void setId(UUID id) {
		this.id = id;
	}




	public Date getStartDateTime() {
		return startDateTime;
	}


	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}


	public Date getEndDateTime() {
		return endDateTime;
	}


	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public User getOwner() {
		return owner;
	}


	public void setOwner(User owner) {
		this.owner = owner;
	}


	public Unit getUnit() {
		return unit;
	}


	public void setUnit(Unit unit) {
		this.unit = unit;
	}


	public List<OtherTag> getTags() {
		return tags;
	}


	public void setTags(List<OtherTag> tags) {
		this.tags = tags;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public ReservationType getReservationType() {
		return reservationType;
	}


	public void setReservationType(ReservationType reservationType) {
		this.reservationType = reservationType;
	}


	public ReservationStatus getReservationStatus() {
		return reservationStatus;
	}


	public void setReservationStatus(ReservationStatus reservationStatus) {
		this.reservationStatus = reservationStatus;
	}


	
    
    
	
	
	
	
}
