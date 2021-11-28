package serverapp.isaBack.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import serverapp.isaBack.model.CottageOwner;

public interface CottageOwnerRepository extends JpaRepository<CottageOwner, UUID> {
	
}
