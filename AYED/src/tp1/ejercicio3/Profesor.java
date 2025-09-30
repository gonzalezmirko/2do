package tp1.ejercicio3;
/*
 a.	Cree una clase llamada Profesor con los atributos especificados abajo y sus correspondientes 
 métodos getters y setters (haga uso de las facilidades que brinda eclipse)
●	nombre
●	apellido
●	email
●	catedra
●	facultad

 */
public class Profesor {
	
	private String nombre;
	private String apellido;
	private String mail;
	private int catedra;
	private String facultad;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getCatedra() {
		return catedra;
	}
	public void setCatedra(int catedra) {
		this.catedra = catedra;
	}
	public String getFacultad() {
		return facultad;
	}
	public void setFacultad(String facultad) {
		this.facultad = facultad;
	}
	
	public Profesor(String nombre, String apellido, String mail, int catedra, String facultad) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.mail = mail;
		this.catedra = catedra;
		this.facultad = facultad;
	}
	public String tusDatos() {
		return "Nombre:"+this.getNombre()+"\n"+
				"Apellido:"+this.getApellido()+"\n"+
				"Mail:"+this.getMail()+"\n"+
				"Catedra:"+this.getCatedra()+"\n"+
				"Facultad:"+this.getFacultad();
	}
}
