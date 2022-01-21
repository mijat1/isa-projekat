package serverapp.isaBack.repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import serverapp.isaBack.model.Reservation;
import serverapp.isaBack.model.ReservationStatus;
import serverapp.isaBack.model.ReservationType;

public interface ReservationRepository extends JpaRepository<Reservation, UUID>{
	
	@Query(value = "SELECT r FROM Reservation r WHERE r.reservationType = ?3 AND r.reservationStatus = 'RESERVED' "
			+ " AND NOT (r.startDateTime >= ?2 OR r.endDateTime <= ?1) ")
	List<Reservation> findAllreservationsInDataRange(Date startDate, Date endDate,ReservationType reservationType);
	
	@Query(value = "SELECT r FROM Reservation r WHERE r.reservationType = 'BOAT' AND r.reservationStatus = 'RESERVED' "
			+ " AND r.unit.id = ?3  AND NOT (r.startDateTime >= ?2 OR r.endDateTime <= ?1) ")
	List<Reservation> findAllBusyReservationInDataRangeBoat(Date startDate, Date endDate,UUID boatId);
	
	@Query(value = "SELECT r FROM Reservation r WHERE r.reservationType = 'COTTAGE' AND r.reservationStatus = 'RESERVED' "
			+ " AND r.unit.id = ?3  AND NOT (r.startDateTime >= ?2 OR r.endDateTime <= ?1) ")
	List<Reservation> findAllBusyCtReservationInDataRangeBoat(Date startDate, Date endDate,UUID boatId);
	
	@Query(value = "SELECT r FROM Reservation r WHERE r.reservationType = 'BOAT' AND r.reservationStatus = 'RESERVED'"
			+ " AND r.client.id = ?3 AND NOT (r.startDateTime >= ?2 OR r.endDateTime <= ?1) ")
	List<Reservation> findAllReservationsForClientInDataRange(Date startDate, Date endDate,UUID clientId);
	
	@Query(value = "SELECT r FROM Reservation r WHERE r.reservationType = 'COTTAGE' AND r.reservationStatus = 'RESERVED'"
			+ " AND r.client.id = ?3 AND NOT (r.startDateTime >= ?2 OR r.endDateTime <= ?1) ")
	List<Reservation> findAllCtReservationsForClientInDataRange(Date startDate, Date endDate,UUID clientId);


	@Query(value = "SELECT r FROM Reservation r WHERE r.client.id = ?1 AND r.startDateTime > CURRENT_TIMESTAMP"
			+ "  AND r.reservationType = ?2 AND r.reservationStatus = 'RESERVED' ")
	List<Reservation> findAllFutureClientsReservation(UUID userId, ReservationType reservationType);  
	

	@Query(value = "SELECT r FROM Reservation r WHERE (r.client.id = ?1 AND r.reservationStatus = 'FINISHED'"   
			+ "  AND r.reservationType = ?2) OR ( r.client.id = ?1 AND r.reservationType = ?2 AND r.startDateTime < CURRENT_TIMESTAMP) ")
	List<Reservation> findAllHistoryClientsReservation(UUID userId, ReservationType reservationType);

	@Query(value = "SELECT r FROM Reservation r WHERE r.reservationStatus = 'ACTION' AND r.reservationType = ?1 ")
	List<Reservation> findAllActionReservation(ReservationType reservationType);
}
