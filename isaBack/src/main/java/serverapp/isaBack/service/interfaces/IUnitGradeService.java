package serverapp.isaBack.service.interfaces;

import java.util.UUID;

public interface IUnitGradeService {

	double getAvgGradeForUnit(UUID unitId);

	double getAvgGradeForUser(UUID userId);

}
