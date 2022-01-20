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
import serverapp.isaBack.service.interfaces.IBoatService;
import serverapp.isaBack.unspecifiedDTO.UnspecifiedDTO;

@RestController
@RequestMapping(value = "/boat", produces = MediaType.APPLICATION_JSON_VALUE)
public class BoatController {

	@Autowired
	private IBoatService boatService;

	@CrossOrigin
	@GetMapping("/allBoats") 
	public ResponseEntity<List<UnspecifiedDTO<BoatDTO>>> getAllBoats() {
	
		try {
		return new ResponseEntity<>(boatService.getAllBoats() ,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	@CrossOrigin
	@GetMapping("/allBoatsWithFreePeriod") 
	public ResponseEntity<List<UnspecifiedDTO<BoatDTO>>> getAllBoatsWithFreePeriod(@RequestParam long DateTime, @RequestParam int days,@RequestParam int people, @RequestParam String location,@RequestParam double gradeFrom,@RequestParam double gradeTo) {
		Date startDate = new Date(DateTime);
		//long startTime = startDate.getTime();
		Date endDate= new Date(DateTime+24*60*60*1000*days);
		//endDate.setHours(15);
		System.out.println("start " + startDate +" end " +endDate + " dani " + days );
		System.out.println("ocenaaa "+ gradeFrom);
		try {
		return new ResponseEntity<>(boatService.fidnAllFreeBoatsInDataRange(startDate,endDate,people,location,gradeFrom,gradeTo) ,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@GetMapping("/getAllFreeBoatsForSelectedDate/SortByGradeDescending") 
	public ResponseEntity<List<UnspecifiedDTO<BoatDTO>>> getAllFreeBoatsForSelectedDateSortByGradeDescending(@RequestParam long DateTime, @RequestParam int days,@RequestParam int people, @RequestParam String location,@RequestParam double gradeFrom,@RequestParam double gradeTo) {
		Date startDate = new Date(DateTime);
		//long startTime = startDate.getTime();
		Date endDate= new Date(DateTime+24*60*60*1000*days);
		//endDate.setHours(15);
		System.out.println("start " + startDate +" end " +endDate + " dani " + days );
		System.out.println("ocenaaa "+ gradeFrom);
		try {
		return new ResponseEntity<>(boatService.findAllBoatsWithFreePeriodSortByGradeDescending(startDate,endDate,people,location,gradeFrom,gradeTo) ,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@GetMapping("/getAllFreeBoatsForSelectedDate/SortByGradeAscending") 
	public ResponseEntity<List<UnspecifiedDTO<BoatDTO>>> getAllFreeBoatsForSelectedDateSortByGradeAscending(@RequestParam long DateTime, @RequestParam int days,@RequestParam int people, @RequestParam String location,@RequestParam double gradeFrom,@RequestParam double gradeTo) {
		Date startDate = new Date(DateTime);
		//long startTime = startDate.getTime();
		Date endDate= new Date(DateTime+24*60*60*1000*days);
		//endDate.setHours(15);
		System.out.println("start " + startDate +" end " +endDate + " dani " + days );
		System.out.println("ocenaaa "+ gradeFrom);
		try {
		return new ResponseEntity<>(boatService.findAllBoatsWithFreePeriodSortByGradeAscending(startDate,endDate,people,location,gradeFrom,gradeTo) ,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@CrossOrigin
	@GetMapping("/getAllFreeBoatsForSelectedDate/SortByPriceDescending") 
	public ResponseEntity<List<UnspecifiedDTO<BoatDTO>>> getAllFreeBoatsForSelectedDateSortByPriceDescending(@RequestParam long DateTime, @RequestParam int days,@RequestParam int people, @RequestParam String location,@RequestParam double gradeFrom,@RequestParam double gradeTo) {
		Date startDate = new Date(DateTime);
		//long startTime = startDate.getTime();
		Date endDate= new Date(DateTime+24*60*60*1000*days);
		//endDate.setHours(15);
		System.out.println("start " + startDate +" end " +endDate + " dani " + days );
		System.out.println("ocenaaa "+ gradeFrom);
		try {
		return new ResponseEntity<>(boatService.findAllBoatsWithFreePeriodSortByGradeDescending(startDate,endDate,people,location,gradeFrom,gradeTo) ,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@GetMapping("/getAllFreeBoatsForSelectedDate/SortByPriceAscending") 
	public ResponseEntity<List<UnspecifiedDTO<BoatDTO>>> getAllFreeBoatsForSelectedDateSortByPriceAscending(@RequestParam long DateTime, @RequestParam int days,@RequestParam int people, @RequestParam String location,@RequestParam double gradeFrom,@RequestParam double gradeTo) {
		Date startDate = new Date(DateTime);
		//long startTime = startDate.getTime();
		Date endDate= new Date(DateTime+24*60*60*1000*days);
		//endDate.setHours(15);
		System.out.println("start " + startDate +" end " +endDate + " dani " + days );
		System.out.println("ocenaaa "+ gradeFrom);
		try {
		return new ResponseEntity<>(boatService.findAllBoatsWithFreePeriodSortByGradeAscending(startDate,endDate,people,location,gradeFrom,gradeTo) ,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
