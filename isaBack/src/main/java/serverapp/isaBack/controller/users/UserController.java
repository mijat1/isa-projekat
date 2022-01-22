package serverapp.isaBack.controller.users;

import java.util.UUID;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import serverapp.isaBack.DTO.entities.DeleteRequestDTO;
import serverapp.isaBack.DTO.users.ChangePasswordDTO;
import serverapp.isaBack.DTO.users.ClientDTO;
import serverapp.isaBack.DTO.users.IdDTO;
import serverapp.isaBack.DTO.users.UserChangeInfoDTO;
import serverapp.isaBack.security.TokenUtils;
import serverapp.isaBack.service.users.UserService;
import serverapp.isaBack.unspecifiedDTO.UnspecifiedDTO;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	
	@Autowired
	private UserService userService;
	
	 @Autowired
	 private TokenUtils tokenUtils;
	
	
	@GetMapping("/client")
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	public ResponseEntity<UnspecifiedDTO<ClientDTO>> getLogedClient(HttpServletRequest request) {
		try {
			UnspecifiedDTO<ClientDTO> client = userService.getLoggedClient();
			
			return new ResponseEntity<>(client,HttpStatus.OK);  
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		} 
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		
	}
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@CrossOrigin
	@PostMapping("/deleteRequest")
	public ResponseEntity<?>  deleteRequest(@RequestBody DeleteRequestDTO drDTO ) {
		
		try {
			userService.deleteRequest(drDTO.getText());
			return new ResponseEntity<>(HttpStatus.OK);
		
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
			
		
	}
	
	@CrossOrigin
	@PostMapping("/replydeleteRequestF")
	public ResponseEntity<?>  deleteRequestF(@RequestBody DeleteRequestDTO drDTO ) {
		
		try {
			userService.replyToDeleteRequest(drDTO.getId(),drDTO.getReply(),false);
			return new ResponseEntity<>(HttpStatus.OK);
		
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
			
		
	}
	
	@CrossOrigin
	@PostMapping("/replydeleteRequestT")
	public ResponseEntity<?>  deleteRequestT(@RequestBody DeleteRequestDTO drDTO) {
		
		try {
			userService.replyToDeleteRequest(drDTO.getId(),drDTO.getReply(),true);
			return new ResponseEntity<>(HttpStatus.OK);
		
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
			
		
	}
	@PutMapping("/client") 
	@CrossOrigin
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	public ResponseEntity<?> updatePhAdminInfo(@RequestBody UserChangeInfoDTO userInfoChangeDTO ) {
	  
		try {
			userService.updateClient(userInfoChangeDTO);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
		}
	}
	
	@PostMapping("/changePassword")
	@PreAuthorize("hasRole('ROLE_CLIENT') ")
	public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
		
		try {
			userService.changePassword(changePasswordDTO);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}catch (BadCredentialsException e){
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/subscribeToUnit/") 
	@CrossOrigin
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	public ResponseEntity<?> subscribeToUnit(@RequestParam UUID unitId ) {

		System.out.println("usao11");
	  
		try {
			userService.subscribeToUnit(unitId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
		}
	}
	
	@GetMapping("/unsubscribeFromUnit") 
	@CrossOrigin
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	public ResponseEntity<?> unsubscribeFromUnit(@RequestParam UUID unitId ) {

		System.out.println("usao22");
		try {
			userService.unsubscribeFromUnit(unitId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
		}
	}
}
