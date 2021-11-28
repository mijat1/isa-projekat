package serverapp.isaBack.service.users;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import serverapp.isaBack.DTO.users.AuthorityDTO;
import serverapp.isaBack.DTO.users.UserDTO;
import serverapp.isaBack.DTO.users.UserRegistrationDTO;
import serverapp.isaBack.model.Authority;
import serverapp.isaBack.model.Client;
import serverapp.isaBack.repository.ClientRepository;
import serverapp.isaBack.repository.UserRepository;
import serverapp.isaBack.service.interfaces.IUserService;
import serverapp.isaBack.unspecifiedDTO.UnspecifiedDTO;

@Service
public class UserService implements IUserService{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthorityService authorityService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Override
	public UUID createClient(UserRegistrationDTO clientRegDTO) {
	
		Client client = createClientFromDTO(clientRegDTO);
		System.out.println("pacijent: " + client.getName());
		UnspecifiedDTO<AuthorityDTO> authority = authorityService.findByName("ROLE_CLIENT");
		List<Authority> authorities = new ArrayList<Authority>();
		authorities.add(new Authority(authority.Id,authority.EntityDTO.getName()));
		client.setUserAuthorities(authorities);
		
		clientRepository.save(client);
		
		try {
			emailService.sendSignUpNotificaitionAsync(client);
		} catch (MailException | InterruptedException e) {
			e.printStackTrace();
		} catch (MessagingException e) {			
			e.printStackTrace();
		}
		
		return client.getId();
	}
	
	@Override
	public boolean activatingAccountClient(UUID id) {
		try {
			Client client = clientRepository.getOne(id);
			client.setActive(true);
			clientRepository.save(client);
			return true;
		}
		catch (EntityNotFoundException e) { return false; } 
		catch (IllegalArgumentException e) { return false; }
	}
	
	
	private Client createClientFromDTO(UserRegistrationDTO clientRegDTO) {
		return new Client(clientRegDTO.getEmail(), passwordEncoder.encode(clientRegDTO.getPassword()), clientRegDTO.getName(), clientRegDTO.getSurname(), clientRegDTO.getAddress(), clientRegDTO.getPhoneNumber());
	}

	@Override
	public List<UnspecifiedDTO<AuthorityDTO>> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UnspecifiedDTO<UserDTO> findById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UUID create(UserDTO entityDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(UserDTO entityDTO, UUID id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(UUID id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
