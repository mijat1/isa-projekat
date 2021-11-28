package serverapp.isaBack.service.interfaces;

import serverapp.isaBack.DTO.users.AuthorityDTO;
import serverapp.isaBack.unspecifiedDTO.UnspecifiedDTO;

public interface IAuthorityService extends IService<AuthorityDTO, UnspecifiedDTO<AuthorityDTO>> {
	
	UnspecifiedDTO<AuthorityDTO> findByName ( String name );

}
