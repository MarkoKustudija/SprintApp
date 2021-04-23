package sprint.sprint.support;

//import java.util.HashSet;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//
//import sprint.sprint.model.Stanje;
//import sprint.sprint.model.Zadatak;
//import sprint.sprint.service.StanjeService;
//import sprint.sprint.service.ZadatakService;
//import sprint.sprint.web.dto.StanjeDto;
//import sprint.sprint.web.dto.ZadatakDto;
//
//@Component
//public class StanjeDtoToStanje implements Converter<StanjeDto, Stanje>{
//	
//	@Autowired
//	StanjeService stanjeService;
//	
//	@Autowired
//	private ZadatakService zadatakService;
//
//	@Override
//	public Stanje convert(StanjeDto source) {
//		Stanje stanje = null;
//		
//		if(source.getId() == null) {
//			stanje = new Stanje();
//			
//		} else {
//			stanje = stanjeService.findOne(source.getId());
//		}
//		if(stanje != null) {
//			stanje.setId(source.getId());
//			stanje.setIme(source.getIme());
//	
////			List<Long> idZadatka = source.getZadaci().stream().map(ZadatakDto::getId).collect(Collectors.toList());
////			List<Zadatak> zadaci = zadatakService.findAll(idZadatka);
////			stanje.setZadaci(new HashSet<>(zadaci));
//		}
//		return stanje;
//	}
//	
//
//}
