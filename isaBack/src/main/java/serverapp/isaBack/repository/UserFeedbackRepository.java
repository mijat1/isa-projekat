package serverapp.isaBack.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import serverapp.isaBack.model.UnitFeedback;
import serverapp.isaBack.model.UserFeedBack;

public interface UserFeedbackRepository extends JpaRepository<UserFeedBack, UUID> {
	@Query(value = "SELECT AVG(u.grade) FROM UserFeedBack u WHERE u.user.id = ?1")
	double getAvgGradeForUser(UUID userId);
	
	
}
