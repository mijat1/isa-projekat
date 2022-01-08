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
	
}
