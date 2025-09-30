package ar.edu.unlp.info.oo1.ejercicio13;
import java.util.*;
public class ReporteDeConstruccion {
	private List<Pieza>piezas;
	//	"Recibe como parámetro un nombre de material 
	//(un string, por ejemplo 'Hierro'). Retorna la suma de los volúmenes
	//de todas las piezas hechas en ese material"

	public double volumenDeMaterial(String nombreDeMaterial) {
		return piezas.stream().filter(p->p.contieneMaterial(nombreDeMaterial))
				.mapToDouble(p->p.volumen()).sum();
	}
	/*
		"Recibe como parámetro un color (un string, por ejemplo 'Rojo').
		 Retorna la suma de las superficies externas de todas las piezas pintadas con ese color".		

	 */
	public double superficieDeColor(String unNombreDeColor) {
		return piezas.stream().filter(p->p.contieneColor(unNombreDeColor))
				.mapToDouble(p->p.superficie()).sum();
	}
}
