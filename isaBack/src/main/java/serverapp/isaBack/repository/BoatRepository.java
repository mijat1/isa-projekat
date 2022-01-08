package serverapp.isaBack.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import serverapp.isaBack.model.Boat;

public interface BoatRepository extends JpaRepository<Boat, UUID> {
	
}
