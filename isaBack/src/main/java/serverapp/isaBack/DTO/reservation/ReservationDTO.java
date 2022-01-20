package serverapp.isaBack.DTO.reservation;

import java.util.Date;

import serverapp.isaBack.DTO.entities.BoatDTO;
import serverapp.isaBack.DTO.entities.UnitDTO;
import serverapp.isaBack.DTO.users.UserNameDTO;
import serverapp.isaBack.unspecifiedDTO.UnspecifiedDTO;



public class ReservationDTO {	
	
	    private UnspecifiedDTO<UnitDTO> unit;
	    
	    private UnspecifiedDTO<UserNameDTO> user;
		
		private Date startDateTime;
		    
	    private Date endDateTime;
		    
	    private double price;

		public ReservationDTO() {
			super();
		}

		public ReservationDTO( UnspecifiedDTO<UnitDTO> unit,UnspecifiedDTO<UserNameDTO> user, Date startDateTime, Date endDateTime,
				double price) {
			super();
			this.unit=unit;
			this.user=user;
			this.startDateTime = startDateTime;
			this.endDateTime = endDateTime;
			this.price = price;
		}

	

		public UnspecifiedDTO<UserNameDTO> getUser() {
			return user;
		}

		public void setUser(UnspecifiedDTO<UserNameDTO> user) {
			this.user = user;
		}

		public UnspecifiedDTO<UnitDTO> getUnit() {
			return unit;
		}

		public void setUnit(UnspecifiedDTO<UnitDTO> unit) {
			this.unit = unit;
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
	    
}
