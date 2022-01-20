package serverapp.isaBack.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AvailablePeriod {

		@Id
	    @Column(name = "id")
		private UUID id;
		
		@ManyToOne
		private Unit unit;
		
		@ManyToOne
		private User owner;
			
		private Date startDate;
		  
		private Date endDate;
	    
	    private int startTime;
		
	    private int endTime;
	    
	    public AvailablePeriod() {}
		
		public AvailablePeriod(Unit unit,User owner, Date startDate, Date endDate, int startTime, int endTime) {
			this(UUID.randomUUID(), owner,startDate,endDate,startTime,endTime,unit);
		}
		
		public AvailablePeriod(UUID id, User owner, Date startDate, Date endDate, int startTime, int endTime,Unit unit) {
			super();
			this.id = id;
			this.owner= owner;
			this.startDate= startDate;
			this.endDate= endDate;
			this.startTime=startTime;
			this.endTime=endTime;
			this.unit = unit;

		}

		public UUID getId() {
			return id;
		}

		public void setId(UUID id) {
			this.id = id;
		}


		public User getOwner() {
			return owner;
		}

		public void setOwner(User owner) {
			this.owner = owner;
		}

		public Date getStartDate() {
			return startDate;
		}

		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}

		public Date getEndDate() {
			return endDate;
		}

		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}

		public int getStartTime() {
			return startTime;
		}

		public void setStartTime(int startTime) {
			this.startTime = startTime;
		}

		public int getEndTime() {
			return endTime;
		}

		public void setEndTime(int endTime) {
			this.endTime = endTime;
		}

		public Unit getUnit() {
			return unit;
		}

		public void setUnit(Unit unit) {
			this.unit = unit;
		}

		
		
		

	}


