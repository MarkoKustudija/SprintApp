package sprint.sprint.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sprint.sprint.model.Sprint;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long>{

	Sprint findOneById(Long id);


//	List<Sprint> findByIdS(List<Long> ids);
	
	

}
