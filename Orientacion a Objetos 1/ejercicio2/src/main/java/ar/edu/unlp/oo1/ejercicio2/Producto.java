package ar.edu.unlp.oo1.ejercicio2;

public class Producto {
	public void setPeso(double peso) {
		this.peso = peso;
	}

	public void setPrecioPorKilo(double precioPorKilo) {
		this.precioPorKilo = precioPorKilo;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecioPorKilo() {
		return precioPorKilo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	private double peso;
	private double precioPorKilo;
	private String descripcion;
	
	public Producto(String descripcion,double peso, double precioPorKilo) {
		super();
		this.peso = peso;
		this.precioPorKilo = precioPorKilo;
		this.descripcion = descripcion;
	}
	
	public double getPrecio() {
		return this.precioPorKilo*this.peso;
	}
	
	public double getPeso() {
		return this.peso;
	}
	
	
}
