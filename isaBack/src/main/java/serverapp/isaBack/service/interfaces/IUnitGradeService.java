package serverapp.isaBack.service.interfaces;

import java.util.List;
import java.util.UUID;

import serverapp.isaBack.DTO.entities.UnitSDTO;
import serverapp.isaBack.unspecifiedDTO.UnspecifiedDTO;

public interface IUnitGradeService {

	double getAvgGradeForUnit(UUID unitId);

	double getAvgGradeForUser(UUID userId);

	List<UnspecifiedDTO<UnitSDTO>> getAllClientsSubscribedUnits();

}
