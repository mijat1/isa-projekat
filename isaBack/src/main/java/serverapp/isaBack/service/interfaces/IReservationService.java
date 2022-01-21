package serverapp.isaBack.service.interfaces;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import serverapp.isaBack.DTO.reservation.NewReservationDTO;
import serverapp.isaBack.DTO.reservation.ReservationDTO;
import serverapp.isaBack.model.ReservationType;
import serverapp.isaBack.model.Unit;
import serverapp.isaBack.unspecifiedDTO.UnspecifiedDTO;

public interface IReservationService {

	List<Unit> findAllFreeBoats(Date startDate, Date endDate);

	void makeBoatReservation(NewReservationDTO reservationDTO);

	List<UnspecifiedDTO<ReservationDTO>> findAllFutureClientBoatReservation(ReservationType reservationType) throws IOException;

	List<UnspecifiedDTO<ReservationDTO>> findAllHistoryClientReservation(ReservationType reservationType)
			throws IOException;
	
	List<UnspecifiedDTO<ReservationDTO>> findAllHistoryClientsReservationsSortByPriceAscending(
			ReservationType resrvationType) throws Exception;
	
	List<UnspecifiedDTO<ReservationDTO>> findAllHistoryClientsReservationsSortByPriceDescending(
			ReservationType resrvationType) throws Exception;

	List<UnspecifiedDTO<ReservationDTO>> findAllHistoryClientsReservationsSortByDateAscending(
			ReservationType resrvationType) throws Exception;
	
	List<UnspecifiedDTO<ReservationDTO>> findAllHistoryClientsReservationsSortByDateDescending(
			ReservationType resrvationType) throws Exception;
	
	List<UnspecifiedDTO<ReservationDTO>> findAllHistoryClientsReservationsSortByDurationAscending(
			ReservationType resrvationType) throws Exception;

	List<UnspecifiedDTO<ReservationDTO>> findAllHistoryClientsReservationsSortByDurationDescending(
			ReservationType resrvationType) throws Exception;

	void cancelReservation(UUID reservationId);

	List<UnspecifiedDTO<ReservationDTO>> findAllActionReservationClient(ReservationType reservationType)
			throws IOException;

	void fastReservation(UUID reservationId);

	List<Unit> findAllFreeCottages(Date startDate, Date endDate);

	


	
	


}
