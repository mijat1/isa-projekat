package serverapp.isaBack.service.interfaces;

import java.util.List;
import java.util.UUID;

import serverapp.isaBack.DTO.users.AuthorityDTO;
import serverapp.isaBack.unspecifiedDTO.UnspecifiedDTO;


public interface IService<T, IdentifiableT> {

	List<UnspecifiedDTO<AuthorityDTO>> findAll();
	IdentifiableT findById(UUID id);
	UUID create(T entityDTO);
	void update(T entityDTO, UUID id);
    boolean delete(UUID id);

}
