package serverapp.isaBack.services.reservation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import serverapp.isaBack.DTO.entities.UnitDTO;
import serverapp.isaBack.DTO.reservation.NewReservationDTO;
import serverapp.isaBack.DTO.reservation.ReservationDTO;
import serverapp.isaBack.DTO.users.UserNameDTO;
import serverapp.isaBack.mappers.entities.ReservationMapper;
import serverapp.isaBack.mappers.entities.UnitMapper;
import serverapp.isaBack.model.AvailablePeriod;
import serverapp.isaBack.model.Boat;
import serverapp.isaBack.model.Client;
import serverapp.isaBack.model.OtherTag;
import serverapp.isaBack.model.Reservation;
import serverapp.isaBack.model.ReservationStatus;
import serverapp.isaBack.model.ReservationType;
import serverapp.isaBack.model.Unit;
import serverapp.isaBack.model.User;
import serverapp.isaBack.repository.AvailablePeriodRepository;
import serverapp.isaBack.repository.BoatRepository;
import serverapp.isaBack.repository.ClientRepository;
import serverapp.isaBack.repository.ReservationRepository;
import serverapp.isaBack.repository.UnitRepository;
import serverapp.isaBack.repository.UserRepository;
import serverapp.isaBack.service.interfaces.IReservationService;
import serverapp.isaBack.service.interfaces.IUserService;
import serverapp.isaBack.unspecifiedDTO.UnspecifiedDTO;

@Service
public class ReservationService implements IReservationService{
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private AvailablePeriodRepository periodRepository;
	
	@Autowired
	private IUserService userService;

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BoatRepository boatRepository;
	
	@Autowired
	private UnitRepository  unitRepository;
	
	private UnitMapper unitMapper= new UnitMapper();
	private ReservationMapper resMapper= new ReservationMapper();
	
	@Override	
	public List<Unit> findAllFreeBoats(Date startDate, Date endDate) {
		
		List<AvailablePeriod> availablePeriodInDateRange= new ArrayList<AvailablePeriod>();
		
		availablePeriodInDateRange= periodRepository.findAvailablePeriodInDateRange(startDate,endDate, startDate.getHours(), endDate.getHours());
				
		List<Reservation> busyReservationsInDataRange= reservationRepository.findAllreservationsInDataRange(startDate,endDate);
					
		List<Unit> pharmacyWithFreeConsulations= findBoatsWithFreePeroid(availablePeriodInDateRange,busyReservationsInDataRange);
		
				
		return pharmacyWithFreeConsulations;
	}
	
	
	
	public List<Unit> findBoatsWithFreePeroid(List<AvailablePeriod> availablePeriodInDateRange, List<Reservation> reservationInDataRange){
		
		List<Unit> boatsWithFreePeriod= new ArrayList<Unit>();
		System.out.println("ej alo bidiou");
		for (AvailablePeriod currentAP : availablePeriodInDateRange) {
				System.out.println("ej alo bidiou");
				boolean scheduled= false;			
				
				for (Reservation currentR : reservationInDataRange) {
					if(currentAP.getUnit().getId().equals(currentR.getUnit().getId())) {
						scheduled = true;
						break;
					}
					
				}
				
				if(scheduled==false)
					boatsWithFreePeriod.add(currentAP.getUnit());
			
		}
		
		
		
		return throwOutDuplicatesBoats(boatsWithFreePeriod);
		
		
	}
	
	public List<Unit> throwOutDuplicatesBoats(List<Unit> boatWithFreePeriod){
		
		List<Unit> filtered= new ArrayList<Unit>();
		
		for (Unit unit : boatWithFreePeriod) {
			
				boolean alreadyAdded= false;
				for(Unit current: filtered) {
					if(unit.getId().equals(current.getId())) {
						alreadyAdded = true;
						break;
					}
				}
				
				if(alreadyAdded==false)
					filtered.add(unit);
					
		}
		
		
		return filtered;
		
		
	}
	
	
	@Override
	
	public void makeBoatReservation(NewReservationDTO reservationDTO){
		
		Date startDate= new Date(reservationDTO.getStartDateTime());
		Date endDate= new Date(reservationDTO.getStartDateTime()+24*60*60*1000*reservationDTO.getDays());
		
		System.out.println( "startno vreme " + startDate + "   " +  " Krajnje vremee " + endDate);
		
	
		anyBusyBoatReservationInDataRange(reservationDTO,startDate,endDate);
		
		Reservation reservation= makeReservation(reservationDTO,startDate,endDate);
		
		reservationRepository.save(reservation);
		
		
		/*try {
			//emailService.sendConsultationAppointmentReservationNotification(appointment);
		} catch (MessagingException e) {}
		
		*/
	}
	
	
	
