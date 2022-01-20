package serverapp.isaBack.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import serverapp.isaBack.model.OwnerGrade;


public interface OwnerGradeRepository extends JpaRepository<OwnerGrade, UUID>{
	@Query(value = "SELECT AVG(u.grade) FROM OwnerGrade u WHERE u.owner.id = ?1")
	double getAvgGradeForUnit(UUID ownerId);
}
