package sprint.sprint.support;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import sprint.sprint.model.Stanje;
import sprint.sprint.model.Zadatak;
import sprint.sprint.web.dto.StanjeDto;

@Component
public class StanjeToStanjeDto implements Converter<Stanje, StanjeDto> {
	
	@Autowired
	private ZadatakToZadatakDto toDto;

	@Override
	public StanjeDto convert(Stanje source) {
		StanjeDto stanjeDto = new StanjeDto();
		stanjeDto.setId(source.getId());
		stanjeDto.setIme(source.getIme());
		
   		 List<Zadatak>zadaci = new ArrayList<>(source.getZadaci());
         stanjeDto.setZadaci(new HashSet<>(toDto.convert(zadaci)));
			
	         return stanjeDto;
	
	}
	
	public List<StanjeDto> convert(List<Stanje> stanja){
		List<StanjeDto>stanjeDtos = new ArrayList<>();
		for(Stanje s: stanja) {
			stanjeDtos.add(convert(s));
		}
		
		return stanjeDtos;
	}
	

}
