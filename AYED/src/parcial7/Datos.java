package parcial7;

public class Datos {
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public int getDistancia() {
		return distancia;
	}
	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}
	private String usuario;
	private int distancia;
	public Datos(String usuario, int distancia) {
		super();
		this.usuario = usuario;
		this.distancia = distancia;
	}
	
	
}
