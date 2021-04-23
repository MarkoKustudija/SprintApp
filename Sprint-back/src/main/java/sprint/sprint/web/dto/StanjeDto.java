package sprint.sprint.web.dto;

import java.util.HashSet;
import java.util.Set;

public class StanjeDto {
	
	
	private Long id;
	
	private String ime;
	
	private Set<ZadatakDto> zadaci = new HashSet<>();

	public StanjeDto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public Set<ZadatakDto> getZadaci() {
		return zadaci;
	}

	public void setZadaci(Set<ZadatakDto> zadaci) {
		this.zadaci = zadaci;
	}
	
	

}
