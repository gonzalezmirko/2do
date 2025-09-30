package ar.edu.unlp.info.oo1.ejercicio5;
import java.time.*;
import java.time.temporal.ChronoUnit;
public class InversionEnPlazosFijos implements Inversion{
	private LocalDate fecha;
	private double montoDepositado;
	private double porcentajeInteres;
	
	
	public InversionEnPlazosFijos(double montoDepositado, double porcentajeInteres) {
		super();
		this.montoDepositado = montoDepositado;
		this.porcentajeInteres = porcentajeInteres;
		this.fecha=LocalDate.now();
	}

	public double valorActual() {
		long dias=ChronoUnit.DAYS.between(this.fecha, LocalDate.now());
		double monto=this.montoDepositado;
		for(int i=0;i<dias;i++) {
			monto += monto * this.porcentajeInteres;
		}
		return monto;
	}
	
}
