package serverapp.isaBack.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import serverapp.isaBack.model.UnitFeedback;

public interface UnitFeedbackRepository extends JpaRepository<UnitFeedback, UUID> {
	@Query(value = "SELECT AVG(u.grade) FROM UnitFeedback u WHERE u.unit.id = ?1")
	double getAvgGradeForUnit(UUID unitId);
	
	
}
