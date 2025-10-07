package ar.edu.unlp.info.oo1.ejercicio16;
import java.time.*;
import java.time.temporal.ChronoUnit;
public class DateLapse implements DateLapseInterface{
	private LocalDate from;
	private LocalDate to;
	
	public DateLapse(LocalDate from,LocalDate to) {
		this.from=from;
		this.to=to;
	}
	
	/*
	Tenga en cuenta que los métodos de LocalDate colaboran con otros objetos que están definidos a partir de enums, 
	clases e interfaces de java.time; por ejemplo java.time.temporal.ChronoUnit.DAYS
	 */
	
	/*
	//Investigue cómo hacer para crear una fecha determinada, por ejemplo 15/09/1972. 
	public void crearFecha() {
		//cualquier fecha inicio o fin
		from.of(15, 9, 1972);
	}
	*/
	
	/*
	Investigue cómo hacer para calcular el número de días entre dos fechas.
	Lo mismo para el número de meses y de años Sugerencia: vea el método until.

	 */
	//me parecio mas facil asi(no vi el metodo until)
	public long sizeMonths() {
		return ChronoUnit.MONTHS.between(from, to);
	}
	public long sizeYears() {
		return ChronoUnit.YEARS.between(from, to);
	}
	
	//“Retorna la fecha de inicio del rango”
	public LocalDate getFrom() {
		return this.from;
	}

	//“Retorna la fecha de fin del rango”
	public LocalDate getTo() {
		return this.to;
	}

	//“retorna la cantidad de días entre la fecha 'from' y la fecha 'to'”
	//¿O directamente devuelve long?
	public int sizeInDays() {
		return (int) ChronoUnit.DAYS.between(from, to);
	}

	/*
	Investigue cómo hacer para determinar si la fecha de hoy se encuentra entre las fechas 15/12/1972 y 15/12/2032.
	 Sugerencia: vea los métodos permiten comparar LocalDates y que retornan booleans.
	 */
	//“recibe un objeto LocalDate y retorna true si la fecha está entre el from y el to del receptor y false en caso contrario”.
	public boolean includesDate(LocalDate other) {
		return (other.isAfter(this.from) || other.isEqual(this.from)) && (other.isBefore(this.to)||other.isEqual(this.to));
	}


}
