package serverapp.isaBack.repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import serverapp.isaBack.model.AvailablePeriod;


public interface AvailablePeriodRepository extends JpaRepository<AvailablePeriod, UUID>{
	@Query(value = "SELECT a from AvailablePeriod a WHERE  a.unit.unitType = 'BOAT'"
			+ "AND a.startDate <= ?2 AND a.endDate >= ?1 AND a.startDate <= ?1 AND a.endDate >= ?2 ")
	List<AvailablePeriod> findAvailablePeriodInDateRange(Date startDate,Date endDate, int startHour,  int endHour);
	
	@Query(value = "SELECT a from AvailablePeriod a WHERE  a.unit.unitType = 'BOAT' AND a.unit.id = ?3 "
			+ "AND a.startDate <= ?2 AND a.endDate >= ?1 AND a.startDate <= ?1 AND a.endDate >= ?2 ")
	List<AvailablePeriod> findAvailablePeriodInDateRangeForBoat(Date startDate,Date endDate,UUID boatId);
}
