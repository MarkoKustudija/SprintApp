package sprint.sprint.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sprint.sprint.model.Sprint;
import sprint.sprint.service.SprintService;
import sprint.sprint.support.SprintToSprintDto;
import sprint.sprint.web.dto.SprintDto;


@RestController
@RequestMapping(value = "/api/sprintovi", produces = MediaType.APPLICATION_JSON_VALUE)
public class SprintController {
	
	@Autowired
	private SprintService sprintService;
	
	@Autowired
	private SprintToSprintDto toDto;
	
	@GetMapping
	public ResponseEntity <List<SprintDto>> getAll (){
		
		List<Sprint> sprintovi = sprintService.findAll();
		
		return new ResponseEntity<>(toDto.convert(sprintovi), HttpStatus.OK);
		
	}

}
