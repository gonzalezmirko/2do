package ar.edu.unlp.info.oo1.ejercicio5;
import java.util.*;
public class Inversor {
	private String nombre;
	private List<Inversion>inversiones;
	
	public Inversor(String nombre) {
		super();
		this.nombre = nombre;
		this.inversiones=new LinkedList<Inversion>();
	}
	
	public void agregarInversion(Inversion inversion) {
		this.inversiones.add(inversion);
	}
	
	public void quitarInversion(Inversion inversion) {
		this.inversiones.remove(inversion);
	}
	
	public double calcularValorActualTotal() {
		return this.inversiones.stream().mapToDouble(i->i.valorActual()).sum();
	}
}
