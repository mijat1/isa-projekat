package serverapp.isaBack.service.interfaces;

import java.util.Date;
import java.util.List;

import serverapp.isaBack.DTO.reservation.NewReservationDTO;
import serverapp.isaBack.model.Unit;

public interface IReservationService {

	List<Unit> findAllFreeBoats(Date startDate, Date endDate);

	void makeBoatReservation(NewReservationDTO reservationDTO);

}
