package ar.edu.unlp.info.oo1.ejercicio15;
import java.util.*;
public class ClienteDeCorreo {
	private Carpeta inbox;
	private List<Carpeta> carpetas;
	
	
	public ClienteDeCorreo() {
		this.inbox= new Carpeta("inbox");
		this.carpetas = new ArrayList<Carpeta>();
	}
	
	private List<Carpeta> getCarpetas(){
		List<Carpeta> carpetas = new ArrayList<Carpeta>(this.carpetas);
		carpetas.add(inbox);
		return carpetas;
	}

	public void recibir(Email email) {
		this.inbox.agregarEmail(email);
	}
	
	/*
	 * En respuesta al mensaje #buscar retorna el primer email 
	 * en el Cliente de Correo cuyo título o cuerpo contienen 
	 * el texto indicado como parámetro. Busca en todas las carpetas. 

	 */
	/*
	public Email buscar(String texto) {
		//consultar como se hace con stream
		Email email=null;
		for(Carpeta carpeta:this.carpetas) {
			email = carpeta.buscar(texto);
		}
		return email;
	}
	*/
	public Email buscar(String text) {
		return this.getCarpetas().stream().map(c->c.buscar(text)).findFirst().orElse(null);
	}
	public int espacioOcupado() {
		return this.getCarpetas().stream().mapToInt(c->c.tamaño()).sum();
	}
	public void agregarCarpeta(Carpeta carpeta) {
		this.carpetas.add(carpeta);
	}
}
