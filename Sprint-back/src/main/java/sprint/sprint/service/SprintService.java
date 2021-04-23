package sprint.sprint.service;

import java.util.List;



import sprint.sprint.model.Sprint;

public interface SprintService {
	
	Sprint findeOne(Long id);
	
	List<Sprint> findAll();
	
	//List<Sprint> find(List<Long> ids);

}
