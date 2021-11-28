package serverapp.isaBack.controller.users;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import serverapp.isaBack.DTO.users.UserRegistrationDTO;
import serverapp.isaBack.service.users.UserService;

@RestController
@RequestMapping(value = "/reg")
public class RegistrationController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/signupclient")
	public ResponseEntity<UUID> addUser(@RequestBody UserRegistrationDTO clientRegistrationDTO) {
		try {
			System.out.println("usao u /patsignup" + clientRegistrationDTO.getName());
			System.out.println(clientRegistrationDTO.getEmail()+" AXAXAXAXA");
			UUID userId = userService.createClient(clientRegistrationDTO);
			return new ResponseEntity<UUID>(userId, HttpStatus.CREATED);
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/activeAccountClient/{clientId}")
	public ResponseEntity<?> activateClient(@PathVariable UUID clientId) {
		System.out.println("ide gas ide plin" + clientId.toString());
		try {
			if (userService.activatingAccountClient(clientId))
				return new ResponseEntity<>(HttpStatus.OK);
			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
		}
	}

}
