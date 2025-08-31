package ar.edu.unlp.oo1.ejercicio2;

import java.time.LocalDate;

public class Balanza {
	public int getCantidadDeProductos() {
		return cantidadDeProductos;
	}

	public void setCantidadDeProductos(int cantidadDeProductos) {
		this.cantidadDeProductos = cantidadDeProductos;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public void setPesoTotal(double pesoTotal) {
		this.pesoTotal = pesoTotal;
	}

	private int cantidadDeProductos;
	private double precioTotal;
	private double pesoTotal;
	
	public Balanza() {
		this.cantidadDeProductos=0;
		this.pesoTotal=0;
		this.precioTotal=0;
	}
	
	public int getCantProductos() {
		return cantidadDeProductos;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public double getPesoTotal() {
		return pesoTotal;
	}

	//duda
	public void ponerEnCero() {
		this.cantidadDeProductos=0;
		this.pesoTotal=0;
		this.precioTotal=0;
	}
	
	public void agregarProducto(Producto producto) {
		this.cantidadDeProductos++;
		this.pesoTotal+=producto.getPeso();
		this.precioTotal+=producto.getPrecio();
	}
	
	public Ticket emitirTicket() {
		return new Ticket(LocalDate.now(),this.cantidadDeProductos,this.pesoTotal,this.precioTotal);
	}
}
