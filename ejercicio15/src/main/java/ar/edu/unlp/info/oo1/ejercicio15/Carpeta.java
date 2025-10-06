package ar.edu.unlp.info.oo1.ejercicio15;
import java.util.*;
public class Carpeta {
	private String nombre;
	private List<Email>emails;
	
	public Carpeta(String nombre) {
		this.nombre=nombre;
		this.emails=new ArrayList<Email>();
	}
	public String getNombre() {
		return this.nombre;
	}
	
	public void agregarEmail(Email email) {
		this.emails.add(email);
	}
	
	//Hay que preguntar si existe cada cosa?
	public void mover(Email email,Carpeta carpetaDestino) {
		this.emails.remove(email);
		carpetaDestino.agregarEmail(email);
	}
	
	public List<Email>getEmails(){
		return this.emails;
	}
	
	public int tamaño() {
		return this.emails.stream().mapToInt(e->e.tamaño()).sum();
	}
	
	public Email buscar(String text) {
		return this.emails.stream().filter(e->e.contieneTituloCuerpo(text)).findFirst().orElse(null);
	}
}
