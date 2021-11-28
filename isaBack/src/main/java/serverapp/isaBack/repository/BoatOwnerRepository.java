package serverapp.isaBack.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import serverapp.isaBack.model.BoatOwner;

public interface BoatOwnerRepository extends JpaRepository<BoatOwner, UUID> {
	
}
