package ar.edu.unlp.info.oo1.ejercicio16;
import java.time.*;
import java.time.temporal.ChronoUnit;
public class DateLapse2 implements DateLapseInterface{
	private LocalDate from;
	private int sizeInDays;
	
	public DateLapse2(LocalDate from,int size) {
		this.from=from;
		this.sizeInDays=size;
	}
	
	public LocalDate getFrom() {
		return this.from;
	}
	
	public LocalDate getTo() {
		return this.from.plusDays(sizeInDays);
	}
	
	public int sizeInDays2() {
		return from.until(this.getTo()).getDays();
	}
	public int sizeInDays() {
		return (int) ChronoUnit.DAYS.between(this.from, this.getTo());
	}
	
	public boolean includesDate(LocalDate other) {
		return (other.isAfter(this.from)||other.isEqual(this.from))&&(other.isBefore(this.getTo())||other.isEqual(this.getTo()));
	}
}
