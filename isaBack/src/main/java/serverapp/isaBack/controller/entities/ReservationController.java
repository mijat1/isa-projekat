package serverapp.isaBack.controller.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import serverapp.isaBack.DTO.entities.CourseDTO;
import serverapp.isaBack.DTO.reservation.NewReservationDTO;
import serverapp.isaBack.model.ReservationType;
import serverapp.isaBack.service.interfaces.IBoatService;
import serverapp.isaBack.service.interfaces.ICourseService;
import serverapp.isaBack.service.interfaces.IReservationService;
import serverapp.isaBack.unspecifiedDTO.UnspecifiedDTO;

@RestController
@RequestMapping(value = "/reservation", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationController {
	
	@Autowired
	private IReservationService reservationService;
	
	
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@CrossOrigin
	@PostMapping("/createBoatReservaton")
	public ResponseEntity<?> createBoatReservation(@RequestBody NewReservationDTO newReservationDTO) {
			Date neki= new Date(newReservationDTO.getStartDateTime());
			System.out.println(newReservationDTO.getUnitId() + "    "+  neki);
		try {
			reservationService.makeBoatReservation(newReservationDTO);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	

}
