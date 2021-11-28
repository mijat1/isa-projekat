package serverapp.isaBack.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import serverapp.isaBack.model.SystemAdmin;

public interface SystemAdminRepository extends JpaRepository<SystemAdmin, UUID> {
	
}
