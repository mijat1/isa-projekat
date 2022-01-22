package serverapp.isaBack.service.interfaces;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import serverapp.isaBack.DTO.entities.CottageDTO;
import serverapp.isaBack.DTO.entities.CourseDTO;
import serverapp.isaBack.unspecifiedDTO.UnspecifiedDTO;

public interface ICourseService extends IService<CourseDTO, UnspecifiedDTO<CourseDTO>>{

	List<UnspecifiedDTO<CourseDTO>> getAllCourses() throws IOException;
	
	List<UnspecifiedDTO<CourseDTO>> fidnAllFreeCoursesInDataRange(Date startDate, Date endDate, int people,
			String location, double gradeFrom, double gradeTo) throws IOException;
	
	List<UnspecifiedDTO<CourseDTO>> findAllCoursesWithFreePeriodSortByGradeDescending(Date startDate, Date endDate,
			int people, String location, double gradeFrom, double gradeTo) throws IOException;

	List<UnspecifiedDTO<CourseDTO>> findAllCoursesWithFreePeriodSortByGradeAscending(Date startDate, Date endDate,
			int people, String location, double gradeFrom, double gradeTo) throws IOException;
	
	List<UnspecifiedDTO<CourseDTO>> findAllCoursesWithFreePeriodSortByPriceDescending(Date startDate, Date endDate,
			int people, String location, double gradeFrom, double gradeTo) throws IOException;

	List<UnspecifiedDTO<CourseDTO>> findAllCoursesWithFreePeriodSortByPriceAscending(Date startDate, Date endDate,
			int people, String location, double gradeFrom, double gradeTo) throws IOException;

}
