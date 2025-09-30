package tp3.ejercicio4;

public class AreaEmpresa {
	
	private String identificacion;
	private int tardanzaTransmicion;
	
	public AreaEmpresa(String identificacion, int tardanzaTransmicion) {
		super();
		this.identificacion = identificacion;
		this.tardanzaTransmicion = tardanzaTransmicion;
	}
	
	public String getIdentificacion() {
		return identificacion;
	}

	public int getTardanzaTransmicion() {
		return tardanzaTransmicion;
	}

}
