package ar.edu.unlp.info.oo1.ejercicio12;

import java.util.List;

public class HighestPriorityStrategy implements JobSelectionStrategy{
	public JobDescription next(List<JobDescription>jobs) {
		if(jobs.isEmpty()) {
			return null;
		}
		return jobs.stream()
                .max((j1,j2) -> Double.compare(j1.getPriority(), j2.getPriority()))
                .orElse(null);
	}
}
