package sprint.sprint.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sprint.sprint.model.Stanje;
import sprint.sprint.service.StanjeService;
import sprint.sprint.support.StanjeToStanjeDto;
import sprint.sprint.web.dto.StanjeDto;

@RestController
@RequestMapping(value = "/api/stanja", produces = MediaType.APPLICATION_JSON_VALUE)
public class StanjeController {
	
	@Autowired
	private StanjeService stanjeService;
	
	@Autowired
	private StanjeToStanjeDto toDto;
  
	
	@GetMapping()
	public ResponseEntity<List<StanjeDto>> getAll(){
		List<Stanje> stanja = stanjeService.findAll();
		
		return new ResponseEntity<>(toDto.convert(stanja), HttpStatus.OK);
	}
}
