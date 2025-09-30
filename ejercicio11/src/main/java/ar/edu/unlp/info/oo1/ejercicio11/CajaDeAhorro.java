package ar.edu.unlp.info.oo1.ejercicio11;

public class CajaDeAhorro extends Cuenta{

	@Override
	protected boolean puedeExtraer(double monto) {
		return this.getSaldo() >= monto+this.costoAdicional(monto);
	}
	
	public double costoAdicional(double unMonto) {
		return unMonto*0.02;
	}
	@Override
	public void depositar(double unMonto) {
		super.depositar(unMonto-this.costoAdicional(unMonto));//(980-200)->780-2%=
	}
	@Override
	public boolean transferirACuenta(double unMonto,Cuenta cuentaDestino) {
		if(this.puedeExtraer(unMonto)) {
			this.extraerSinControlar(unMonto);
			cuentaDestino.depositar(unMonto);
			return true;
		}
		return false;
	}
	@Override
	protected void extraerSinControlar(double unMonto) {
		super.extraerSinControlar(unMonto+this.costoAdicional(unMonto));
	}
}
