package sprint.sprint.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sprint.sprint.model.Sprint;
import sprint.sprint.model.Stanje;
import sprint.sprint.model.Zadatak;
import sprint.sprint.repository.SprintRepository;
import sprint.sprint.repository.StanjeRepository;
import sprint.sprint.repository.ZadatakRepository;
import sprint.sprint.service.ZadatakService;
import sprint.sprint.support.ZadatakDtoToZadatak;
import sprint.sprint.support.ZadatakToZadatakDto;
import sprint.sprint.web.dto.ZadatakDto;

@Service
public class JpaZadatakService implements ZadatakService{
	
	@Autowired
	private ZadatakDtoToZadatak toZadatak;
	
	@Autowired
	private ZadatakRepository zadatakRepository;
	
	@Autowired
	private StanjeRepository stanjeRepository;
	
	@Autowired SprintRepository sprintRepository;

	@Override
	public Zadatak findOne(Long id) {
		return zadatakRepository.findOneById(id);
	}

	@Override
	public List<Zadatak> findAll() { 
		return zadatakRepository.findAll();
	}

	@Override
	public Page<Zadatak> findAll(Pageable pageable) {
		return zadatakRepository.findAll(pageable);
	}
	

//	@Override
//	public List<Zadatak> find(List<Long> idS) {
//		return zadatakRepository.findByIdS(idS);
//	}

	@Override
	public Zadatak save(Zadatak zadatak) {
		return zadatakRepository.save(zadatak);
	}

	@Override
	public Zadatak update(Zadatak zadatak) {
		return zadatakRepository.save(zadatak);
	}

	@Override
	@Transactional
	public Zadatak delete(Long id) {
		Optional<Zadatak> zadatakOptional = zadatakRepository.findById(id);
		if(zadatakOptional.isPresent()) {
	
			Zadatak zadatak = zadatakOptional.get();
			
			Sprint sprint = zadatak.getSprint();
			Integer newSrintBodovi = Integer.parseInt(sprint.getUkupnoBodova()) - zadatak.getBodovi();
			sprint.setUkupnoBodova(newSrintBodovi + "");
			
			sprint.removeZadatak(zadatak.getId());
			Stanje stanje = zadatak.getStanje();
		    stanje.removeZadatak(zadatak.getId());
		    
		    stanjeRepository.save(stanje);
		    sprintRepository.save(sprint);
			zadatakRepository.deleteById(id);
			return zadatak;
		}
		return null;
	}

	@Override
	public Page<Zadatak> pretraga(Long id, int pageNum) {
		return zadatakRepository.pretraga(id, PageRequest.of(pageNum, 4));
	}

	@Override
	public Zadatak save(ZadatakDto zadatkDto) {
		Zadatak zadatak = toZadatak.convert(zadatkDto);
		if(zadatak.getId() == null) {
			Stanje novoStanje = stanjeRepository.findById(1L).get();
			zadatak.setStanje(novoStanje);
		}
		if(zadatkDto.getId() != null) {
			Optional<Zadatak> stariZadatakOptional = one (zadatkDto.getId());
			if(stariZadatakOptional.isPresent()) {
				Zadatak stariZadatak = stariZadatakOptional.get();
				Sprint stariSprint = stariZadatak.getSprint();
				stariSprint.removeZadatak(zadatkDto.getId());
				sprintRepository.save(stariSprint);		
			}
		}
		Sprint sprint = zadatak.getSprint();
		sprint.addZadatak(zadatak);
		Zadatak sacuvaniZadatak = zadatakRepository.save(zadatak);
		sprintRepository.save(sprint);
		return sacuvaniZadatak;
	}

	@Override
	public Optional<Zadatak> one(Long id) {
		return zadatakRepository.findById(id);
	}


	

	

	
	

}
