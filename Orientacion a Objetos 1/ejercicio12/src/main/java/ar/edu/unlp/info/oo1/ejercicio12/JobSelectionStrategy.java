package ar.edu.unlp.info.oo1.ejercicio12;
import java.util.*;
public interface JobSelectionStrategy {
	public JobDescription next(List<JobDescription>jobs);
}
