package sprint.sprint.support;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import sprint.sprint.model.Sprint;
import sprint.sprint.model.Zadatak;
import sprint.sprint.web.dto.SprintDto;
import sprint.sprint.web.dto.ZadatakDto;

@Component
public class SprintToSprintDto implements Converter<Sprint, SprintDto> {
	
	@Autowired
	private ZadatakToZadatakDto toDto;

	@Override
	public SprintDto convert(Sprint source) {
		SprintDto dto = new SprintDto();
		dto.setId(source.getId());
		dto.setIme(source.getIme());
        dto.setUkupnoBodova(source.getUkupnoBodova());
        
        List<Zadatak>zadaci = new ArrayList<>(source.getZadaci());
        dto.setZadaci(new HashSet<>(toDto.convert(zadaci)));
		return dto;
	}
	
	public List<SprintDto> convert (List<Sprint> sprintovi){
		
		List<SprintDto> sprintDtos = new ArrayList<>();
		for(Sprint s : sprintovi) {
			sprintDtos.add(convert(s));
		}
		
		return sprintDtos;
		   
	}

	

}
