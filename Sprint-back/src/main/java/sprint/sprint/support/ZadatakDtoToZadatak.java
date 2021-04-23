package sprint.sprint.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import sprint.sprint.model.Zadatak;
import sprint.sprint.service.SprintService;
import sprint.sprint.service.StanjeService;
import sprint.sprint.service.ZadatakService;
import sprint.sprint.web.dto.ZadatakDto;

@Component
public class ZadatakDtoToZadatak  implements Converter<ZadatakDto, Zadatak>{
	
	@Autowired
	ZadatakService zadatakService;

	@Autowired
	private SprintService sprintService;
	
	@Autowired 
	private StanjeService stanjeService;
	
	@Override
	public Zadatak convert(ZadatakDto source) {
		Zadatak zadatak = null;
		if(source.getId() == null) {
			zadatak = new Zadatak();
		}else {
			zadatak = zadatakService.findOne(source.getId());
		}
		if(zadatak!=null) {
			zadatak.setBodovi(source.getBodovi());
			zadatak.setId(source.getId());
			zadatak.setImeZadataka(source.getImeZadataka());
			zadatak.setZaduzeni(source.getZaduzeni());
			zadatak.setSprint(sprintService.findeOne(source.getSprintId()));
			zadatak.setStanje(stanjeService.findOne(source.getStanjeId()));
			
//			
//			zadatak.setSprint(source.getSprintId());
//			zadatak.setSprint(source.getSprintNaziv());
//			
//			zadatak.setStanje(source.getStanjeId());
//			zadatak.setStanje(source.getStanjeNaziv());
		}
		
		return zadatak;
	}
	

}
