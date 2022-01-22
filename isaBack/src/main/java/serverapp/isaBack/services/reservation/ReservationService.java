package serverapp.isaBack.services.reservation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;

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
import serverapp.isaBack.model.Cottage;
import serverapp.isaBack.model.FishingCourse;
import serverapp.isaBack.model.OtherTag;
import serverapp.isaBack.model.Reservation;
import serverapp.isaBack.model.ReservationStatus;
import serverapp.isaBack.model.ReservationType;
import serverapp.isaBack.model.Unit;
import serverapp.isaBack.model.UnitType;
import serverapp.isaBack.model.User;
import serverapp.isaBack.repository.AvailablePeriodRepository;
import serverapp.isaBack.repository.BoatRepository;
import serverapp.isaBack.repository.ClientRepository;
import serverapp.isaBack.repository.CottageRepository;
import serverapp.isaBack.repository.FishingCourseRepository;
import serverapp.isaBack.repository.ReservationRepository;
import serverapp.isaBack.repository.UnitRepository;
import serverapp.isaBack.repository.UserRepository;
import serverapp.isaBack.service.interfaces.IReservationService;
import serverapp.isaBack.service.interfaces.IUserService;
import serverapp.isaBack.service.users.EmailService;
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
	private CottageRepository ctRepository;
	
	@Autowired
	private FishingCourseRepository cRepository;
	
	@Autowired
	private UnitRepository  unitRepository;
	
	@Autowired
	private EmailService emailService;
	
	private UnitMapper unitMapper= new UnitMapper();
	private ReservationMapper resMapper= new ReservationMapper();
	
	@Override	
	public List<Unit> findAllFreeBoats(Date startDate, Date endDate) {
		
		List<AvailablePeriod> availablePeriodInDateRange= new ArrayList<AvailablePeriod>();
		
		availablePeriodInDateRange= periodRepository.findAvailablePeriodInDateRange(startDate,endDate, UnitType.BOAT);
				
		List<Reservation> busyReservationsInDataRange= reservationRepository.findAllreservationsInDataRange(startDate,endDate,ReservationType.BOAT);
					
		List<Unit> boatsFree= findBoatsWithFreePeroid(availablePeriodInDateRange,busyReservationsInDataRange);
		
				
		return boatsFree;
	}
	
	@Override	
	public List<Unit> findAllFreeCottages(Date startDate, Date endDate) {
		
		List<AvailablePeriod> availablePeriodInDateRange= new ArrayList<AvailablePeriod>();
		
		availablePeriodInDateRange= periodRepository.findAvailablePeriodInDateRange(startDate,endDate,UnitType.COTTAGE);
				
		List<Reservation> busyReservationsInDataRange= reservationRepository.findAllreservationsInDataRange(startDate,endDate,ReservationType.COTTAGE);
					
		List<Unit> cottagesFree= findBoatsWithFreePeroid(availablePeriodInDateRange,busyReservationsInDataRange);
		
				
		return cottagesFree;
	}
	
	@Override	
	public List<Unit> findAllFreeCourses(Date startDate, Date endDate) {
		
		List<AvailablePeriod> availablePeriodInDateRange= new ArrayList<AvailablePeriod>();
		
		availablePeriodInDateRange= periodRepository.findAvailablePeriodInDateRange(startDate,endDate,UnitType.COURSE);
				
		List<Reservation> busyReservationsInDataRange= reservationRepository.findAllreservationsInDataRange(startDate,endDate,ReservationType.COTTAGE);
					
		List<Unit> cottagesFree= findCoursesWithFreePeroid(availablePeriodInDateRange,busyReservationsInDataRange);
		
				
		return cottagesFree;
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
	
public List<Unit> findCoursesWithFreePeroid(List<AvailablePeriod> availablePeriodInDateRange, List<Reservation> reservationInDataRange){
		
		List<Unit> coursesWithFreePeriod= new ArrayList<Unit>();
		System.out.println("ej alo bidiou");
		for (AvailablePeriod currentAP : availablePeriodInDateRange) {
				System.out.println("ej alo bidiou");
				boolean scheduled= false;			
				
				for (Reservation currentR : reservationInDataRange) {
					if(currentAP.getOwner().getId().equals(currentR.getOwner().getId())) {
						scheduled = true;
						break;
					}
					
				}
				
				if(scheduled==false)
					coursesWithFreePeriod.add(currentAP.getUnit());
			
		}
		
		
		
		return throwOutDuplicatesBoats(coursesWithFreePeriod);
		
		
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
		
		
		try {
			emailService.sendReservationNotification(reservation);
		} catch (MessagingException e) {}
	}
	
	
	
	public void anyBusyBoatReservationInDataRange(NewReservationDTO reservationRequestDTO,Date startDate, Date endDate  ){
		
		if(reservationRepository.findAllBusyReservationInDataRangeBoat(startDate,endDate,reservationRequestDTO.getUnitId()).size()>0)
			throw new IllegalArgumentException("Brod je zauzet u tom terminu.");
		
		if(!( periodRepository.findAvailablePeriodInDateRangeForBoat(startDate,endDate, reservationRequestDTO.getUnitId()).size()>0))
			throw new IllegalArgumentException("Brod nije dostupan u izabranom terminu");
		
	}
	
	

	public Reservation makeReservation(NewReservationDTO reservationRequestDTO,Date startDate, Date endDate ){
		
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
	public void makeCottageReservation(NewReservationDTO reservationDTO){
		
		Date startDate= new Date(reservationDTO.getStartDateTime());
		Date endDate= new Date(reservationDTO.getStartDateTime()+24*60*60*1000*reservationDTO.getDays());
		
		System.out.println( "startno vreme " + startDate + "   " +  " Krajnje vremee " + endDate);
		
	
		anyBusyCottageReservationInDataRange(reservationDTO,startDate,endDate);
		
		Reservation reservation= makeCtReservation(reservationDTO,startDate,endDate);
		
		reservationRepository.save(reservation);
		
		
		try {
			emailService.sendReservationNotification(reservation);
		} catch (MessagingException e) {}
	}
	
	
	public void anyBusyCottageReservationInDataRange(NewReservationDTO reservationRequestDTO,Date startDate, Date endDate  ){
		
		if(reservationRepository.findAllBusyCtReservationInDataRange(startDate,endDate,reservationRequestDTO.getUnitId()).size()>0)
			throw new IllegalArgumentException("Vikendica je zauzeta u tom terminu.");
		
		if(!( periodRepository.findAvailablePeriodInDateRangeForCottage(startDate,endDate, reservationRequestDTO.getUnitId()).size()>0))
			throw new IllegalArgumentException("Vikendica nije dostupan u izabranom terminu");
		
	}
	
	

	public Reservation makeCtReservation(NewReservationDTO reservationRequestDTO,Date startDate, Date endDate ){
		System.out.println("nj njenj");
		UUID clientId = userService.getLoggedUserId();
		Client client = clientRepository.findById(clientId).get();
	    Cottage cottage = ctRepository.findById(reservationRequestDTO.getUnitId()).get();
	    System.out.println("userrrrr");
		User owner = userRepository.findById(cottage.getOwner().getId()).get();
		Unit unit = unitRepository.findById(cottage.getId()).get();
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
		Reservation reservation= new Reservation( owner,unit, startDate, endDate,price,client, ReservationType.COTTAGE, ReservationStatus.RESERVED);
		
		isCtReservationValid(reservation);
		
		return reservation;
		
	}
	
	public void isCtReservationValid(Reservation reservation ){
		
		if (!(reservation.getStartDateTime().after(new Date())))
			throw new IllegalArgumentException("Početni datum ne može biti pre trenutnog.");
		
		if(reservationRepository.findAllCtReservationsForClientInDataRange(reservation.getStartDateTime(), reservation.getEndDateTime(), reservation.getClient().getId()).size() > 0)
				throw new IllegalArgumentException("Klijent ima već rezervaciju za vikendicu u izabranom terminu");
	}
	
	
	@Override
	public void makeCourseReservation(NewReservationDTO reservationDTO){
		
		Date startDate= new Date(reservationDTO.getStartDateTime());
		Date endDate= new Date(reservationDTO.getStartDateTime()+24*60*60*1000*reservationDTO.getDays());
		
		System.out.println( "startno vreme " + startDate + "   " +  " Krajnje vremee " + endDate);
		
	
		//anyBusyCourseReservationInDataRange(reservationDTO,startDate,endDate);
		
		Reservation reservation= makeCReservation(reservationDTO,startDate,endDate);
		
		reservationRepository.save(reservation);
		
		
		try {
			emailService.sendReservationNotification(reservation);
		} catch (MessagingException e) {}
	}
	
	
	public void anyBusyCourseReservationInDataRange(NewReservationDTO reservationRequestDTO,Date startDate, Date endDate,UUID userId){
		
		if(reservationRepository.findAllBusyCReservationInDataRange(startDate,endDate,userId).size()>0)
			throw new IllegalArgumentException("Instruktor je zauzet u tom terminu.");
		
		/*if(!( periodRepository.findAvailablePeriodInDateRangeForCourse(startDate,endDate,reservationRequestDTO.getUnitId()).size()>0));
			throw new IllegalArgumentException("Kurs nije dostupan u izabranom terminu");*/
	}
	
	

	public Reservation makeCReservation(NewReservationDTO reservationRequestDTO,Date startDate, Date endDate ){
		System.out.println("nj njenj");
		UUID clientId = userService.getLoggedUserId();
		Client client = clientRepository.findById(clientId).get();
	    FishingCourse course = cRepository.findById(reservationRequestDTO.getUnitId()).get();
	    System.out.println("userrrrr");
	   
		User owner = userRepository.findById(course.getInstructor().getId()).get();
		 System.out.println("sas" + owner.getId());
		 anyBusyCourseReservationInDataRange(reservationRequestDTO,startDate,endDate,owner.getId());
		Unit unit = unitRepository.findById(course.getId()).get();
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
		Reservation reservation= new Reservation( owner,unit, startDate, endDate,price,client, ReservationType.COURSE, ReservationStatus.RESERVED);
		
		isCtReservationValid(reservation);
		
		return reservation;
		
	}
	
	public void isCReservationValid(Reservation reservation ){
		
		if (!(reservation.getStartDateTime().after(new Date())))
			throw new IllegalArgumentException("Početni datum ne može biti pre trenutnog.");
		
		if(reservationRepository.findAllCReservationsForClientInDataRange(reservation.getStartDateTime(), reservation.getEndDateTime(), reservation.getClient().getId()).size() > 0)
				throw new IllegalArgumentException("Klijent ima već rezervaciju za vikendicu u izabranom terminu");
	}
	
	
	@Override

	public void cancelReservation(UUID reservationId) {
		
		Reservation reservation = reservationRepository.findById(reservationId).get();
		

		if (reservation==null)
			throw new EntityNotFoundException("Rezervacija ne postoji");
		Date currentDate=new Date();
		Date availableCancel=new Date(currentDate.getTime()+24*60*60*1000*3);
				if (reservation.getStartDateTime().before(availableCancel))
			throw new IllegalArgumentException("Nije moguće otkazati rezervaciju ranije od 3 dana.");
		
		reservation.setReservationStatus(ReservationStatus.CANCELED);
	
		
		reservationRepository.save(reservation);
		
		
	}
	
	@Override
	public void fastReservation(UUID reservationId) {
		
		Reservation reservation = reservationRepository.findById(reservationId).get();
		UUID clientId = userService.getLoggedUserId();
		Client client = clientRepository.findById(clientId).get();

		if (reservation==null)
			throw new EntityNotFoundException("Rezervacija ne postoji");
		
	
		reservation.setReservationStatus(ReservationStatus.RESERVED);
	    reservation.setClient(client);
		
		reservationRepository.save(reservation);
		
		try {
			emailService.sendReservationNotification(reservation);
		} catch (MessagingException e) {}
		
		
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
		System.out.println("cacacacaca");
		UUID logedClientID= userService.getLoggedUserId();
		System.out.println("nje " + logedClientID);
		System.out.println("nje " + reservationType);
		List<Reservation> reservations = reservationRepository.findAllHistoryClientsReservation(logedClientID, reservationType); 
		System.out.println("cacabjbjbj");
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
	public List<UnspecifiedDTO<ReservationDTO>> findAllActionReservationClient(ReservationType reservationType) throws IOException {
		System.out.println("ossss");
		System.err.println(reservationType);
		List<Reservation> reservations=new ArrayList<Reservation>();
		reservations = reservationRepository.findAllActionReservation(reservationType); 
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
