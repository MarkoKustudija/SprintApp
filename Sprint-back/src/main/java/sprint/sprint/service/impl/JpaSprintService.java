package sprint.sprint.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sprint.sprint.model.Sprint;
import sprint.sprint.repository.SprintRepository;
import sprint.sprint.service.SprintService;

@Service
public class JpaSprintService implements SprintService {
	
	@Autowired
	private SprintRepository sprintRepository;

	@Override
	public Sprint findeOne(Long id) {
		// TODO Auto-generated method stub
		return sprintRepository.findOneById(id);
	}

	@Override
	public List<Sprint> findAll() {
		// TODO Auto-generated method stub
		return sprintRepository.findAll();
	}

	
//	@Override
//	public List<Sprint> find(List<Long> ids) {
//		// TODO Auto-generated method stub
//		return sprintRepository.findByIdS(ids);
//	}
	

}
