package serverapp.isaBack.mappers.entities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import serverapp.isaBack.DTO.entities.BoatDTO;
import serverapp.isaBack.DTO.entities.CottageDTO;
import serverapp.isaBack.DTO.entities.CourseDTO;
import serverapp.isaBack.DTO.entities.OtherTagDTO;
import serverapp.isaBack.DTO.entities.UnitDTO;
import serverapp.isaBack.model.Boat;
import serverapp.isaBack.model.Cottage;
import serverapp.isaBack.model.FishingCourse;
import serverapp.isaBack.model.OtherTag;
import serverapp.isaBack.model.Unit;
import serverapp.isaBack.model.User;
import serverapp.isaBack.unspecifiedDTO.UnspecifiedDTO;

@Service
public class UnitMapper {

	
	public UnspecifiedDTO<BoatDTO> MapBoatToBoatDTO(Boat boat,double avgGrade) throws IOException{
		if(boat == null) 
			throw new IllegalArgumentException();
		String basePath = new File("").getAbsolutePath();
		System.out.println("putanjaaaa "+basePath);
		byte[] imageBytes=null;
		if(!boat.getAlbum().getImages().isEmpty()) {
			System.out.println("slikaaa "+ boat.getAlbum().getImages().get(0).getFileName());
			imageBytes = extractBytes(basePath+"\\src\\main\\java\\serverapp\\isaBack\\images\\" + boat.getAlbum().getImages().get(0).getFileName());
		}else {
			imageBytes = extractBytes(basePath+"\\src\\main\\java\\serverapp\\isaBack\\images\\nemaslike.png");
		}
		System.out.println("slikaaa "+ boat.getAlbum().getImages().get(0).getFileName());
		
 		List<UnspecifiedDTO<OtherTagDTO>> tags= MapTagToTagDTO(boat.getServices());
 		//tags.add(new UnspecifiedDTO<OtherTagDTO>(UUID.randomUUID(),new OtherTagDTO("osnovna cena",boat.getPrice())));
		
		return new UnspecifiedDTO<BoatDTO> (boat.getId(), new BoatDTO( boat.getName(), boat.getAddress(),
				boat.getDescription(),imageBytes,tags,avgGrade,boat.getPrice()));
	}
	
	public UnspecifiedDTO<CottageDTO> MapCottageToCottageDTO(Cottage cottage,double avgGrade) throws IOException{
		if(cottage == null) 
			throw new IllegalArgumentException();
		String basePath = new File("").getAbsolutePath();
		System.out.println("putanjaaaa "+basePath);
		byte[] imageBytes=null;
		if(!cottage.getAlbum().getImages().isEmpty()) {
			System.out.println("slikaaa "+ cottage.getAlbum().getImages().get(0).getFileName());
			imageBytes = extractBytes(basePath+"\\src\\main\\java\\serverapp\\isaBack\\images\\" + cottage.getAlbum().getImages().get(0).getFileName());
		}else {
			imageBytes = extractBytes(basePath+"\\src\\main\\java\\serverapp\\isaBack\\images\\nemaslike.png");
		}
			List<UnspecifiedDTO<OtherTagDTO>> tags= MapTagToTagDTO(cottage.getServices());
 		//tags.add(new UnspecifiedDTO<OtherTagDTO>(UUID.randomUUID(),new OtherTagDTO("osnovna cena",cottage.getPrice())));
		
		return new UnspecifiedDTO<CottageDTO> (cottage.getId(), new CottageDTO( cottage.getName(), cottage.getAddress(),
				cottage.getDescription(),imageBytes,tags,avgGrade,cottage.getPrice()));
	}
	
	
	public UnspecifiedDTO<CourseDTO> MapCourseToCourseDTO(FishingCourse course,double avgGrade) throws IOException{
		if(course == null) 
			throw new IllegalArgumentException();
		String basePath = new File("").getAbsolutePath();
		System.out.println("putanjaaaa "+basePath);
		byte[] imageBytes=null;
		if(!course.getAlbum().getImages().isEmpty()) {
			System.out.println("slikaaa "+ course.getAlbum().getImages().get(0).getFileName());
			imageBytes = extractBytes(basePath+"\\src\\main\\java\\serverapp\\isaBack\\images\\" + course.getAlbum().getImages().get(0).getFileName());
		}else {
			imageBytes = extractBytes(basePath+"\\src\\main\\java\\serverapp\\isaBack\\images\\nemaslike.png");
		}
			List<UnspecifiedDTO<OtherTagDTO>> tags= MapTagToTagDTO(course.getServices());
 		//tags.add(new UnspecifiedDTO<OtherTagDTO>(UUID.randomUUID(),new OtherTagDTO("osnovna cena",course.getPrice())));
		
		return new UnspecifiedDTO<CourseDTO> (course.getId(), new CourseDTO( course.getName(), course.getAddress(),
				course.getDescription(),imageBytes,tags,avgGrade,course.getInstructor().toString(),course.getBiography(),course.getPrice()));
	}
	
	
	
	public  List<UnspecifiedDTO<OtherTagDTO>> MapTagToTagDTO(List<OtherTag> tags){
		List<UnspecifiedDTO<OtherTagDTO>> retVal = new ArrayList<UnspecifiedDTO<OtherTagDTO>>();
		
		for (OtherTag tag : tags) {
			
			retVal.add(new UnspecifiedDTO<OtherTagDTO>(tag.getId(),
					   new OtherTagDTO(tag.getName(),tag.getPrice())));
			
		}
		
		return retVal;
		
	}
	
	public UnspecifiedDTO<UnitDTO> mapUnitToUnitDTO(Unit unit) throws IOException{
		if(unit == null) 
			throw new IllegalArgumentException();
		String basePath = new File("").getAbsolutePath();
		System.out.println("putanjaaaa "+basePath);
		byte[] imageBytes=null;
		if(!unit.getAlbum().getImages().isEmpty()) {
			System.out.println("slikaaa "+ unit.getAlbum().getImages().get(0).getFileName());
			imageBytes = extractBytes(basePath+"\\src\\main\\java\\serverapp\\isaBack\\images\\" + unit.getAlbum().getImages().get(0).getFileName());
		}else {
			System.out.println("iiiiiiiiiinjee");
			imageBytes = extractBytes(basePath+"\\src\\main\\java\\serverapp\\isaBack\\images\\nemaslike.png");
		}
		//System.out.println("slikaaa "+ unit.getAlbum().getImages().get(0).getFileName());
		
 		
 		
		
		return new UnspecifiedDTO<UnitDTO> (unit.getId(), new UnitDTO( unit.getName(),imageBytes,unit.getPercentOfCancel()));
	}
	
	private byte[] extractBytes (String ImageName) throws IOException{
		File fi = new File(ImageName);
	
		byte[] fileContent = Files.readAllBytes(fi.toPath());
		System.out.println(fileContent);
	    return fileContent;
		}

}
