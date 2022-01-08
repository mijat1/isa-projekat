package serverapp.isaBack.service.interfaces;

import java.io.IOException;
import java.util.List;

import serverapp.isaBack.DTO.entities.CottageDTO;
import serverapp.isaBack.unspecifiedDTO.UnspecifiedDTO;

public interface ICottageService extends IService<CottageDTO, UnspecifiedDTO<CottageDTO>> {

	List<UnspecifiedDTO<CottageDTO>> getAllCottages() throws IOException;

}
