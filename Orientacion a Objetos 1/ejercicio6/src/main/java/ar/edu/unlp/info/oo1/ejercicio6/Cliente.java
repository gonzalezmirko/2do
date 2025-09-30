package ar.edu.unlp.info.oo1.ejercicio6;
import java.util.*;
public class Cliente {
	private String nombre;
	private String domilicio;
	private List<Consumo>consumos;
	
	public Cliente(String nombre, String domilicio) {
		super();
		this.nombre = nombre;
		this.domilicio = domilicio;
		this.consumos= new ArrayList<Consumo>();
	}
	
	public void agregarConsumo(Consumo consumo) {
		this.consumos.add(consumo);
	}
	
	public Consumo ultimoConsumo() {
		return this.consumos.getLast();
	}
}
