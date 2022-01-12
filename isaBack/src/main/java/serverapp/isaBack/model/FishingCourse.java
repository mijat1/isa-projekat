package serverapp.isaBack.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class FishingCourse extends Unit{
	
	@Column(name = "biography", nullable = false)
	private String biography;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "fish_equipment",
            joinColumns = @JoinColumn(name = "unit_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "fe_id", referencedColumnName = "id"))
    private List<FishingEquipment> fEqupment;
	
	@ManyToOne
	private FishingInstructor instructor;

	
	
	public FishingCourse() {
		super();
		// TODO Auto-generated constructor stub
	}


	public FishingCourse(String biography, List<FishingEquipment> fEqupment, FishingInstructor instructor) {
		super();
		this.biography = biography;
		this.fEqupment = fEqupment;
		this.instructor = instructor;
	}


	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public List<FishingEquipment> getfEqupment() {
		return fEqupment;
	}

	public void setfEqupment(List<FishingEquipment> fEqupment) {
		this.fEqupment = fEqupment;
	}

	public FishingInstructor getInstructor() {
		return instructor;
	}

	public void setInstructor(FishingInstructor instructor) {
		this.instructor = instructor;
	}
	
	
	
}
