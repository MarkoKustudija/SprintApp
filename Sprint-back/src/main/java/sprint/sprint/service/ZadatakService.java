package sprint.sprint.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import sprint.sprint.model.Zadatak;
import sprint.sprint.web.dto.ZadatakDto;




public interface ZadatakService {
	
    Zadatak findOne(Long id);
	
	List<Zadatak> findAll();
	
	Page<Zadatak> findAll(Pageable pageable);

	//List<Zadatak> find(List<Long> idS);

	Zadatak save(Zadatak zadatak);
		
	Zadatak update(Zadatak zadatak);

	Zadatak delete(Long id);

	Page<Zadatak> pretraga(Long id, int pageNum);
	
	// ove 2 funkcije dole sam dopisao zbog metoda delete i save u Jpa Zadatak, da bi obrisao podatke kasnije iz baze,
	// odnosno da podaci o Zadatku ne bi ostali vezani za Stanja i Sprintove 
	
    Zadatak save(ZadatakDto zadatkDto);
	
	Optional<Zadatak> one(Long id);


	

}
