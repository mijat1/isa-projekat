package serverapp.isaBack.mappers.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import serverapp.isaBack.DTO.entities.UnitDTO;
import serverapp.isaBack.DTO.reservation.ReservationDTO;
import serverapp.isaBack.DTO.users.UserNameDTO;
import serverapp.isaBack.model.Reservation;
import serverapp.isaBack.model.Unit;
import serverapp.isaBack.model.User;
import serverapp.isaBack.unspecifiedDTO.UnspecifiedDTO;

@Service
public class ReservationMapper {
	

	
	public List<UnspecifiedDTO<ReservationDTO>> MapReservationToReservationDTO(List<Reservation> reservations,
			List<UnspecifiedDTO<UnitDTO>> units,List<UnspecifiedDTO<UserNameDTO>> owners){
		
		 List<UnspecifiedDTO<ReservationDTO>> retVal= new ArrayList<UnspecifiedDTO<ReservationDTO>>();
		 
		 reservations.forEach((currentReservation) -> retVal.add(MapReservationToDTOs(currentReservation, findMatchingUnit(currentReservation.getUnit().getId(), units),findMatchingOwner(currentReservation.getOwner().getId(), owners))));
		
		
		
		return retVal;
		
		
	}
	
	
		
	
	public UnspecifiedDTO<ReservationDTO> MapReservationToDTOs(Reservation reservation,UnspecifiedDTO<UnitDTO> unit,UnspecifiedDTO<UserNameDTO> owner){
		
		return new UnspecifiedDTO<ReservationDTO>(reservation.getId(), 
				new ReservationDTO(unit,owner,reservation.getStartDateTime(),reservation.getEndDateTime(),reservation.getPrice(),reservation.getActionPrice()));
		
	}
	
	public UnspecifiedDTO<UnitDTO> findMatchingUnit(UUID unitId,List<UnspecifiedDTO<UnitDTO>> units){
		
		for (UnspecifiedDTO<UnitDTO> current : units) {
			if(current.Id.equals(unitId))
				return current;
		}
		
		return null;
	
	}
	
	public UnspecifiedDTO<UserNameDTO> findMatchingOwner(UUID ownnerId,List<UnspecifiedDTO<UserNameDTO>> owners){
		
		for (UnspecifiedDTO<UserNameDTO> current : owners) {
			if(current.Id.equals(ownnerId))
				return current;
		}
		
		return null;
	}
	
	
}