	public void anyBusyBoatReservationInDataRange(NewReservationDTO reservationRequestDTO,Date startDate, Date endDate  ){
		
		if(reservationRepository.findAllBusyReservationInDataRangeBoat(startDate,endDate,reservationRequestDTO.getUnitId()).size()>0)
			throw new IllegalArgumentException("Brod je zauzet u tom terminu.");
		
		if(!( periodRepository.findAvailablePeriodInDateRangeForBoat(startDate,endDate, reservationRequestDTO.getUnitId()).size()>0))
			throw new IllegalArgumentException("Brod nije dostupan u izabranom terminu");
		
	}
	
	

	public Reservation makeReservation(NewReservationDTO reservationRequestDTO,Date startDate, Date endDate  ){
		
		UUID clientId = userService.getLoggedUserId();
		Client client = clientRepository.findById(clientId).get();
	    Boat boat = boatRepository.findBoatById(reservationRequestDTO.getUnitId());
		User owner = userRepository.findById(boat.getOwner().getId()).get();
		Unit unit = unitRepository.findById(boat.getId()).get();
		Double price= unit.getPrice()*reservationRequestDTO.getDays();
		List<OtherTag> servicesList=new ArrayList<OtherTag>();
		for(OtherTag o:unit.getServices()) {
			for(UUID tagId : reservationRequestDTO.getListServices()) {
				if(tagId.equals(o.getId())){
					servicesList.add(o);
					price+=o.getPrice();
				}
			}
			
			
		}
		Reservation reservation= new Reservation( owner,unit, startDate, endDate,price,client, ReservationType.BOAT, ReservationStatus.RESERVED);
		
		isReservationValid(reservation);
		
		return reservation;
		
	}
	
	public void isReservationValid(Reservation reservation ){
		
		if (!(reservation.getStartDateTime().after(new Date())))
			throw new IllegalArgumentException("Početni datum ne može biti pre trenutnog.");
		
		if(reservationRepository.findAllReservationsForClientInDataRange(reservation.getStartDateTime(), reservation.getEndDateTime(), reservation.getClient().getId()).size() > 0)
				throw new IllegalArgumentException("Klijent ima već rezervaciju za brod u izabranom terminu");
	}
	
	@Override
	public List<UnspecifiedDTO<ReservationDTO>> findAllFutureClientBoatReservation(ReservationType reservationType) throws IOException {
		
		UUID logedClientID= userService.getLoggedUserId();
		List<Reservation> reservations = reservationRepository.findAllFutureClientsReservation(logedClientID, reservationType); 
		System.out.println(reservations);
		List<User> owners= userRepository.findAll();
		List<Unit> units=unitRepository.findAll();
		List<UnspecifiedDTO<UserNameDTO>> ownerNames= new ArrayList<UnspecifiedDTO<UserNameDTO>>();
		List<UnspecifiedDTO<UnitDTO>> unitsDTOs= new ArrayList<UnspecifiedDTO<UnitDTO>>();
		for(User o : owners) {
			ownerNames.add(new UnspecifiedDTO<UserNameDTO>(o.getId(),new UserNameDTO(o.toString())));
		}
	  for(Unit u : units) {
		unitsDTOs.add(unitMapper.mapUnitToUnitDTO(u));
	  }
		System.out.println("INJEeee");
		List<UnspecifiedDTO<ReservationDTO>> freeReservations=  resMapper.MapReservationToReservationDTO(reservations,unitsDTOs,ownerNames);             
		
		
		return freeReservations;
		
	}
	
	@Override
	public List<UnspecifiedDTO<ReservationDTO>> findAllHistoryClientReservation(ReservationType reservationType) throws IOException {
		
		UUID logedClientID= userService.getLoggedUserId();
		List<Reservation> reservations = reservationRepository.findAllHistoryClientsReservation(logedClientID, reservationType); 
		System.out.println(reservations);
		List<User> owners= userRepository.findAll();
		List<Unit> units=unitRepository.findAll();
		List<UnspecifiedDTO<UserNameDTO>> ownerNames= new ArrayList<UnspecifiedDTO<UserNameDTO>>();
		List<UnspecifiedDTO<UnitDTO>> unitsDTOs= new ArrayList<UnspecifiedDTO<UnitDTO>>();
		for(User o : owners) {
			ownerNames.add(new UnspecifiedDTO<UserNameDTO>(o.getId(),new UserNameDTO(o.toString())));
		}
	  for(Unit u : units) {
		unitsDTOs.add(unitMapper.mapUnitToUnitDTO(u));
	  }
		System.out.println("Istorijaaaa");
		List<UnspecifiedDTO<ReservationDTO>> freeReservations=  resMapper.MapReservationToReservationDTO(reservations,unitsDTOs,ownerNames);             
		
		
		return freeReservations;
		
	}
	
	

