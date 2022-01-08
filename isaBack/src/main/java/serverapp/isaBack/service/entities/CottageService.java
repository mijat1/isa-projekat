package serverapp.isaBack.service.entities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import serverapp.isaBack.DTO.entities.BoatDTO;
import serverapp.isaBack.DTO.entities.CottageDTO;
import serverapp.isaBack.DTO.users.AuthorityDTO;
import serverapp.isaBack.mappers.entities.UnitMapper;
import serverapp.isaBack.model.Boat;
import serverapp.isaBack.model.Cottage;
import serverapp.isaBack.repository.CottageRepository;
import serverapp.isaBack.service.interfaces.ICottageService;
import serverapp.isaBack.service.interfaces.IUnitGradeService;
import serverapp.isaBack.unspecifiedDTO.UnspecifiedDTO;

@Service
public class CottageService implements ICottageService{
	

	@Autowired
	private CottageRepository cottageRepository;
	
	@Autowired
	private IUnitGradeService unitGradeService;
	
	private UnitMapper gradeMapper= new UnitMapper();
	

	@Override
	public List<UnspecifiedDTO<CottageDTO>>getAllCottages() throws IOException{
		System.out.println("usao u vikendice");
		List<UnspecifiedDTO<CottageDTO>> retList = new ArrayList<UnspecifiedDTO<CottageDTO>>();
		List<Cottage> cottages = cottageRepository.findAll();
		for(Cottage cottage : cottages) {
               double avgGrade = getAvgGradeForUnit(cottage.getId());
			
               retList.add(gradeMapper.MapCottageToCottageDTO(cottage,avgGrade));
		   }
		
		
		return retList;

		
		
	}
	
	
	public double getAvgGradeForUnit(UUID unitId){
		
		return unitGradeService.getAvgGradeForEmployee(unitId);
		
		}


	@Override
	public List<UnspecifiedDTO<AuthorityDTO>> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public UnspecifiedDTO<CottageDTO> findById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public UUID create(CottageDTO entityDTO) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void update(CottageDTO entityDTO, UUID id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean delete(UUID id) {
		// TODO Auto-generated method stub
		return false;
	}


	


}
