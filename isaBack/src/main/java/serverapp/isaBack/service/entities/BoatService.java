package serverapp.isaBack.service.entities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import serverapp.isaBack.DTO.entities.BoatDTO;
import serverapp.isaBack.DTO.users.AuthorityDTO;
import serverapp.isaBack.mappers.entities.UnitMapper;
import serverapp.isaBack.model.Boat;
import serverapp.isaBack.repository.BoatRepository;
import serverapp.isaBack.service.interfaces.IBoatService;
import serverapp.isaBack.service.interfaces.IUnitGradeService;
import serverapp.isaBack.unspecifiedDTO.UnspecifiedDTO;

@Service
public class BoatService implements IBoatService {
	
	@Autowired
	private BoatRepository boatRepository;
	
	@Autowired
	private IUnitGradeService unitGradeService;
	
	private UnitMapper gradeMapper= new UnitMapper();
	

	@Override
	public List<UnspecifiedDTO<BoatDTO>>getAllBoats() throws IOException{
		System.out.println("usao u brodove");
		List<UnspecifiedDTO<BoatDTO>> retList = new ArrayList<UnspecifiedDTO<BoatDTO>>();
		List<Boat> boats = boatRepository.findAll();
		for(Boat boat : boats) {
               double avgGrade = getAvgGradeForUnit(boat.getId());
			
               retList.add(gradeMapper.MapBoatToBoatDTO(boat,avgGrade));
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
		public UnspecifiedDTO<BoatDTO> findById(UUID id) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public UUID create(BoatDTO entityDTO) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public void update(BoatDTO entityDTO, UUID id) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public boolean delete(UUID id) {
			// TODO Auto-generated method stub
			return false;
		}
}
		
	
