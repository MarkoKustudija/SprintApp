package sprint.sprint.service;

import java.util.List;

import sprint.sprint.model.Stanje;

public interface StanjeService {
	
	Stanje findOne(Long id);
	
	List<Stanje> findAll();

}
