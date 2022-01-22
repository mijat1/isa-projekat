package serverapp.isaBack.controller.entities;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import serverapp.isaBack.DTO.entities.CottageDTO;
import serverapp.isaBack.DTO.entities.CourseDTO;
import serverapp.isaBack.service.interfaces.ICottageService;
import serverapp.isaBack.service.interfaces.ICourseService;
import serverapp.isaBack.unspecifiedDTO.UnspecifiedDTO;

@RestController
@RequestMapping(value = "/course", produces = MediaType.APPLICATION_JSON_VALUE)
public class CourseController {
	
	@Autowired
	private ICourseService courseService;
	
	@CrossOrigin
	@GetMapping("/allCourses") 
	public ResponseEntity<List<UnspecifiedDTO<CourseDTO>>> getAllCourses() {
		
		try {
		return new ResponseEntity<>(courseService.getAllCourses() ,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@CrossOrigin
	@GetMapping("/allCoursesWithFreePeriod") 
	public ResponseEntity<List<UnspecifiedDTO<CourseDTO>>> getAllCoursesWithFreePeriod(@RequestParam long DateTime, @RequestParam int days,@RequestParam int people, @RequestParam String location,@RequestParam double gradeFrom,@RequestParam double gradeTo) {
		Date startDate = new Date(DateTime);
		//long startTime = startDate.getTime();
		Date endDate= new Date(DateTime+24*60*60*1000*days);
		//endDate.setHours(15);
		System.out.println("Ima li casova");
		System.out.println("start " + startDate +" end " +endDate + " dani " + days );
		System.out.println("ocenaaa "+ gradeFrom);
		try {
		return new ResponseEntity<>(courseService.fidnAllFreeCoursesInDataRange(startDate,endDate,people,location,gradeFrom,gradeTo) ,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@GetMapping("/getAllFreeCoursesForSelectedDate/SortByGradeDescending") 
	public ResponseEntity<List<UnspecifiedDTO<CourseDTO>>> getAllFreeCoursesForSelectedDateSortByGradeDescending(@RequestParam long DateTime, @RequestParam int days,@RequestParam int people, @RequestParam String location,@RequestParam double gradeFrom,@RequestParam double gradeTo) {
		Date startDate = new Date(DateTime);
		//long startTime = startDate.getTime();
		Date endDate= new Date(DateTime+24*60*60*1000*days);
		//endDate.setHours(15);
		System.out.println("start " + startDate +" end " +endDate + " dani " + days );
		System.out.println("ocenaaa "+ gradeFrom);
		try {
		return new ResponseEntity<>(courseService.findAllCoursesWithFreePeriodSortByGradeDescending(startDate,endDate,people,location,gradeFrom,gradeTo) ,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@GetMapping("/getAllFreeCoursesForSelectedDate/SortByGradeAscending") 
	public ResponseEntity<List<UnspecifiedDTO<CourseDTO>>> getAllFreeCoursesForSelectedDateSortByGradeAscending(@RequestParam long DateTime, @RequestParam int days,@RequestParam int people, @RequestParam String location,@RequestParam double gradeFrom,@RequestParam double gradeTo) {
		Date startDate = new Date(DateTime);
		//long startTime = startDate.getTime();
		Date endDate= new Date(DateTime+24*60*60*1000*days);
		//endDate.setHours(15);
		System.out.println("start " + startDate +" end " +endDate + " dani " + days );
		System.out.println("ocenaaa "+ gradeFrom);
		try {
		return new ResponseEntity<>(courseService.findAllCoursesWithFreePeriodSortByGradeAscending(startDate,endDate,people,location,gradeFrom,gradeTo) ,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@CrossOrigin
	@GetMapping("/getAllFreeCoursesForSelectedDate/SortByPriceDescending") 
	public ResponseEntity<List<UnspecifiedDTO<CourseDTO>>> getAllFreeCoursesForSelectedDateSortByPriceDescending(@RequestParam long DateTime, @RequestParam int days,@RequestParam int people, @RequestParam String location,@RequestParam double gradeFrom,@RequestParam double gradeTo) {
		Date startDate = new Date(DateTime);
		//long startTime = startDate.getTime();
		Date endDate= new Date(DateTime+24*60*60*1000*days);
		//endDate.setHours(15);
		System.out.println("start " + startDate +" end " +endDate + " dani " + days );
		System.out.println("ocenaaa "+ gradeFrom);
		try {
		return new ResponseEntity<>(courseService.findAllCoursesWithFreePeriodSortByPriceDescending(startDate,endDate,people,location,gradeFrom,gradeTo) ,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@GetMapping("/getAllFreeCoursesForSelectedDate/SortByPriceAscending") 
	public ResponseEntity<List<UnspecifiedDTO<CourseDTO>>> getAllFreeCoursesForSelectedDateSortByPriceAscending(@RequestParam long DateTime, @RequestParam int days,@RequestParam int people, @RequestParam String location,@RequestParam double gradeFrom,@RequestParam double gradeTo) {
		Date startDate = new Date(DateTime);
		//long startTime = startDate.getTime();
		Date endDate= new Date(DateTime+24*60*60*1000*days);
		//endDate.setHours(15);
		System.out.println("start " + startDate +" end " +endDate + " dani " + days );
		System.out.println("ocenaaa "+ gradeFrom);
		try {
		return new ResponseEntity<>(courseService.findAllCoursesWithFreePeriodSortByPriceAscending(startDate,endDate,people,location,gradeFrom,gradeTo) ,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
