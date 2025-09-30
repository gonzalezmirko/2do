package tp1.ejercicio7;
/*
 * d.	Escriba un método que realice las siguientes acciones:
■ cree una lista que contenga 3 estudiantes
■ genere una nueva lista que sea una copia de la lista del inciso i
■ imprima el contenido de la lista original y el contenido de la nueva lista
■ modifique algún dato de los estudiantes
■ vuelva a imprimir el contenido de la lista original y el contenido de la nueva lista. ¿Qué conclusiones }
obtiene a partir de lo realizado?
■ ¿Cuántas formas de copiar una lista existen? ¿Qué diferencias existen entre ellas?
e.	A la lista del punto 7d, agregue un nuevo estudiante. Antes de agregar, verifique que el estudiante 
no estaba incluido en la lista.

 */
import java.util.*;
import tp1.ejercicio3.Estudiante;
public class TestEstudiantes {
	public static void imprimirE(LinkedList<Estudiante>list) {
		for(Estudiante e:list) {
			System.out.println(e.tusDatos());
			System.out.println("----------");
		}
	}
	public static void main(String[]args) {
		LinkedList<Estudiante> listaEstudiantes=new LinkedList<Estudiante>();
		Estudiante est1=new Estudiante("Mirko","Gonzalez",1,"m@g","10");
		Estudiante est2=new Estudiante("Ana","De Pasqua",2,"a@d","11");
		Estudiante est3=new Estudiante("Carla","Pizzolante",3,"c@p","12");
		listaEstudiantes.add(est1);
		listaEstudiantes.add(est2);
		listaEstudiantes.add(est3);
		LinkedList<Estudiante> listaCopia=new LinkedList<Estudiante>(listaEstudiantes);
		
		System.out.println("--Lista original--");
		imprimirE(listaEstudiantes);
		System.out.println("--Lista copia--");
		imprimirE(listaCopia);
		
		listaEstudiantes.get(0).setNombre("MARCOS");
		listaCopia.getLast().setNombre("JUAN");
		
		System.out.println("--Lista original--");
		imprimirE(listaEstudiantes);
		System.out.println("--Lista copia--");
		imprimirE(listaCopia);
		//Se modifican el contenido de ambas listas porque trabajan con punteros o referencias(apuntan a lo mismo)
		//Existen varias formas de copiar una lista como por ejemplo la anterior 
		//metodo addall() y clonar pero no se recomienda
		
		/*
		 e.	A la lista del punto 7d, agregue un nuevo estudiante. 
		 Antes de agregar, verifique que el estudiante no estaba incluido en la lista.
		 */
		//o la otra opcion es hacer un downcasting con el metodo equals()
		Estudiante est4=new Estudiante("Mirko","Gonzalez",1,"m@g","10");
		Estudiante est5=new Estudiante("Juan","Perez",2,"m@g","10");
		boolean existe1=false,existe2=false;
		for(Estudiante e:listaEstudiantes) {
			if(e.existeEstudiante(est4)) {
				existe1=true;
			}
			if(e.existeEstudiante(est5)) {
				existe2=true;
			}
		}
		if(!existe1) {
			listaEstudiantes.add(est4);
		}
		if(!existe2) {
			listaEstudiantes.add(est5);
		}
		imprimirE(listaEstudiantes);
	}
}
