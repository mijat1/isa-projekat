package serverapp.isaBack.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import serverapp.isaBack.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, UUID> {
	
	Authority findByName ( String name );

}