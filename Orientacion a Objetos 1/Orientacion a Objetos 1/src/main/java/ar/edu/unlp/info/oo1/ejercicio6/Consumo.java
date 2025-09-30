package ar.edu.unlp.info.oo1.ejercicio6;

public class Consumo {
	private double energiaActiva;
	private double energiaReactiva;
	
	public Consumo(double energiaActiva, double energiaReactiva) {
		super();
		this.energiaActiva = energiaActiva;
		this.energiaReactiva = energiaReactiva;
	}

	public double getEnergiaActiva() {
		return energiaActiva;
	}

	public double getEnergiaReactiva() {
		return energiaReactiva;
	}
	
	public double getFactorDePotencia() {
		return this.energiaActiva / Math.sqrt(Math.pow(energiaReactiva, 2)+Math.pow(energiaActiva, 2));
	}
}
