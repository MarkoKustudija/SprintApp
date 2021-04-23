package sprint.sprint.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sprint.sprint.model.Stanje;
import sprint.sprint.repository.StanjeRepository;
import sprint.sprint.service.StanjeService;

@Service
public class JpaStanjeService implements StanjeService{
	
	@Autowired
	private StanjeRepository stanjeRepository;

	@Override
	public Stanje findOne(Long id) {
		// TODO Auto-generated method stub
		return stanjeRepository.findOneById(id);
	}

	@Override
	public List<Stanje> findAll() {
		// TODO Auto-generated method stub
		return stanjeRepository.findAll();
	}
	
	

}
