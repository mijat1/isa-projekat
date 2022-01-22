package serverapp.isaBack.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import serverapp.isaBack.model.Authority;
import serverapp.isaBack.model.DeleteRequest;

public interface DeletetRequestRepository extends JpaRepository<DeleteRequest, UUID>{

}
