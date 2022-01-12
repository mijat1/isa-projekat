package serverapp.isaBack.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import serverapp.isaBack.model.FishingCourse;


public interface FishingCourseRepository extends JpaRepository<FishingCourse, UUID> {

}
