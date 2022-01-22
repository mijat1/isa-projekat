package serverapp.isaBack.repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import serverapp.isaBack.model.AvailablePeriod;
import serverapp.isaBack.model.UnitType;


public interface AvailablePeriodRepository extends JpaRepository<AvailablePeriod, UUID>{
	@Query(value = "SELECT a from AvailablePeriod a WHERE  a.unit.unitType = ?3 "
			+ "AND a.startDate <= ?2 AND a.endDate >= ?1 AND a.startDate <= ?1 AND a.endDate >= ?2 ")
	List<AvailablePeriod> findAvailablePeriodInDateRange(Date startDate,Date endDate,UnitType unitType);
	
	@Query(value = "SELECT a from AvailablePeriod a WHERE  a.unit.unitType = 'BOAT' AND a.unit.id = ?3 "
			+ "AND a.startDate <= ?2 AND a.endDate >= ?1 AND a.startDate <= ?1 AND a.endDate >= ?2 ")
	List<AvailablePeriod> findAvailablePeriodInDateRangeForBoat(Date startDate,Date endDate,UUID boatId);
	
	@Query(value = "SELECT a from AvailablePeriod a WHERE  a.unit.unitType = 'COTTAGE' AND a.unit.id = ?3 "
			+ "AND a.startDate <= ?2 AND a.endDate >= ?1 AND a.startDate <= ?1 AND a.endDate >= ?2 ")
	List<AvailablePeriod> findAvailablePeriodInDateRangeForCottage(Date startDate,Date endDate,UUID boatId);
	
	
	@Query(value = "SELECT a from AvailablePeriod a WHERE  a.unit.unitType = 'COURSE' AND a.unit.id = ?3 "
			+ "AND a.startDate <= ?2 AND a.endDate >= ?1 AND a.startDate <= ?1 AND a.endDate >= ?2 ")
	List<AvailablePeriod> findAvailablePeriodInDateRangeForCourse(Date startDate,Date endDate,UUID courseId);
	
}
