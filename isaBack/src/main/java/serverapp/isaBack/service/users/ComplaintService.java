package serverapp.isaBack.service.users;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import serverapp.isaBack.DTO.users.ComplaintUnitDTO;
import serverapp.isaBack.DTO.users.ComplaintUserDTO;
import serverapp.isaBack.model.Boat;
import serverapp.isaBack.model.Client;
import serverapp.isaBack.model.ComplaintUnit;
import serverapp.isaBack.model.ComplaintUser;
import serverapp.isaBack.model.Cottage;
import serverapp.isaBack.model.FishingCourse;
import serverapp.isaBack.model.Unit;
import serverapp.isaBack.model.User;
import serverapp.isaBack.repository.BoatOwnerRepository;
import serverapp.isaBack.repository.BoatRepository;
import serverapp.isaBack.repository.ClientRepository;
import serverapp.isaBack.repository.ComplaintUnitRepository;
import serverapp.isaBack.repository.ComplaintUserRepository;
import serverapp.isaBack.repository.CottageOwnerRepository;
import serverapp.isaBack.repository.CottageRepository;
import serverapp.isaBack.repository.FishingCourseRepository;
import serverapp.isaBack.repository.UnitRepository;
import serverapp.isaBack.repository.UserRepository;
import serverapp.isaBack.service.interfaces.IComplaintService;
import serverapp.isaBack.unspecifiedDTO.UnspecifiedDTO;


@Service
public class ComplaintService implements IComplaintService{
	
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private UnitRepository unitRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BoatOwnerRepository bowRepository;
	
	@Autowired
	private BoatRepository bRepository;
	
	@Autowired
	private CottageRepository ctRepository;
	
	@Autowired
	private FishingCourseRepository cuRepository;
	
	@Autowired
	private CottageOwnerRepository cowRepository;
	
	@Autowired
	private ComplaintUserRepository complaintUserRepository;
	
	@Autowired
	private ComplaintUnitRepository complaintUnitRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Override
	public UUID createComplaintUser(ComplaintUserDTO entityDTO){
		
		
		UUID clientId = userService.getLoggedUserId();
		
		Client client = clientRepository.findById(clientId).get();
		
		System.out.println(client.getName());
		
			
		User user = userRepository.findById(entityDTO.getOwnerId()).get();
		
		String profession ="";
		if(bowRepository.existsById(user.getId())) {
			profession="Vlasnik broda";
		}else if(cowRepository.existsById(user.getId())){
			profession="Vlasnik vikendice";
		}else {
			profession="Instruktor pecanja";
		}
		
		ComplaintUser complaintUser = new ComplaintUser(user,client, entityDTO.getText(), user.toString(), profession, client.getEmail(), true);
		complaintUserRepository.save(complaintUser);
		
		return complaintUser.getId();
		
	}
	
	@Override
	public UUID createComplaintUnit(ComplaintUnitDTO entityDTO){
		
		UUID clientId = userService.getLoggedUserId();
		
		Client client = clientRepository.findById(clientId).get();
		
		System.out.println(client.getName());
		
		
		
		Unit unit = unitRepository.findById(entityDTO.getUnitId()).get();
		String userEmail="";
		if(bRepository.existsById(entityDTO.getUnitId())) {
			Boat b = bRepository.findById(entityDTO.getUnitId()).get();
			userEmail=b.getOwner().getEmail();
		}else if(ctRepository.existsById(entityDTO.getUnitId())){
			Cottage c = ctRepository.findById(entityDTO.getUnitId()).get();
			userEmail=c.getOwner().getEmail();
		}else if(cuRepository.existsById(entityDTO.getUnitId())){
			 FishingCourse course = cuRepository.findById(entityDTO.getUnitId()).get();
			 userEmail=course.getInstructor().getEmail();
		}
		
		ComplaintUnit complaintUnit = new ComplaintUnit(unit,client,entityDTO.getText(),unit.getName(),client.getEmail(),userEmail);
		
		complaintUnitRepository.save(complaintUnit);
		
		return complaintUnit.getId();
		
	}
	@Override
	public List<UnspecifiedDTO<ComplaintUserDTO>> findAllUserComplaints(){
		
		List<UnspecifiedDTO<ComplaintUserDTO>> ownerList = new ArrayList<UnspecifiedDTO<ComplaintUserDTO>>();
		System.out.println("1");
		for (ComplaintUser owner : complaintUserRepository.findAll()) {
			if(owner.isActive()==true) {
			System.out.println(owner.getDate());
			ComplaintUserDTO userDTO = new ComplaintUserDTO(owner.getId(), owner.getDate(), owner.getText(), owner.getUserName(),owner.getClient().toString(), owner.getProfession(),owner.getReply(), owner.getEmail());
			ownerList.add(new UnspecifiedDTO<ComplaintUserDTO>(owner.getId(),userDTO));
				}
			}
		
		return ownerList;
	}
	

	
	@Override
	public List<UnspecifiedDTO<ComplaintUnitDTO>> findAllUnitComplaints(){
		
		List<UnspecifiedDTO<ComplaintUnitDTO>> unitList = new ArrayList<UnspecifiedDTO<ComplaintUnitDTO>>();
		System.out.println("1");
		for (ComplaintUnit unit : complaintUnitRepository.findAll()) {
			
			if(unit.isActive()==true) {
				ComplaintUnitDTO unitDTO = new ComplaintUnitDTO(unit.getId(),unit.getDate(),unit.getText(),unit.getReply(),unit.getEmail(),unit.getUnitName(),unit.getClient().toString());
				unitList.add(new UnspecifiedDTO<ComplaintUnitDTO>(unit.getId(),unitDTO));
				}
			}
		
		return unitList;
	}
	
	
	@Override
    public UUID replyToUnitComplaint(UUID complaintId,String reply,String replyToUser){
		System.out.println("is " + complaintId);
	ComplaintUnit complaintUnit = complaintUnitRepository.findById(complaintId).get();
	System.out.println("njnjnjne veslaa");
	complaintUnit.setReply(reply);
	complaintUnit.setReplyToUser(replyToUser);
	complaintUnit.setActive(false);
	
	complaintUnitRepository.save(complaintUnit);
	
	try {
		emailService.sendEmailforReplyedComplaint(complaintUnit);
		emailService.sendEmailforReplyedComplaintToOwner(complaintUnit);
	} catch (MailException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	return complaintUnit.getId();
}


@Override
public UUID replyToUserComplaint(UUID complaintId,String reply,String replyToUser){
	System.out.println("nje " + complaintId);
	ComplaintUser complaintUser = complaintUserRepository.findById(complaintId).get();
	
	complaintUser.setReply(reply);
	complaintUser.setActive(false);
	complaintUser.setReplyToUser(replyToUser);
	complaintUserRepository.save(complaintUser);
	

	try {
		emailService.sendEmailforReplyedComplaint(complaintUser);
		emailService.sendEmailforReplyedComplaintToOwner(complaintUser);
	} catch (MailException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return complaintUser.getId();
}
	


}