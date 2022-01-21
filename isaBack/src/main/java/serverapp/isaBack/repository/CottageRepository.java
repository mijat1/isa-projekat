package serverapp.isaBack.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import serverapp.isaBack.model.Boat;
import serverapp.isaBack.model.Cottage;


public interface CottageRepository extends JpaRepository<Cottage, UUID>{
	@Query(value = "SELECT c FROM Cottage c WHERE LOWER(c.address) LIKE %?1% ")
	List<Cottage> findCottageByLocation(String location);
}
