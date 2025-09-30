package ejerciciosChatGPT;
import java.util.*;
public class CaminoSeguro {
	public List<String> getCaminos() {
		return caminos;
	}
	public void setCaminos(List<String> caminos) {
		this.caminos = caminos;
	}
	public int getCostoTotal() {
		return costoTotal;
	}
	public void setCostoTotal(int costoTotal) {
		this.costoTotal = costoTotal;
	}
	private List<String>caminos;
	private int costoTotal;
	public CaminoSeguro(List<String> caminos, int costoTotal) {
		super();
		this.caminos = caminos;
		this.costoTotal = costoTotal;
	}
	
	
}
