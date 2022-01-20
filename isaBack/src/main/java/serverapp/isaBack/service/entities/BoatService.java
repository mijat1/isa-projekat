package serverapp.isaBack.service.entities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import serverapp.isaBack.DTO.entities.BoatDTO;
import serverapp.isaBack.DTO.users.AuthorityDTO;
import serverapp.isaBack.mappers.entities.UnitMapper;
import serverapp.isaBack.model.Boat;
import serverapp.isaBack.model.Unit;
import serverapp.isaBack.repository.BoatRepository;
import serverapp.isaBack.service.interfaces.IBoatService;
import serverapp.isaBack.service.interfaces.IReservationService;
import serverapp.isaBack.service.interfaces.IUnitGradeService;
import serverapp.isaBack.unspecifiedDTO.UnspecifiedDTO;

@Service
public class BoatService implements IBoatService {
	
	@Autowired
	private BoatRepository boatRepository;
	
	@Autowired
	private IUnitGradeService unitGradeService;
	
	@Autowired
	private IReservationService reservationService;
	
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
	
	
	@Override
	public List<UnspecifiedDTO<BoatDTO>> fidnAllFreeBoatsInDataRange(Date startDate, Date endDate,int people,String location, double gradeFrom,double gradeTo) throws IOException{
			
		System.out.println( "startno vreme " + startDate + "   " +  " Krajnje vremee " + endDate);
		
		List<UnspecifiedDTO<BoatDTO>> boatDTO= new ArrayList<UnspecifiedDTO<BoatDTO>>();  
		List<Unit>  boats= new ArrayList<Unit>();
		boats= reservationService.findAllFreeBoats(startDate,endDate);
		boats=searchLocation(boats, location);
		Boat bop=null;
		for (Unit boat : boats) {
			 double avgGrade = getAvgGradeForUnit(boat.getId());
			 
			 bop=boatRepository.findBoatById(boat.getId());
			 if(bop.getCapacity()>=people)
				 boatDTO.add( gradeMapper.MapBoatToBoatDTO(bop,avgGrade));
		}
		
	     boatDTO = searchGrade(boatDTO, gradeFrom, gradeTo);
	
		return boatDTO;
		
		
	}
	
	private List<UnspecifiedDTO<BoatDTO>> searchGrade(List<UnspecifiedDTO<BoatDTO>> boats, double gradeFrom, double gradeTo){
		List<UnspecifiedDTO<BoatDTO>> retVal = new ArrayList<UnspecifiedDTO<BoatDTO>>();
		double grade;
		for(UnspecifiedDTO<BoatDTO> b:boats) {
			grade=b.EntityDTO.getAvgGrade();
			if(!(gradeFrom == -1.0 || gradeTo == -1.0)) {
				if(grade >= gradeFrom && grade <= gradeTo) {
					retVal.add(b);
				}
			}else {
				if(gradeFrom == -1.0 & gradeTo != -1.0) {
					if(grade <= gradeTo)
						retVal.add(b);
				}else if (gradeTo == -1.0 & gradeFrom != -1.0){
					if(grade >= gradeFrom)
						retVal.add(b);
				}else {
						retVal.add(b);
				}
			}
			
		}
		return retVal;
	}
	
	private List<Unit> searchLocation(List<Unit> units, String location){
		
		if(location.equals(""))
			return units;
		List<Boat> boatLocation=boatRepository.findBoatByLocation(location.toLowerCase());
		List<Unit> retVal = new ArrayList<Unit>();
		for (Unit unit : units) {
			System.out.println("usao u pretragu lokacije");
			for(Boat boat:boatLocation) {
			if(unit.getId().equals(boat.getId())) {
				retVal.add(unit);
			}
			}
		}
		
		return retVal;
	}
	
	
	
	public double getAvgGradeForUnit(UUID unitId){
		
		return unitGradeService.getAvgGradeForUnit(unitId);
		
		}
	
	
	@Override
	public List<UnspecifiedDTO<BoatDTO>> findAllBoatsWithFreePeriodSortByGradeDescending(Date startDate, Date endDate,int people,String location, double gradeFrom,double gradeTo) throws IOException{
		
		List<UnspecifiedDTO<BoatDTO>> boatDTOSortedByGradeDescending=fidnAllFreeBoatsInDataRange(startDate,endDate,people,location,gradeFrom,gradeTo);
				
		Collections.sort(boatDTOSortedByGradeDescending, (boat1, boat2) -> Double.compare(boat1.EntityDTO.getAvgGrade(), boat2.EntityDTO.getAvgGrade()));
		Collections.reverse(boatDTOSortedByGradeDescending);
		
		
		return boatDTOSortedByGradeDescending;
		
	}
	
	@Override
	public List<UnspecifiedDTO<BoatDTO>> findAllBoatsWithFreePeriodSortByGradeAscending(Date startDate, Date endDate,int people,String location, double gradeFrom,double gradeTo) throws IOException{
		
		List<UnspecifiedDTO<BoatDTO>> boatDTOSortedByGradeAscending=fidnAllFreeBoatsInDataRange(startDate,endDate,people,location,gradeFrom,gradeTo);
				
		Collections.sort(boatDTOSortedByGradeAscending, (boat1, boat2) -> Double.compare(boat1.EntityDTO.getAvgGrade(), boat2.EntityDTO.getAvgGrade()));
		
		
		
		return boatDTOSortedByGradeAscending;
		
	}
	
	@Override
	public List<UnspecifiedDTO<BoatDTO>> findAllBoatsWithFreePeriodSortByPriceDescending(Date startDate, Date endDate,int people,String location, double gradeFrom,double gradeTo) throws IOException{
		
		List<UnspecifiedDTO<BoatDTO>> boatDTOSortedByPriceDescending=fidnAllFreeBoatsInDataRange(startDate,endDate,people,location,gradeFrom,gradeTo);
				
		Collections.sort(boatDTOSortedByPriceDescending, (boat1, boat2) -> Double.compare(boat1.EntityDTO.getPrice(), boat2.EntityDTO.getPrice()));
		Collections.reverse(boatDTOSortedByPriceDescending);
		
		
		return boatDTOSortedByPriceDescending;
		
	}
	
	@Override
	public List<UnspecifiedDTO<BoatDTO>> findAllBoatsWithFreePeriodSortByPriceAscending(Date startDate, Date endDate,int people,String location, double gradeFrom,double gradeTo) throws IOException{
		
		List<UnspecifiedDTO<BoatDTO>> boatDTOSortedByPriceAscending=fidnAllFreeBoatsInDataRange(startDate,endDate,people,location,gradeFrom,gradeTo);
				
		Collections.sort(boatDTOSortedByPriceAscending, (boat1, boat2) -> Double.compare(boat1.EntityDTO.getPrice(), boat2.EntityDTO.getPrice()));
		
		
		
		return boatDTOSortedByPriceAscending;
		
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
		
	
