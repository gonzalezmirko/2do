package tp1.ejercicio3;
/*
 * a.	Cree una clase llamada Estudiante con los atributos especificados abajo y 
 * sus correspondientes métodos getters y setters (haga uso de las facilidades que brinda eclipse)
●	nombre
●	apellido
●	comision
●	email
●	direccion

 */
public class Estudiante {
	private String nombre;
	private String apellido;
	private int comision;
	private String mail;
	private String direccion;
	
	
	public Estudiante(String nombre, String apellido, int comision, String mail, String direccion) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.comision = comision;
		this.mail = mail;
		this.direccion = direccion;
	}
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
	public int getComision() {
		return comision;
	}
	public void setComision(int comision) {
		this.comision = comision;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String tusDatos() {
		return "Nombre:"+this.getNombre()+"\n"+
				"Apellido:"+this.getApellido()+"\n"+
				"Mail:"+this.getMail()+"\n"+
				"Comision:"+this.getComision()+"\n"+
				"Direccion:"+this.getDireccion();
	}
	
	public boolean existeEstudiante(Estudiante e) {
		return (this.getApellido().equals(e.getApellido())&&(this.getNombre().equals(e.getNombre()))&&(this.getComision()==e.getComision()));
	}
}
