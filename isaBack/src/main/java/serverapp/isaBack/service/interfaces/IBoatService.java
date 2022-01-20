package serverapp.isaBack.service.interfaces;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import serverapp.isaBack.DTO.entities.BoatDTO;
import serverapp.isaBack.unspecifiedDTO.UnspecifiedDTO;

public interface IBoatService extends IService<BoatDTO, UnspecifiedDTO<BoatDTO>> {

	List<UnspecifiedDTO<BoatDTO>> getAllBoats() throws IOException;

	List<UnspecifiedDTO<BoatDTO>> fidnAllFreeBoatsInDataRange(Date startDate, Date endDate,int people,String location, double gradeFrom,double gradeTo) throws IOException;

	List<UnspecifiedDTO<BoatDTO>> findAllBoatsWithFreePeriodSortByGradeDescending(Date startDate, Date endDate,
			int people, String location, double gradeFrom, double gradeTo) throws IOException;

	List<UnspecifiedDTO<BoatDTO>> findAllBoatsWithFreePeriodSortByGradeAscending(Date startDate, Date endDate,
			int people, String location, double gradeFrom, double gradeTo) throws IOException;
	
	List<UnspecifiedDTO<BoatDTO>> findAllBoatsWithFreePeriodSortByPriceDescending(Date startDate, Date endDate,
			int people, String location, double gradeFrom, double gradeTo) throws IOException;

	List<UnspecifiedDTO<BoatDTO>> findAllBoatsWithFreePeriodSortByPriceAscending(Date startDate, Date endDate,
			int people, String location, double gradeFrom, double gradeTo) throws IOException;


}
