package serverapp.isaBack.service.entities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import serverapp.isaBack.DTO.entities.CottageDTO;
import serverapp.isaBack.DTO.entities.CourseDTO;
import serverapp.isaBack.DTO.users.AuthorityDTO;
import serverapp.isaBack.mappers.entities.UnitMapper;
import serverapp.isaBack.model.Cottage;
import serverapp.isaBack.model.FishingCourse;
import serverapp.isaBack.repository.CottageRepository;
import serverapp.isaBack.repository.FishingCourseRepository;
import serverapp.isaBack.service.interfaces.ICottageService;
import serverapp.isaBack.service.interfaces.ICourseService;
import serverapp.isaBack.service.interfaces.IUnitGradeService;
import serverapp.isaBack.unspecifiedDTO.UnspecifiedDTO;


@Service
public class CourseService implements ICourseService{
	

	@Autowired
	private FishingCourseRepository courseRepository;
	
	@Autowired
	private IUnitGradeService unitGradeService;
	
	private UnitMapper gradeMapper= new UnitMapper();
	


	@Override
	public List<UnspecifiedDTO<CourseDTO>>getAllCourses() throws IOException{
		System.out.println("usao u kurseve");
		List<UnspecifiedDTO<CourseDTO>> retList = new ArrayList<UnspecifiedDTO<CourseDTO>>();
		List<FishingCourse> courses = courseRepository.findAll();
		for(FishingCourse course : courses) {
               double avgGrade = getAvgGradeForUnit(course.getId());
			
               retList.add(gradeMapper.MapCourseToCourseDTO(course,avgGrade));
		   }
		
		
		return retList;

		
		
	}
	
	
	public double getAvgGradeForUnit(UUID unitId){
		
		return unitGradeService.getAvgGradeForEmployee(unitId);
		
		}


	@Override
	public List<UnspecifiedDTO<AuthorityDTO>> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public UnspecifiedDTO<CourseDTO> findById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public UUID create(CourseDTO entityDTO) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void update(CourseDTO entityDTO, UUID id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean delete(UUID id) {
		// TODO Auto-generated method stub
		return false;
	}


}



