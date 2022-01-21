package serverapp.isaBack.service.entities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
import serverapp.isaBack.model.Unit;
import serverapp.isaBack.repository.CottageRepository;
import serverapp.isaBack.service.interfaces.ICottageService;
import serverapp.isaBack.service.interfaces.IReservationService;
import serverapp.isaBack.service.interfaces.IUnitGradeService;
import serverapp.isaBack.unspecifiedDTO.UnspecifiedDTO;

@Service
public class CottageService implements ICottageService{
	

	@Autowired
	private CottageRepository cottageRepository;
	
	@Autowired
	private IUnitGradeService unitGradeService;
	
	private UnitMapper gradeMapper= new UnitMapper();
	

	@Autowired
	private IReservationService reservationService;
	
	
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
	
	
	@Override
	public List<UnspecifiedDTO<CottageDTO>> fidnAllFreeCottagesInDataRange(Date startDate, Date endDate,int people,String location, double gradeFrom,double gradeTo) throws IOException{
			
		System.out.println( "startno vreme " + startDate + "   " +  " Krajnje vremee " + endDate);
		
		List<UnspecifiedDTO<CottageDTO>> cottagesDTO= new ArrayList<UnspecifiedDTO<CottageDTO>>();  
		List<Unit>  cottages= new ArrayList<Unit>();
		cottages= reservationService.findAllFreeCottages(startDate,endDate);
		cottages=searchLocation(cottages, location);
		Cottage ct=null;
		for (Unit cottage : cottages) {
			 double avgGrade = getAvgGradeForUnit(cottage.getId());
			 
			 ct=cottageRepository.findById(cottage.getId()).get();
			 int capacity=ct.getNumberBedsPerRoom()*ct.getNumberOfRooms();
			 if(capacity>=people)
				 cottagesDTO.add(gradeMapper.MapCottageToCottageDTO(ct,avgGrade));
		}
		
		cottagesDTO = searchGrade(cottagesDTO, gradeFrom, gradeTo);
	
		return cottagesDTO;
		
		
	}
	
	private List<UnspecifiedDTO<CottageDTO>> searchGrade(List<UnspecifiedDTO<CottageDTO>> cottages, double gradeFrom, double gradeTo){
		List<UnspecifiedDTO<CottageDTO>> retVal = new ArrayList<UnspecifiedDTO<CottageDTO>>();
		double grade;
		for(UnspecifiedDTO<CottageDTO> b:cottages) {
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
		List<Cottage> cottageLocation=cottageRepository.findCottageByLocation(location.toLowerCase());
		List<Unit> retVal = new ArrayList<Unit>();
		for (Unit unit : units) {
			System.out.println("usao u pretragu lokacije vikendaje");
			for(Cottage cottage:cottageLocation) {
			if(unit.getId().equals(cottage.getId())) {
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
	public List<UnspecifiedDTO<CottageDTO>> findAllCottagesWithFreePeriodSortByGradeDescending(Date startDate, Date endDate,int people,String location, double gradeFrom,double gradeTo) throws IOException{
		
		List<UnspecifiedDTO<CottageDTO>> boatDTOSortedByGradeDescending=fidnAllFreeCottagesInDataRange(startDate,endDate,people,location,gradeFrom,gradeTo);
				
		Collections.sort(boatDTOSortedByGradeDescending, (boat1, boat2) -> Double.compare(boat1.EntityDTO.getAvgGrade(), boat2.EntityDTO.getAvgGrade()));
		Collections.reverse(boatDTOSortedByGradeDescending);
		
		
		return boatDTOSortedByGradeDescending;
		
	}
	
	@Override
	public List<UnspecifiedDTO<CottageDTO>> findAllCottagesWithFreePeriodSortByGradeAscending(Date startDate, Date endDate,int people,String location, double gradeFrom,double gradeTo) throws IOException{
		
		List<UnspecifiedDTO<CottageDTO>> boatDTOSortedByGradeAscending=fidnAllFreeCottagesInDataRange(startDate,endDate,people,location,gradeFrom,gradeTo);
				
		Collections.sort(boatDTOSortedByGradeAscending, (boat1, boat2) -> Double.compare(boat1.EntityDTO.getAvgGrade(), boat2.EntityDTO.getAvgGrade()));
		
		
		
		return boatDTOSortedByGradeAscending;
		
	}
	
	@Override
	public List<UnspecifiedDTO<CottageDTO>> findAllCottagesWithFreePeriodSortByPriceDescending(Date startDate, Date endDate,int people,String location, double gradeFrom,double gradeTo) throws IOException{
		
		List<UnspecifiedDTO<CottageDTO>> boatDTOSortedByPriceDescending=fidnAllFreeCottagesInDataRange(startDate,endDate,people,location,gradeFrom,gradeTo);
				
		Collections.sort(boatDTOSortedByPriceDescending, (boat1, boat2) -> Double.compare(boat1.EntityDTO.getPrice(), boat2.EntityDTO.getPrice()));
		Collections.reverse(boatDTOSortedByPriceDescending);
		
		
		return boatDTOSortedByPriceDescending;
		
	}
	
	@Override
	public List<UnspecifiedDTO<CottageDTO>> findAllCottagesWithFreePeriodSortByPriceAscending(Date startDate, Date endDate,int people,String location, double gradeFrom,double gradeTo) throws IOException{
		
		List<UnspecifiedDTO<CottageDTO>> boatDTOSortedByPriceAscending=fidnAllFreeCottagesInDataRange(startDate,endDate,people,location,gradeFrom,gradeTo);
				
		Collections.sort(boatDTOSortedByPriceAscending, (boat1, boat2) -> Double.compare(boat1.EntityDTO.getPrice(), boat2.EntityDTO.getPrice()));
		
		
		
		return boatDTOSortedByPriceAscending;
		
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
