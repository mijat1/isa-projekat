package serverapp.isaBack.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import serverapp.isaBack.model.Cottage;
import serverapp.isaBack.model.FishingCourse;


public interface FishingCourseRepository extends JpaRepository<FishingCourse, UUID> {
	@Query(value = "SELECT c FROM FishingCourse c WHERE LOWER(c.address) LIKE %?1% ")
	List<Cottage> findCottageByLocation(String location);
}
