package sprint.sprint.web.dto;



import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import sprint.sprint.model.Sprint;
import sprint.sprint.model.Stanje;

public class ZadatakDto {
	
	
	private Long id;
	@NotBlank
	@Length(max = 40)
	private String imeZadataka;
	
	private String zaduzeni;
	@Min(value = 0)
	@Max(value = 20)
	private int bodovi;
	
//	private Sprint sprintId;
//	private Sprint sprintNaziv;
//	
//	private Stanje stanjeId;
//	private Stanje stanjeNaziv;
	
	private Long sprintId;
	private Long stanjeId;
	
	
	public ZadatakDto() {
		super();
	}
	

	public Long getSprintId() {
		return sprintId;
	}


	public void setSprintId(Long sprintId) {
		this.sprintId = sprintId;
	}


	public Long getStanjeId() {
		return stanjeId;
	}


	public void setStanjeId(Long stanjeId) {
		this.stanjeId = stanjeId;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getImeZadataka() {
		return imeZadataka;
	}


	public void setImeZadataka(String imeZadataka) {
		this.imeZadataka = imeZadataka;
	}


	public String getZaduzeni() {
		return zaduzeni;
	}


	public void setZaduzeni(String zaduzeni) {
		this.zaduzeni = zaduzeni;
	}


	public int getBodovi() {
		return bodovi;
	}


	public void setBodovi(int bodovi) {
		this.bodovi = bodovi;
	}



	
	
	
	
	

}
