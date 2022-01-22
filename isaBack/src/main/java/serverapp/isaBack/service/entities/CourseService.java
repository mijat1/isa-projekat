package serverapp.isaBack.service.entities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
import serverapp.isaBack.model.Unit;
import serverapp.isaBack.repository.CottageRepository;
import serverapp.isaBack.repository.FishingCourseRepository;
import serverapp.isaBack.service.interfaces.ICottageService;
import serverapp.isaBack.service.interfaces.ICourseService;
import serverapp.isaBack.service.interfaces.IReservationService;
import serverapp.isaBack.service.interfaces.IUnitGradeService;
import serverapp.isaBack.unspecifiedDTO.UnspecifiedDTO;


@Service
public class CourseService implements ICourseService{
	

	@Autowired
	private FishingCourseRepository courseRepository;
	
	@Autowired
	private IUnitGradeService unitGradeService;
	
	private UnitMapper gradeMapper= new UnitMapper();
	
	@Autowired
	private IReservationService reservationService;

	@Override
	public List<UnspecifiedDTO<CourseDTO>>getAllCourses() throws IOException{
		System.out.println("usao u kurseve");
		List<UnspecifiedDTO<CourseDTO>> retList = new ArrayList<UnspecifiedDTO<CourseDTO>>();
		List<FishingCourse> courses = courseRepository.findAll();
		for(FishingCourse course : courses) {
               double avgGrade = getAvgGradeForUser(course.getId());
			
               retList.add(gradeMapper.MapCourseToCourseDTO(course,avgGrade));
		   }
		
		
		return retList;

		
		
	}
	
	
	@Override
	public List<UnspecifiedDTO<CourseDTO>> fidnAllFreeCoursesInDataRange(Date startDate, Date endDate,int people,String location, double gradeFrom,double gradeTo) throws IOException{
			
		System.out.println( "startno vreme " + startDate + "   " +  " Krajnje vremee " + endDate);
		
		List<UnspecifiedDTO<CourseDTO>> cottagesDTO= new ArrayList<UnspecifiedDTO<CourseDTO>>();  
		List<Unit>  cottages= new ArrayList<Unit>();
		cottages= reservationService.findAllFreeCourses(startDate,endDate);
		cottages=searchLocation(cottages, location);
		FishingCourse fc=null;
		for (Unit cottage : cottages) {
			 double avgGrade = getAvgGradeForUser(cottage.getId());
			 
			 fc=courseRepository.findById(cottage.getId()).get();
			 int capacity=fc.getCapacity();
			 if(capacity>=people)
				 cottagesDTO.add(gradeMapper.MapCourseToCourseDTO(fc,avgGrade));
		}
		
		cottagesDTO = searchGrade(cottagesDTO, gradeFrom, gradeTo);
	
		return cottagesDTO;
		
		
	}
	
	private List<UnspecifiedDTO<CourseDTO>> searchGrade(List<UnspecifiedDTO<CourseDTO>> cottages, double gradeFrom, double gradeTo){
		List<UnspecifiedDTO<CourseDTO>> retVal = new ArrayList<UnspecifiedDTO<CourseDTO>>();
		double grade;
		for(UnspecifiedDTO<CourseDTO> b:cottages) {
			grade=b.EntityDTO.getAvgGrade();
			if(!(gradeFrom == -1.0 || gradeTo == -1.0)) {
				if(grade >= gradeFrom && grade <= gradeTo) {
					retVal.add(b);
				}
			}else {
				if(gradeFrom == -1.0 & gradeTo != -1.0) {
					if(grade <= gradeTo)
						retVal.add(b);
				}else if (gradeTo == -1.0 & gradeFrom != -1.0){
					if(grade >= gradeFrom)
						retVal.add(b);
				}else {
						retVal.add(b);
				}
			}
			
		}
		return retVal;
	}
	
	private List<Unit> searchLocation(List<Unit> units, String location){
		
		if(location.equals(""))
			return units;
		List<Cottage> cottageLocation=courseRepository.findCottageByLocation(location.toLowerCase());
		List<Unit> retVal = new ArrayList<Unit>();
		for (Unit unit : units) {
			System.out.println("usao u pretragu lokacije vikendaje");
			for(Cottage cottage:cottageLocation) {
			if(unit.getId().equals(cottage.getId())) {
				retVal.add(unit);
			}
			}
		}
		
		return retVal;
	}
	
	
	
	public double getAvgGradeForUser(UUID unitId){
		
		return unitGradeService.getAvgGradeForUser(unitId);
		
		}


	
	@Override
	public List<UnspecifiedDTO<CourseDTO>> findAllCoursesWithFreePeriodSortByGradeDescending(Date startDate, Date endDate,int people,String location, double gradeFrom,double gradeTo) throws IOException{
		
		List<UnspecifiedDTO<CourseDTO>> boatDTOSortedByGradeDescending=fidnAllFreeCoursesInDataRange(startDate,endDate,people,location,gradeFrom,gradeTo);
				
		Collections.sort(boatDTOSortedByGradeDescending, (boat1, boat2) -> Double.compare(boat1.EntityDTO.getAvgGrade(), boat2.EntityDTO.getAvgGrade()));
		Collections.reverse(boatDTOSortedByGradeDescending);
		
		
		return boatDTOSortedByGradeDescending;
		
	}
	
	@Override
	public List<UnspecifiedDTO<CourseDTO>> findAllCoursesWithFreePeriodSortByGradeAscending(Date startDate, Date endDate,int people,String location, double gradeFrom,double gradeTo) throws IOException{
		
		List<UnspecifiedDTO<CourseDTO>> boatDTOSortedByGradeAscending=fidnAllFreeCoursesInDataRange(startDate,endDate,people,location,gradeFrom,gradeTo);
				
		Collections.sort(boatDTOSortedByGradeAscending, (boat1, boat2) -> Double.compare(boat1.EntityDTO.getAvgGrade(), boat2.EntityDTO.getAvgGrade()));
		
		
		
		return boatDTOSortedByGradeAscending;
		
	}
	
	@Override
	public List<UnspecifiedDTO<CourseDTO>> findAllCoursesWithFreePeriodSortByPriceDescending(Date startDate, Date endDate,int people,String location, double gradeFrom,double gradeTo) throws IOException{
		
		List<UnspecifiedDTO<CourseDTO>> boatDTOSortedByPriceDescending=fidnAllFreeCoursesInDataRange(startDate,endDate,people,location,gradeFrom,gradeTo);
				
		Collections.sort(boatDTOSortedByPriceDescending, (boat1, boat2) -> Double.compare(boat1.EntityDTO.getPrice(), boat2.EntityDTO.getPrice()));
		Collections.reverse(boatDTOSortedByPriceDescending);
		
		
		return boatDTOSortedByPriceDescending;
		
	}
	
	@Override
	public List<UnspecifiedDTO<CourseDTO>> findAllCoursesWithFreePeriodSortByPriceAscending(Date startDate, Date endDate,int people,String location, double gradeFrom,double gradeTo) throws IOException{
		
		List<UnspecifiedDTO<CourseDTO>> boatDTOSortedByPriceAscending=fidnAllFreeCoursesInDataRange(startDate,endDate,people,location,gradeFrom,gradeTo);
				
		Collections.sort(boatDTOSortedByPriceAscending, (boat1, boat2) -> Double.compare(boat1.EntityDTO.getPrice(), boat2.EntityDTO.getPrice()));
		
		
		
		return boatDTOSortedByPriceAscending;
		
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



