package sprint.sprint.web.dto;

import java.util.HashSet;
import java.util.Set;

public class SprintDto {
	

	private Long id;
	
	private String ime;
	
	private String ukupnoBodova;

	private Set<ZadatakDto> zadaci = new HashSet<>();

	public SprintDto() {
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

	public String getUkupnoBodova() {
		return ukupnoBodova;
	}

	public void setUkupnoBodova(String ukupnoBodova) {
		this.ukupnoBodova = ukupnoBodova;
	}

	public Set<ZadatakDto> getZadaci() {
		return zadaci;
	}

	public void setZadaci(Set<ZadatakDto> zadaci) {
		this.zadaci = zadaci;
	}
	
	

}
