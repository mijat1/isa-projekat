package serverapp.isaBack.controller.entities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import serverapp.isaBack.DTO.entities.UnitDTO;
import serverapp.isaBack.DTO.entities.UnitSDTO;
import serverapp.isaBack.service.interfaces.IUnitGradeService;
import serverapp.isaBack.unspecifiedDTO.UnspecifiedDTO;


@RestController
@RequestMapping(value = "/unit", produces = MediaType.APPLICATION_JSON_VALUE)
public class UnitController {
	
	
	@Autowired
	private IUnitGradeService unitGradeService;
	
	@CrossOrigin
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@GetMapping("/AllClientSubscribedUnits") 
	public ResponseEntity<List<UnspecifiedDTO<UnitSDTO>>> getAllClientSubscribedUnits() {
		System.out.println("njnjnjnjnjnjn");
		try {
		return new ResponseEntity<>(unitGradeService.getAllClientsSubscribedUnits() ,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
}
