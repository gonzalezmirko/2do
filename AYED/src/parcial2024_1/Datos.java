package parcial2024_1;

public class Datos {
	public int getCantidadUsuarios() {
		return cantidadUsuarios;
	}
	public void setCantidadUsuarios(int cantidadUsuarios) {
		this.cantidadUsuarios = cantidadUsuarios;
	}
	public boolean isEsPopular() {
		return esPopular;
	}
	public void setEsPopular(boolean esPopular) {
		this.esPopular = esPopular;
	}
	private int cantidadUsuarios=0;
	private boolean esPopular=false;
	
	
}
