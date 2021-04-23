package sprint.sprint.support;

//import java.util.HashSet;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//
//import sprint.sprint.model.Sprint;
//import sprint.sprint.model.Zadatak;
//import sprint.sprint.service.SprintService;
//import sprint.sprint.service.ZadatakService;
//import sprint.sprint.web.dto.SprintDto;
//import sprint.sprint.web.dto.ZadatakDto;
//
//@Component
//public class SprintDtoToSprint implements Converter<SprintDto, Sprint>{
//	
//   @Autowired
//   private SprintService sprintService;
//   
//   @Autowired
//   private ZadatakService zadatakService;
//	
//
//	@Override
//	public Sprint convert(SprintDto source) {
//		
//	
//		Sprint sprint = null;
//		
//		if(source.getId() == null) {
//			sprint = new Sprint();
//		} else {
//		sprint = sprintService.findeOne(source.getId());
//	}
//		if(sprint != null) {
//			sprint.setId(source.getId());
//			sprint.setIme(source.getIme());
//			sprint.setUkupnoBodova(source.getUkupnoBodova());
//			
////			List<Long> idZadatka = source.getZadaci().stream().map(ZadatakDto::getId).collect(Collectors.toList());
////			List<Zadatak> zadaci = zadatakService.findAll(idZadatka);
////			sprint.setZadaci(new HashSet<>(zadaci));
//			
//		}
//		return sprint;
//}
//	
//}