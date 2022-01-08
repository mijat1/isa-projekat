package serverapp.isaBack.service.interfaces;

import java.util.UUID;

import serverapp.isaBack.DTO.users.UserDTO;
import serverapp.isaBack.DTO.users.UserRegistrationDTO;

import serverapp.isaBack.unspecifiedDTO.UnspecifiedDTO;

public interface IUserService extends IService<UserDTO, UnspecifiedDTO<UserDTO>>{

	boolean activatingAccountClient(UUID id);

	UUID createClient(UserRegistrationDTO clientRegDTO);

}