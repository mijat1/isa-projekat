package serverapp.isaBack.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import serverapp.isaBack.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail ( String email );
}
