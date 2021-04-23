package sprint.sprint.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sprint.sprint.model.Zadatak;

@Repository
public interface ZadatakRepository extends JpaRepository<Zadatak, Long>{
	
	Zadatak findOneById(Long id);

	//List<Zadatak> findByIdS(List<Long> ids);

	//Optional<Zadatak> findOne(Long id);
	
	@Query("SELECT z FROM Zadatak z WHERE : zadatakId = NULL OR z.id = :zadatakId")
	Page<Zadatak> pretraga(@Param("zadatakId") Long id, Pageable pageable);

	

	
	
	

}
