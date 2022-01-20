package serverapp.isaBack.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import serverapp.isaBack.model.Unit;


public interface UnitRepository  extends JpaRepository<Unit, UUID>{
	
}
