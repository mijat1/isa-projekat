package serverapp.isaBack.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import serverapp.isaBack.model.Client;

public interface ClientRepository extends JpaRepository<Client, UUID> {
	
}
