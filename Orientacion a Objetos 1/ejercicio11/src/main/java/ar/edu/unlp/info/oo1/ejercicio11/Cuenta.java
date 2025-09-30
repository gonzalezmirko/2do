package ar.edu.unlp.info.oo1.ejercicio11;

public abstract class Cuenta {
	private double saldo;
	
	public Cuenta() {
		this.saldo=0;
	}
	
	public double getSaldo() {
		return this.saldo;
	}
	
	public void depositar(double unMonto) {
		this.saldo += unMonto;
	}
	
	protected void extraerSinControlar(double unMonto) {
		this.saldo -= unMonto;
	}
	
	protected abstract boolean puedeExtraer(double monto);
	
	public boolean extraer(double monto) {
		if(this.puedeExtraer(monto)) {
			this.extraerSinControlar(monto);
			return true;
		}
		return false;
	}
	
	public boolean transferirACuenta(double unMonto,Cuenta cuentaDestino) {
		if(this.puedeExtraer(unMonto)) {
			this.extraerSinControlar(unMonto);
			cuentaDestino.depositar(unMonto);
			return true;
		}
		return false;
	}
}
