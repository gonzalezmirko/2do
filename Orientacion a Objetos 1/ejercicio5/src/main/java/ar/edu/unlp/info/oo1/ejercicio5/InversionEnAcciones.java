package ar.edu.unlp.info.oo1.ejercicio5;

public class InversionEnAcciones implements Inversion{
	private int cantidadDeAcciones;
	private double valorUnitario;
	private String nombre;
	
	
	public InversionEnAcciones(int cantidadDeAcciones, double valorUnitario, String nombre) {
		super();
		this.cantidadDeAcciones = cantidadDeAcciones;
		this.valorUnitario = valorUnitario;
		this.nombre = nombre;
	}


	public double valorActual() {
		return this.cantidadDeAcciones*this.valorUnitario;
	}
}
