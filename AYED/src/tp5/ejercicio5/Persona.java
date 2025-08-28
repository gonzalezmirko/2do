package tp5.ejercicio5;

import java.util.Objects;

/*
 * La persona conoce si es empleado o jubilado, el nombre y el domicilio.
 */
public class Persona {

	private String nombre;
	private int domicilio;
	private boolean empleado;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(int domicilio) {
		this.domicilio = domicilio;
	}
	public boolean isEmpleado() {
		return empleado;
	}
	public void setEmpleado(boolean empleado) {
		this.empleado = empleado;
	}
	
	public Persona(String nombre, int domicilio, boolean empleado) {
		super();
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.empleado = empleado;
	}
	
	
}
