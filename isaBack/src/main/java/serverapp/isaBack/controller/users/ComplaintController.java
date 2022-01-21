package serverapp.isaBack.controller.users;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import serverapp.isaBack.DTO.users.ComplaintUnitDTO;
import serverapp.isaBack.DTO.users.ComplaintUserDTO;
import serverapp.isaBack.service.interfaces.IComplaintService;
import serverapp.isaBack.unspecifiedDTO.UnspecifiedDTO;




@RestController
@RequestMapping(value = "complaint")
public class ComplaintController {
	
	
	
	@Autowired
	private IComplaintService complaintService;
	
	/*@Autowired
	private IAppointmentService appointmentService;*/
	
	
	
	//@PreAuthorize("hasRole('PATIENT')")
	@PostMapping("/user")
	public ResponseEntity<UUID> createComplaintUser(@RequestBody ComplaintUserDTO complaintUserDTO) {
		System.out.println("usao u zalbu");
	//	System.out.println(ComplaintUserDTO.getStaffName());
		
		try {
			return new ResponseEntity<UUID>(complaintService.createComplaintUser(complaintUserDTO),HttpStatus.CREATED);
		
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//@PreAuthorize("hasRole('PATIENT')")
	@PostMapping("/unit")
	public ResponseEntity<UUID> createComplaintUnit(@RequestBody ComplaintUnitDTO complaintUnitDTO) {
		System.out.println("usao u zalbe entiteta");
	//	System.out.println(appointmentService.canPatientReportPharmacy(complaintPharmacyDTO.getPharmacyId()));
		//if(!appointmentService.canPatientReportPharmacy(complaintPharmacyDTO.getPharmacyId())) {
		//	return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		//}
		
		try {
			return new ResponseEntity<UUID>(complaintService.createComplaintUnit(complaintUnitDTO),HttpStatus.CREATED);
		
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PreAuthorize("hasRole('SYSADMIN')")
	@GetMapping("/getComplaintsUser")
	public ResponseEntity<List<UnspecifiedDTO<ComplaintUserDTO>>> getComplaintsUser() {
		System.out.println("usao u zalbe za vlasnike");
		
		
		try {
			return new ResponseEntity<>(complaintService.findAllUserComplaints(),HttpStatus.CREATED);
		
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@PreAuthorize("hasRole('SYSADMIN')")
	@GetMapping("/getComplaintsUnit")
	public ResponseEntity<List<UnspecifiedDTO<ComplaintUnitDTO>>> getComplaintsUnit() {
		System.out.println("usao ussssssss zalbe");
		
		
		try {
			return new ResponseEntity<>(complaintService.findAllUnitComplaints(),HttpStatus.CREATED);
		
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
 
	
	

}

