package serverapp.isaBack.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

@Entity
public class Boat extends Unit{
	
	private double power;
	
	private double length;
	
	private double maxSpeed;
	
	private int capacity;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "navi_equipment",
            joinColumns = @JoinColumn(name = "boat_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ne_id", referencedColumnName = "id"))
    private List<NavigationEquipment> naviEqipment;

	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "fish_equipment",
            joinColumns = @JoinColumn(name = "unit_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "fe_id", referencedColumnName = "id"))
    private List<FishingEquipment> fEqupment;
	
	
	

	public Boat(String name, String address, String description, String rules, Album album, double price,
			Cancellation cancellation, int percentOfCancel, double power, double length, double maxSpeed, int capacity,
			List<NavigationEquipment> naviEqipment, List<FishingEquipment> fEqupment) {
		super(UUID.randomUUID(),name, address, description, rules, album, price, cancellation, percentOfCancel);
		this.power = power;
		this.length = length;
		this.maxSpeed = maxSpeed;
		this.capacity = capacity;
		this.naviEqipment = naviEqipment;
		this.fEqupment = fEqupment;
	}


	public Boat() {
		super();
		// TODO Auto-generated constructor stub
	}


	public double getPower() {
		return power;
	}


	public void setPower(double power) {
		this.power = power;
	}


	public double getLength() {
		return length;
	}


	public void setLength(double length) {
		this.length = length;
	}


	public double getMaxSpeed() {
		return maxSpeed;
	}


	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}


	public List<NavigationEquipment> getNaviEqipment() {
		return naviEqipment;
	}


	public void setNaviEqipment(List<NavigationEquipment> naviEqipment) {
		this.naviEqipment = naviEqipment;
	}


	public int getCapacity() {
		return capacity;
	}


	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	
	
}
