package serverapp.isaBack.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Cottage extends Unit{
	private int numberOfRooms;
	
	private int numberBedsPerRoom;
	
	@ManyToOne
	private CottageOwner owner;
	

	public Cottage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cottage(String name, String address, String description, String rules, Album album, double price,
			Cancellation cancellation, int percentOfCancel, int numberOfRooms, int numberBedsPerRoom, CottageOwner owner) {
		super(UUID.randomUUID(),name, address, description, rules, album, price, cancellation, percentOfCancel);
		this.numberOfRooms = numberOfRooms;
		this.numberBedsPerRoom = numberBedsPerRoom;
		this.owner=owner;
	}

	public int getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	public int getNumberBedsPerRoom() {
		return numberBedsPerRoom;
	}

	public void setNumberBedsPerRoom(int numberBedsPerRoom) {
		this.numberBedsPerRoom = numberBedsPerRoom;
	}

	public CottageOwner getOwner() {
		return owner;
	}

	public void setOwner(CottageOwner owner) {
		this.owner = owner;
	}
	
	
	

}
