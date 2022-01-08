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
	
}
