package ar.edu.unlp.info.oo1.ejercicio13;

public class Esfera extends Pieza{
	private double radio;
	/*
	 * Esfera
	Volumen de una esfera: ⁴⁄₃ * π * radio ³.
	Superficie de una esfera: 4 * π * radio 2

	 */
	public double volumen() {
		return (4/3) * Math.PI*Math.pow(this.radio, 3);
	}
	public double superficie() {
		return 4*Math.PI*Math.pow(this.radio, 2);
	}
}
