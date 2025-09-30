package ar.edu.unlp.info.oo1.ejercicio10;

public class EmpleadoJerarquico extends Empleado{
	
	public double montoBasico() {
		return 45000;
	}
	
	public double sueldoBasico() {
		return super.sueldoBasico()+this.bonoPorCategoria();
	}
	
	public double bonoPorCategoria() {
		return 8000;
	}
}
