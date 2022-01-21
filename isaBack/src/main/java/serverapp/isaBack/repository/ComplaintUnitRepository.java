package serverapp.isaBack.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import serverapp.isaBack.model.ComplaintUnit;


public interface ComplaintUnitRepository extends JpaRepository<ComplaintUnit, UUID> {

}
