package ar.edu.unlp.info.oo1.ejercicio16;
import java.time.*;
public interface DateLapseInterface {
	
	public LocalDate getFrom();
	public LocalDate getTo();
	public int sizeInDays();
	public boolean includesDate(LocalDate other);
}
