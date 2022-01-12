package serverapp.isaBack.controller.entities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
