package ar.edu.unlp.info.oo1.ejercicio6;
import java.time.*;
public class Factura {
	private Cliente cliente;
	private LocalDate fecha;
	private double montoFinal;
	private double bonificacion;
	
	public Factura(Cliente cliente,CuadroTarifario cuadroTarifario) {
		super();
		this.cliente = cliente;
		this.fecha=LocalDate.now();
		
		this.calcularFactura(cuadroTarifario);
	}
	public boolean tieneBonificacion(double elem) {
		return elem>0.8;
	}
	public void calcularFactura(CuadroTarifario cuadroTarifario) {
		Consumo ultimoConsumo=cliente.ultimoConsumo();
		double montoFinal=ultimoConsumo.getEnergiaActiva()*cuadroTarifario.getCosto();
		if(tieneBonificacion(ultimoConsumo.getFactorDePotencia())) {
			//montoFinal=montoFinal-(montoFinal*0.10);
			this.bonificacion=montoFinal*0.10;
		}
		else {
			this.bonificacion=0;
		}
		this.montoFinal=montoFinal-this.bonificacion;
	}
}
