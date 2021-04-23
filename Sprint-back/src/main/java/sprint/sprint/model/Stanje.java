package sprint.sprint.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Stanje {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String ime;
	@OneToMany(mappedBy = "stanje", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Zadatak> zadaci = new HashSet<>();
	
	public Stanje() {
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

	public Set<Zadatak> getZadaci() {
		return zadaci;
	}

	public void setZadaci(Set<Zadatak> zadaci) {
		this.zadaci = zadaci;
	}

	public void removeZadatak(Long id) {
		for(Zadatak z : this.zadaci) {
			if(z.getId() == id) {
				this.zadaci.remove(z);	
			    return;
			}
		}
	}
	
	

}