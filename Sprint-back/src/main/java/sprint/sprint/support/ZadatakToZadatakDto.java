package sprint.sprint.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import sprint.sprint.model.Zadatak;
import sprint.sprint.web.dto.ZadatakDto;

@Component
public class ZadatakToZadatakDto implements Converter<Zadatak, ZadatakDto>{
	

	@Override
	public ZadatakDto convert(Zadatak source) {
		ZadatakDto zadatakDto = new ZadatakDto();
		zadatakDto.setId(source.getId());
		zadatakDto.setBodovi(source.getBodovi());
		zadatakDto.setImeZadataka(source.getImeZadataka());
		zadatakDto.setZaduzeni(source.getZaduzeni());
		
		zadatakDto.setSprintId(source.getSprint().getId());
		zadatakDto.setStanjeId(source.getStanje().getId());
		
//		zadatakDto.setSprintId(source.getSprint());
//		zadatakDto.setSprintNaziv(source.getSprint());
//		
//		zadatakDto.setStanjeId(source.getStanje());
//		zadatakDto.setStanjeNaziv(source.getStanje());
		
		return zadatakDto;
	}
	
	public List<ZadatakDto>convert(List<Zadatak> zadaci) {
		List<ZadatakDto>zadatakDtos = new ArrayList<>();
		for(Zadatak z: zadaci) {
			zadatakDtos.add(convert(z));
		}
		return zadatakDtos;
	}

}
