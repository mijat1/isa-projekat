package serverapp.isaBack.service.interfaces;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import serverapp.isaBack.DTO.entities.BoatDTO;
import serverapp.isaBack.DTO.entities.CottageDTO;
import serverapp.isaBack.unspecifiedDTO.UnspecifiedDTO;

public interface ICottageService extends IService<CottageDTO, UnspecifiedDTO<CottageDTO>> {

	List<UnspecifiedDTO<CottageDTO>> getAllCottages() throws IOException;

	List<UnspecifiedDTO<CottageDTO>> fidnAllFreeCottagesInDataRange(Date startDate, Date endDate, int people,
			String location, double gradeFrom, double gradeTo) throws IOException;
	
	List<UnspecifiedDTO<CottageDTO>> findAllCottagesWithFreePeriodSortByGradeDescending(Date startDate, Date endDate,
			int people, String location, double gradeFrom, double gradeTo) throws IOException;

	List<UnspecifiedDTO<CottageDTO>> findAllCottagesWithFreePeriodSortByGradeAscending(Date startDate, Date endDate,
			int people, String location, double gradeFrom, double gradeTo) throws IOException;
	
	List<UnspecifiedDTO<CottageDTO>> findAllCottagesWithFreePeriodSortByPriceDescending(Date startDate, Date endDate,
			int people, String location, double gradeFrom, double gradeTo) throws IOException;

	List<UnspecifiedDTO<CottageDTO>> findAllCottagesWithFreePeriodSortByPriceAscending(Date startDate, Date endDate,
			int people, String location, double gradeFrom, double gradeTo) throws IOException;


}
