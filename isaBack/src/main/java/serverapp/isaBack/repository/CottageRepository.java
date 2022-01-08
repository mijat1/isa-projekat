package serverapp.isaBack.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import serverapp.isaBack.model.Cottage;


public interface CottageRepository extends JpaRepository<Cottage, UUID>{

}
