package tp1.ejercicio10;

public class Persona {
	private int nroTicket;
	private String nombre;
	private boolean prioridad;
	private int edad;
	
	public Persona(String nombre,boolean prioridad,int edad,int ticket) {
		this.nombre=nombre;
		this.prioridad=prioridad;
		this.edad=edad;
		this.nroTicket=ticket;
	}
	
	public String getNombre() {
		return nombre;
	}

	public boolean isPrioridad() {
		return prioridad;
	}

	public int getEdad() {
		return edad;
	}
	
	public String toString() {
		return "Nombre"+this.nombre;
	}
}
