package sprint.sprint.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sprint.sprint.model.Zadatak;
import sprint.sprint.service.ZadatakService;
import sprint.sprint.support.ZadatakDtoToZadatak;
import sprint.sprint.support.ZadatakToZadatakDto;
import sprint.sprint.web.dto.ZadatakDto;

@RestController
@RequestMapping(value = "/api/zadaci", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class Zadatakcontroller {
 
	@Autowired
	private ZadatakService zadatakService;
	
	@Autowired 
	private ZadatakToZadatakDto toDto;
	
	@Autowired
	private ZadatakDtoToZadatak toZadatak;
	
	@GetMapping("/{id}")
	public ResponseEntity <ZadatakDto> getOne (@PathVariable Long id){
		
		Zadatak zadatak = zadatakService.findOne(id);
		
		if(zadatak != null) {
			return new ResponseEntity<ZadatakDto>(toDto.convert(zadatak), HttpStatus.OK);
		} else {
			return new ResponseEntity<ZadatakDto>(HttpStatus.NOT_FOUND);
		}
	}
		
		@GetMapping()
		public ResponseEntity<List<ZadatakDto>> getAll (@RequestParam (required = false) Long id,
				@RequestParam(required = false, defaultValue = "0") int pageNo){
			Page<Zadatak> zadatakPage = null;
			
			if(id != null) {
				zadatakPage = zadatakService.pretraga(id, pageNo);
			}else {
				zadatakPage = zadatakService.findAll(PageRequest.of(pageNo, 1));
		}
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("Total-Pages", zadatakPage.getTotalPages() + "");
          
			return new ResponseEntity<>(toDto.convert(zadatakPage.getContent()), responseHeaders, HttpStatus.OK);
		
	}

		
		@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<ZadatakDto> create (@Valid @RequestBody ZadatakDto zadatakDto){
			
			Zadatak zadatak = toZadatak.convert(zadatakDto);
			Zadatak sacuvaniZadatak = zadatakService.save(zadatak);
			
			return new ResponseEntity<>(toDto.convert(sacuvaniZadatak), HttpStatus.CREATED);
			
		}
		
		@PreAuthorize("hasRole('ADMIN')")
		@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<ZadatakDto> update (@PathVariable Long id, @Validated @RequestBody ZadatakDto zadatakDto){
			
			if(!id.equals(zadatakDto.getId())){
				
				return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
				
			}
				
				Zadatak zadatak = toZadatak.convert(zadatakDto);
				Zadatak sacuvaniZadatak = zadatakService.update(zadatak);
				
				return new ResponseEntity<>(toDto.convert(sacuvaniZadatak), HttpStatus.OK);
				
				
		}		
			
		@PreAuthorize("hasRole('ADMIN')")
		@DeleteMapping(value = "/{id}")
		public ResponseEntity<ZadatakDto> delete (@PathVariable Long id){
           Zadatak deleted = zadatakService.delete(id);
           
           if(deleted == null) {
        	   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
           } 
        	   return new ResponseEntity<>(toDto.convert(deleted), HttpStatus.OK);
           
		}
		
	
}
