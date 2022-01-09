package serverapp.isaBack.service.interfaces;

import java.util.UUID;

import serverapp.isaBack.DTO.users.ChangePasswordDTO;
import serverapp.isaBack.DTO.users.ClientDTO;
import serverapp.isaBack.DTO.users.UserChangeInfoDTO;
import serverapp.isaBack.DTO.users.UserDTO;
import serverapp.isaBack.DTO.users.UserRegistrationDTO;

import serverapp.isaBack.unspecifiedDTO.UnspecifiedDTO;

public interface IUserService extends IService<UserDTO, UnspecifiedDTO<UserDTO>>{

	boolean activatingAccountClient(UUID id);

	UUID createClient(UserRegistrationDTO clientRegDTO);

	UUID getLoggedUserId();

	UnspecifiedDTO<ClientDTO> getLoggedClient();

	void updateClient(UserChangeInfoDTO clientInfoChangeDTO);

	void changePassword(ChangePasswordDTO changePasswordDTO);

}
