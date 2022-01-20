package serverapp.isaBack.DTO.reservation;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class NewReservationDTO {
	private long startDateTime;
	private int days;
	private UUID unitId;
	private List<UUID> listServices;
	
	
		
	public NewReservationDTO() {
		super();
	}

	public NewReservationDTO(long startDateTime, int days, UUID unitId, List<UUID> listServices) {
		super();
		this.startDateTime = startDateTime;
		this.days = days;
		this.unitId = unitId;
		this.listServices = listServices;
	}
	
	public List<UUID> getListServices() {
		return listServices;
	}
	public void setListServices(List<UUID> listServices) {
		this.listServices = listServices;
	}
	public long getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(long startDateTime) {
		this.startDateTime = startDateTime;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}

	public UUID getUnitId() {
		return unitId;
	}
	public void setUnitId(UUID unitId) {
		this.unitId = unitId;
	}

}
