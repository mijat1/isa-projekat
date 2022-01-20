package serverapp.isaBack.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import serverapp.isaBack.model.Boat;

public interface BoatRepository extends JpaRepository<Boat, UUID> {
	@Query(value = "SELECT b FROM Boat b WHERE b.id = ?1")
	Boat findBoatById(UUID id);
	
	@Query(value = "SELECT b FROM Boat b WHERE LOWER(b.address) LIKE %?1% ")
	List<Boat> findBoatByLocation(String location);
}
