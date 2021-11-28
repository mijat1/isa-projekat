package serverapp.isaBack.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class FishingCourse extends Unit{
	
	@Column(name = "biography", nullable = false)
	private String biography;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "fish_equipment",
            joinColumns = @JoinColumn(name = "unit_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "fe_id", referencedColumnName = "id"))
    private List<FishingEquipment> fEqupment;
	
}
