package serverapp.isaBack.controller.users;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import serverapp.isaBack.DTO.users.TokenDTO;
import serverapp.isaBack.model.Authority;
import serverapp.isaBack.model.User;
import serverapp.isaBack.repository.UserRepository;
import serverapp.isaBack.security.TokenUtils;
import serverapp.isaBack.security.auth.JwtAuthenticationRequest;


@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenUtils tokenUtils;
	

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/login")
	@CrossOrigin
	public ResponseEntity<TokenDTO> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authRequest,
			HttpServletResponse response) throws AuthenticationException, IOException {

		String jwt;
		int expiresIn;
		List<String> roles = new ArrayList<String>();
		
		System.out.println(passwordEncoder.encode(authRequest.getPassword()));

		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

			System.out.println("prosao autentifikaciju");
			SecurityContextHolder.getContext().setAuthentication(authentication);
			User user = (User) authentication.getPrincipal();
			
			Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
			
			System.out.println("sokovii");
			System.out.println(currentUser.getName());
			
			jwt = tokenUtils.generateToken(user.getUsername());
			expiresIn = tokenUtils.getExpiredIn();
			user.getUserAuthorities().forEach((a) -> roles.add(a.getName()));
			
			System.out.println(authRequest.getUsername() + authRequest.getPassword() + "AUTENTIFIKACIJA");
			
			

		} catch (BadCredentialsException e) {
			System.out.println("bad credentials");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} catch (DisabledException e) {
			System.out.println("nije aktiviran ");
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		} catch (Exception e) {
			System.out.println("neki eksepsn");
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		User user = userRepository.findByEmail(authRequest.getUsername());
		
		
		
		
		
	

		System.out.println("prosao do kraja");

		return new ResponseEntity<TokenDTO>(new TokenDTO(jwt, expiresIn, roles), HttpStatus.OK);
	}
	
	

	

}
