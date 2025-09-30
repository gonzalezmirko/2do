package ar.edu.unlp.info.oo1.ejercicio11;

public class CuentaCorriente extends Cuenta{
	private double limiteDeDescubierto;

	public CuentaCorriente() {
		super();
		this.limiteDeDescubierto = 0;
	}
	
	
	public CuentaCorriente(double limiteDeDescubierto) {
		super();
		this.limiteDeDescubierto = limiteDeDescubierto;
	}


	public boolean puedeExtraer(double monto) {
		return (this.getSaldo()-this.getLimiteDeDescubierto()*-1) >= monto;
	}
	
	public double getLimiteDeDescubierto() {
		return this.limiteDeDescubierto;
	}
	
	public void setLimiteDeDescubierto(double limite) {
		this.limiteDeDescubierto=limite;
	}
}
