package serverapp.isaBack.service.interfaces;

import java.util.List;
import java.util.UUID;

import serverapp.isaBack.DTO.users.ComplaintUnitDTO;
import serverapp.isaBack.DTO.users.ComplaintUserDTO;
import serverapp.isaBack.unspecifiedDTO.UnspecifiedDTO;

public interface IComplaintService {
	public UUID createComplaintUser(ComplaintUserDTO entityDTO);
	public UUID createComplaintUnit(ComplaintUnitDTO entityDTO);
	public List<UnspecifiedDTO<ComplaintUserDTO>> findAllUserComplaints();
	public List<UnspecifiedDTO<ComplaintUnitDTO>> findAllUnitComplaints();

	
}
