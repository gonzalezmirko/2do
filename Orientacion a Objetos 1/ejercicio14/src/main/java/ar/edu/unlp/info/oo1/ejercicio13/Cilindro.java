package ar.edu.unlp.info.oo1.ejercicio13;

public class Cilindro extends Pieza{
	private double radio;
	private int altura;
	/*
	 * Cilindro
	Volumen de un cilindro: π * radio 2 * h.
	Superficie de un cilindro: 2 * π * radio  * h + 2 * π * radio 2 

	 */
	public double volumen() {
		return Math.PI*Math.pow(this.radio, 2)*this.altura;
	}
	public double superficie() {
		return 2*Math.PI*this.radio*this.altura+2*Math.PI*Math.pow(this.radio, 2);
	}
}
