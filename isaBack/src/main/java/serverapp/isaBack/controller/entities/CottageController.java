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

import serverapp.isaBack.DTO.entities.BoatDTO;
import serverapp.isaBack.DTO.entities.CottageDTO;
import serverapp.isaBack.service.interfaces.ICottageService;
import serverapp.isaBack.unspecifiedDTO.UnspecifiedDTO;

@RestController
@RequestMapping(value = "/cottage", produces = MediaType.APPLICATION_JSON_VALUE)
public class CottageController {

	@Autowired
	private ICottageService cottageService;
	
	@CrossOrigin
	@GetMapping("/allCottages") 
	public ResponseEntity<List<UnspecifiedDTO<CottageDTO>>> getAllCottages() {
		
		try {
		return new ResponseEntity<>(cottageService.getAllCottages() ,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@GetMapping("/allCottagesWithFreePeriod") 
	public ResponseEntity<List<UnspecifiedDTO<CottageDTO>>> getAllBoatsWithFreePeriod(@RequestParam long DateTime, @RequestParam int days,@RequestParam int people, @RequestParam String location,@RequestParam double gradeFrom,@RequestParam double gradeTo) {
		Date startDate = new Date(DateTime);
		//long startTime = startDate.getTime();
		Date endDate= new Date(DateTime+24*60*60*1000*days);
		//endDate.setHours(15);
		System.out.println("start " + startDate +" end " +endDate + " dani " + days );
		System.out.println("ocenaaa "+ gradeFrom);
		try {
		return new ResponseEntity<>(cottageService.fidnAllFreeCottagesInDataRange(startDate,endDate,people,location,gradeFrom,gradeTo) ,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@GetMapping("/getAllFreeCottagesForSelectedDate/SortByGradeDescending") 
	public ResponseEntity<List<UnspecifiedDTO<CottageDTO>>> getAllFreeCottagesForSelectedDateSortByGradeDescending(@RequestParam long DateTime, @RequestParam int days,@RequestParam int people, @RequestParam String location,@RequestParam double gradeFrom,@RequestParam double gradeTo) {
		Date startDate = new Date(DateTime);
		//long startTime = startDate.getTime();
		Date endDate= new Date(DateTime+24*60*60*1000*days);
		//endDate.setHours(15);
		System.out.println("start " + startDate +" end " +endDate + " dani " + days );
		System.out.println("ocenaaa "+ gradeFrom);
		try {
		return new ResponseEntity<>(cottageService.findAllCottagesWithFreePeriodSortByGradeDescending(startDate,endDate,people,location,gradeFrom,gradeTo) ,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@GetMapping("/getAllFreeCottagesForSelectedDate/SortByGradeAscending") 
	public ResponseEntity<List<UnspecifiedDTO<CottageDTO>>> getAllFreeCottagesForSelectedDateSortByGradeAscending(@RequestParam long DateTime, @RequestParam int days,@RequestParam int people, @RequestParam String location,@RequestParam double gradeFrom,@RequestParam double gradeTo) {
		Date startDate = new Date(DateTime);
		//long startTime = startDate.getTime();
		Date endDate= new Date(DateTime+24*60*60*1000*days);
		//endDate.setHours(15);
		System.out.println("start " + startDate +" end " +endDate + " dani " + days );
		System.out.println("ocenaaa "+ gradeFrom);
		try {
		return new ResponseEntity<>(cottageService.findAllCottagesWithFreePeriodSortByGradeAscending(startDate,endDate,people,location,gradeFrom,gradeTo) ,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@CrossOrigin
	@GetMapping("/getAllFreeCottagesForSelectedDate/SortByPriceDescending") 
	public ResponseEntity<List<UnspecifiedDTO<CottageDTO>>> getAllFreeCottagesForSelectedDateSortByPriceDescending(@RequestParam long DateTime, @RequestParam int days,@RequestParam int people, @RequestParam String location,@RequestParam double gradeFrom,@RequestParam double gradeTo) {
		Date startDate = new Date(DateTime);
		//long startTime = startDate.getTime();
		Date endDate= new Date(DateTime+24*60*60*1000*days);
		//endDate.setHours(15);
		System.out.println("start " + startDate +" end " +endDate + " dani " + days );
		System.out.println("ocenaaa "+ gradeFrom);
		try {
		return new ResponseEntity<>(cottageService.findAllCottagesWithFreePeriodSortByPriceDescending(startDate,endDate,people,location,gradeFrom,gradeTo) ,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@GetMapping("/getAllFreeCottagesForSelectedDate/SortByPriceAscending") 
	public ResponseEntity<List<UnspecifiedDTO<CottageDTO>>> getAllFreeCottagesForSelectedDateSortByPriceAscending(@RequestParam long DateTime, @RequestParam int days,@RequestParam int people, @RequestParam String location,@RequestParam double gradeFrom,@RequestParam double gradeTo) {
		Date startDate = new Date(DateTime);
		//long startTime = startDate.getTime();
		Date endDate= new Date(DateTime+24*60*60*1000*days);
		//endDate.setHours(15);
		System.out.println("start " + startDate +" end " +endDate + " dani " + days );
		System.out.println("ocenaaa "+ gradeFrom);
		try {
		return new ResponseEntity<>(cottageService.findAllCottagesWithFreePeriodSortByPriceAscending(startDate,endDate,people,location,gradeFrom,gradeTo) ,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
