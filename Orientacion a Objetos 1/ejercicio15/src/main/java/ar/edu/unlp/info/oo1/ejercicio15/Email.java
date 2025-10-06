package ar.edu.unlp.info.oo1.ejercicio15;
import java.util.*;

public class Email {
	private String titulo;
	private String cuerpo;
	private List<Archivo> adjuntos;
	
	public Email(String titulo,String cuerpo) {
		this.titulo=titulo;
		this.cuerpo=cuerpo;
		this.adjuntos=new ArrayList<Archivo>();
	}
	public String getTitulo() {
		return titulo;
	}
	public String getCuerpo() {
		return cuerpo;
	}
	public List<Archivo> getAdjuntos() {
		return adjuntos;
	}
	
	public void agregarArchivoAdjunto(Archivo archivo) {
		this.adjuntos.add(archivo);
	}
	
	public boolean contieneTituloCuerpo(String texto) {
		return this.getCuerpo().equals(texto) || this.getCuerpo().equals(texto);
	}
	
	public int tamaño() {
		return this.titulo.length()+this.cuerpo.length();
	}
	
}
