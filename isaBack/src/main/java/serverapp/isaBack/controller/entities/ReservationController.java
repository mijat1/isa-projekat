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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import serverapp.isaBack.DTO.entities.CourseDTO;
import serverapp.isaBack.DTO.reservation.NewReservationDTO;

import serverapp.isaBack.DTO.reservation.ReservationDTO;
import serverapp.isaBack.DTO.users.IdDTO;
import serverapp.isaBack.model.ReservationType;
import serverapp.isaBack.service.interfaces.IBoatService;
import serverapp.isaBack.service.interfaces.ICourseService;
import serverapp.isaBack.service.interfaces.IReservationService;
import serverapp.isaBack.service.users.EmailService;
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
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@CrossOrigin
	@PostMapping("/cancelReservation")
	public ResponseEntity<?>  cancelResercation(@RequestBody IdDTO reservationId) {
		
		try {
			reservationService.cancelReservation(reservationId.getId());
			return new ResponseEntity<>(reservationId,HttpStatus.OK);
		
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
			
		
	}
	
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@CrossOrigin
	@GetMapping("/findAllFutureBoatReservationClient")
	public ResponseEntity<List<UnspecifiedDTO<ReservationDTO>>> findAllFuturePatientsAppointmets() {
		System.out.println("olala");
		try {
			return new ResponseEntity<>(reservationService.findAllFutureClientBoatReservation(ReservationType.BOAT) ,HttpStatus.OK);
		} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@CrossOrigin
	@GetMapping("/findAllHistoryBoatReservationClient")
	public ResponseEntity<List<UnspecifiedDTO<ReservationDTO>>> findAllHistoryClientsReservations() {
		System.out.println("olala");
		try {
			return new ResponseEntity<>(reservationService.findAllHistoryClientReservation(ReservationType.BOAT) ,HttpStatus.OK);
		} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@CrossOrigin
	@GetMapping("/historyReservation/sortByPriceAscending/{resrvationType}")
	public ResponseEntity<List<UnspecifiedDTO<ReservationDTO>>> findAllHistoryClientsReservationsSortByPriceAscending(@PathVariable ReservationType resrvationType) {
			
		try {				
				return new ResponseEntity<>(reservationService.findAllHistoryClientsReservationsSortByPriceAscending(resrvationType) ,HttpStatus.OK);
			
		} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@CrossOrigin
	@GetMapping("/historyReservation/sortByPriceDescending/{resrvationType}")
	public ResponseEntity<List<UnspecifiedDTO<ReservationDTO>>> findAllHistoryClientsReservationsSortByPriceDescending(@PathVariable ReservationType resrvationType) {
		
		try {
			return new ResponseEntity<>(reservationService.findAllHistoryClientsReservationsSortByPriceDescending(resrvationType) ,HttpStatus.OK);
			
		} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@CrossOrigin
	@GetMapping("/historyReservation/sortByDateAscending/{resrvationType}")
	public ResponseEntity<List<UnspecifiedDTO<ReservationDTO>>> findAllHistoryClientsReservationsSortByDateAscending(@PathVariable ReservationType resrvationType) {
		
		try {
			return new ResponseEntity<>(reservationService.findAllHistoryClientsReservationsSortByDateAscending(resrvationType) ,HttpStatus.OK);
			
		} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@CrossOrigin
	@GetMapping("/historyReservation/sortByDateDescending/{resrvationType}")
	public ResponseEntity<List<UnspecifiedDTO<ReservationDTO>>> findAllHistoryClientsReservationsSortByDateDescending(@PathVariable ReservationType resrvationType) {
		
		try {
			return new ResponseEntity<>(reservationService.findAllHistoryClientsReservationsSortByDateDescending(resrvationType) ,HttpStatus.OK);
			
		} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@CrossOrigin
	@GetMapping("/historyReservation/sortByDurationAscending/{resrvationType}")
	public ResponseEntity<List<UnspecifiedDTO<ReservationDTO>>> findAllHistoryClientsReservationsSortByDurationAscending(@PathVariable ReservationType resrvationType) {
		
		try {
			return new ResponseEntity<>(reservationService.findAllHistoryClientsReservationsSortByDurationAscending(resrvationType) ,HttpStatus.OK);
			
		} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@CrossOrigin
	@GetMapping("/historyReservation/sortByDurationDescending/{resrvationType}")
	public ResponseEntity<List<UnspecifiedDTO<ReservationDTO>>> findAllHistoryClientsReservationsSortByDurationDescending(@PathVariable ReservationType resrvationType) {
		
		try {
			return new ResponseEntity<>(reservationService.findAllHistoryClientsReservationsSortByDurationDescending(resrvationType) ,HttpStatus.OK);
			
		} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
