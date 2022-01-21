package serverapp.isaBack.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import serverapp.isaBack.model.ComplaintUser;

public interface ComplaintUserRepository extends JpaRepository<ComplaintUser, UUID>  {

}
