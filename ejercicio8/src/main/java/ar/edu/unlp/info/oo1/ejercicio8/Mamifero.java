package ar.edu.unlp.info.oo1.ejercicio8;
import java.time.*;
public class Mamifero {
	private String identificador;
	private String especie;
	private LocalDate fechaDeNacimiento;
	private Mamifero madre;
	private Mamifero padre;
	
	public Mamifero(String id) {
		this.identificador=id;
	}
	
	public Mamifero() {
		
	}
	public Mamifero(String identificador, String especie, LocalDate fechaDeNacimiento) {
		super();
		this.identificador = identificador;
		this.especie = especie;
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public LocalDate getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	public Mamifero getMadre() {
		return madre;
	}

	public void setMadre(Mamifero madre) {
		this.madre = madre;
	}

	public Mamifero getPadre() {
		return padre;
	}

	public void setPadre(Mamifero padre) {
		this.padre = padre;
	}

	public Mamifero getAbueloMaterno() {
		if(this.madre!=null) {
			return this.madre.getPadre();
		}
		return null;
	}
	
	public Mamifero getAbuelaMaterna() {
		if(this.madre!=null) {
			return this.madre.getMadre();
		}
		return null;
	}

	public Mamifero getAbuelaPaterna() {
		if(this.padre!=null) {
			return this.padre.getMadre();
		}
		return null;
	}

	public Mamifero getAbueloPaterno() {
		if(this.padre!=null) {
			return this.padre.getPadre();
		}
		return null;
	}
	
	public boolean tieneComoAncestroA(Mamifero unMamifero) {
		//si es un mamifero nulo o es su propio ancestro
		if(unMamifero == null || this.equals(unMamifero)) {
			return false;
		}
		if(this.padre!=null) {
			//si el ancestro es el padre o recursivamente lo tiene él
			if(this.padre.equals(unMamifero) || this.padre.tieneComoAncestroA(unMamifero)) {
				return true;
			}
		}
		if(this.madre!=null) {
			//si el ancestro es la madre o recursivamente lo tiene ella
			if(this.madre.equals(unMamifero)||this.madre.tieneComoAncestroA(unMamifero)) {
				return true;
			}
		}
		return false;
	}
}
