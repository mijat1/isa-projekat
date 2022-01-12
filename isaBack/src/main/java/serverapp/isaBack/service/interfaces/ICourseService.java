package serverapp.isaBack.service.interfaces;

import java.io.IOException;
import java.util.List;

import serverapp.isaBack.DTO.entities.CourseDTO;
import serverapp.isaBack.unspecifiedDTO.UnspecifiedDTO;

public interface ICourseService extends IService<CourseDTO, UnspecifiedDTO<CourseDTO>>{

	List<UnspecifiedDTO<CourseDTO>> getAllCourses() throws IOException;

}
