package serverapp.isaBack.service.interfaces;

import java.io.IOException;
import java.util.List;

import serverapp.isaBack.DTO.entities.BoatDTO;
import serverapp.isaBack.unspecifiedDTO.UnspecifiedDTO;

public interface IBoatService extends IService<BoatDTO, UnspecifiedDTO<BoatDTO>> {

	List<UnspecifiedDTO<BoatDTO>> getAllBoats() throws IOException;

}
