package serverapp.isaBack.service.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import serverapp.isaBack.DTO.entities.UnitSDTO;
import serverapp.isaBack.model.Client;
import serverapp.isaBack.model.Unit;
import serverapp.isaBack.repository.ClientRepository;
import serverapp.isaBack.repository.UnitFeedbackRepository;
import serverapp.isaBack.repository.UserFeedbackRepository;
import serverapp.isaBack.service.interfaces.IUnitGradeService;
import serverapp.isaBack.service.users.UserService;
import serverapp.isaBack.unspecifiedDTO.UnspecifiedDTO;

@Service
public class UnitGradeService implements IUnitGradeService{

	@Autowired
	UnitFeedbackRepository unitGradeRepository;
	
	@Autowired
	UserFeedbackRepository userGradeRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private UserService userService;
	
	@Override
	public double getAvgGradeForUnit(UUID unitId) {
		double avgGrade;
		try {
			avgGrade = unitGradeRepository.getAvgGradeForUnit(unitId);
		} catch (Exception e) {
			avgGrade = 0.0;
		}
		
		return avgGrade;
	}
	
	@Override

	public double getAvgGradeForUser(UUID userId) {
		double avgGrade;
		try {
			avgGrade = userGradeRepository.getAvgGradeForUser(userId);
		} catch (Exception e) {
			avgGrade = 0.0;
		}
		
		return avgGrade;
	}
	
	@Override
	public List<UnspecifiedDTO<UnitSDTO>> getAllClientsSubscribedUnits() {
		 	
		
		UUID logedUserId= userService.getLoggedUserId();
		Client client = clientRepository.findById(logedUserId).get();
		
		List<Unit> units =client.getUnits();
		
		List<UnspecifiedDTO<UnitSDTO>> DTOList = new ArrayList<UnspecifiedDTO<UnitSDTO>>();
				
		for (Unit current : units) 
		{	
			double avgGrade= getAvgGradeForUnit(current.getId());
			UnitSDTO unitDTO= new UnitSDTO(current.getName(),current.getDescription(),current.getPrice(),avgGrade);	
			DTOList.add(new UnspecifiedDTO<UnitSDTO>(current.getId(),unitDTO));
		}
		
		return DTOList;
	}
}
