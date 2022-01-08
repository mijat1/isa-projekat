package serverapp.isaBack.service.entities;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import serverapp.isaBack.repository.UnitFeedbackRepository;
import serverapp.isaBack.service.interfaces.IUnitGradeService;

@Service
public class UnitGradeService implements IUnitGradeService{

	@Autowired
	UnitFeedbackRepository unitGradeRepository;
	
	@Override
	public double getAvgGradeForEmployee(UUID unitId) {
		double avgGrade;
		try {
			avgGrade = unitGradeRepository.getAvgGradeForUnit(unitId);
		} catch (Exception e) {
			avgGrade = 0.0;
		}
		
		return avgGrade;
	}
}