	@Override
	public List<UnspecifiedDTO<ReservationDTO>> findAllHistoryClientsReservationsSortByPriceAscending(ReservationType resrvationType) throws Exception {
				
		List<UnspecifiedDTO<ReservationDTO>> sorterdReservations= findAllHistoryClientReservation(resrvationType);             
		
		if(sorterdReservations==null)
			throw new Exception(" The list is null");
		
		Collections.sort(sorterdReservations, (reservation1, reservation2) -> Double.compare(reservation1.EntityDTO.getPrice(), reservation2.EntityDTO.getPrice()));
		
		
		return sorterdReservations;
		
	}
	
	
	@Override
	public List<UnspecifiedDTO<ReservationDTO>> findAllHistoryClientsReservationsSortByPriceDescending(ReservationType resrvationType) throws Exception {
				
		List<UnspecifiedDTO<ReservationDTO>> sorterdReservations=  findAllHistoryClientReservation(resrvationType);             
		
		if(sorterdReservations==null)
			throw new Exception(" The list is null");
		
		Collections.sort(sorterdReservations, (reservation1, reservation2) -> Double.compare(reservation1.EntityDTO.getPrice(), reservation2.EntityDTO.getPrice()));
		Collections.reverse(sorterdReservations);
		
		return sorterdReservations;
		
	}
	
	
	@Override
	public List<UnspecifiedDTO<ReservationDTO>> findAllHistoryClientsReservationsSortByDateAscending(ReservationType resrvationType) throws Exception {
				
		List<UnspecifiedDTO<ReservationDTO>> sorterdReservations=  findAllHistoryClientReservation(resrvationType);             
		
		if(sorterdReservations==null)
			throw new Exception(" The list is null");
		
		Collections.sort(sorterdReservations, (reservation1, reservation2) -> Double.compare(reservation1.EntityDTO.getStartDateTime().getTime(), reservation2.EntityDTO.getStartDateTime().getTime()));
		
		
		return sorterdReservations;
		
	}
	
	
	@Override
	public List<UnspecifiedDTO<ReservationDTO>> findAllHistoryClientsReservationsSortByDateDescending(ReservationType resrvationType) throws Exception {
				
		List<UnspecifiedDTO<ReservationDTO>> sorterdReservations=  findAllHistoryClientReservation(resrvationType);             
		
		if(sorterdReservations==null)
			throw new Exception(" The list is null");
		
		Collections.sort(sorterdReservations, (reservation1, reservation2) -> Double.compare(reservation1.EntityDTO.getStartDateTime().getTime(), reservation2.EntityDTO.getStartDateTime().getTime()));
		Collections.reverse(sorterdReservations);
		
		return sorterdReservations;
		
	}
	
	
	
	
	@Override
	public List<UnspecifiedDTO<ReservationDTO>> findAllHistoryClientsReservationsSortByDurationAscending(ReservationType resrvationType) throws Exception {
				
		List<UnspecifiedDTO<ReservationDTO>> sorterdReservations=  findAllHistoryClientReservation(resrvationType);             
		
		if(sorterdReservations==null)
			throw new Exception(" The list is null");
		
		Collections.sort(sorterdReservations, (reservation1, reservation2) -> Double.compare(reservation1.EntityDTO.getEndDateTime().getTime() - reservation1.EntityDTO.getStartDateTime().getTime(),       
				reservation2.EntityDTO.getEndDateTime().getTime() - reservation2.EntityDTO.getStartDateTime().getTime()));
		
		return sorterdReservations;
		
	}
	
	
	
	@Override
	public List<UnspecifiedDTO<ReservationDTO>> findAllHistoryClientsReservationsSortByDurationDescending(ReservationType resrvationType) throws Exception {
				
		List<UnspecifiedDTO<ReservationDTO>> sorterdReservations=  findAllHistoryClientReservation(resrvationType);             
		
		if(sorterdReservations==null)
			throw new Exception(" The list is null");
		
		Collections.sort(sorterdReservations, (reservation1, reservation2) -> Double.compare(reservation1.EntityDTO.getEndDateTime().getTime() - reservation1.EntityDTO.getStartDateTime().getTime(),       
				reservation2.EntityDTO.getEndDateTime().getTime() - reservation2.EntityDTO.getStartDateTime().getTime()));
		
		Collections.reverse(sorterdReservations);
		
		return sorterdReservations;
		
	}
	
	
	
}
