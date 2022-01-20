package serverapp.isaBack.repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import serverapp.isaBack.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, UUID>{
	
	@Query(value = "SELECT r FROM Reservation r WHERE r.reservationType = 'BOAT' AND r.reservationStatus = 'RESERVED' "
			+ " AND NOT (r.startDateTime >= ?2 OR r.endDateTime <= ?1) ")
	List<Reservation> findAllreservationsInDataRange(Date startDate, Date endDate);
	
	@Query(value = "SELECT r FROM Reservation r WHERE r.reservationType = 'BOAT' AND r.reservationStatus = 'RESERVED' "
			+ " AND r.unit.id = ?3  AND NOT (r.startDateTime >= ?2 OR r.endDateTime <= ?1) ")
	List<Reservation> findAllBusyReservationInDataRangeBoat(Date startDate, Date endDate,UUID boatId);
	
	@Query(value = "SELECT r FROM Reservation r WHERE r.reservationType = 'BOAT' AND r.reservationStatus = 'RESERVED'"
			+ " AND r.client.id = ?3 AND NOT (r.startDateTime >= ?2 OR r.endDateTime <= ?1) ")
	List<Reservation> findAllReservationsForClientInDataRange(Date startDate, Date endDate,UUID clientId);
}
