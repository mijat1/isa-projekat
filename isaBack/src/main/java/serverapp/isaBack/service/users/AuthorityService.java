package serverapp.isaBack.service.users;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import serverapp.isaBack.DTO.users.AuthorityDTO;
import serverapp.isaBack.model.Authority;
import serverapp.isaBack.repository.AuthorityRepository;
import serverapp.isaBack.service.interfaces.IAuthorityService;
import serverapp.isaBack.unspecifiedDTO.UnspecifiedDTO;

@Service
public class AuthorityService implements IAuthorityService {
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Override
	public UnspecifiedDTO<AuthorityDTO> findByName(String name) {
		Authority authority = authorityRepository.findByName(name);
		if (authority == null)
			return null;
		AuthorityDTO authorityDTO= new AuthorityDTO(authority.getName());
		return new UnspecifiedDTO<AuthorityDTO>(authority.getId(),authorityDTO);
	}

	@Override
	public List<UnspecifiedDTO<AuthorityDTO>> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UnspecifiedDTO<AuthorityDTO> findById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UUID create(AuthorityDTO entityDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(AuthorityDTO entityDTO, UUID id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(UUID id) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
