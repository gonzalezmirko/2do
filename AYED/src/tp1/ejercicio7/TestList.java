package tp1.ejercicio7;
/*
 * 7.	Uso de las estructuras de listas provistas por la API de Java. Para resolver este punto cree el 
 * paquete tp1.ejercicio7
a.	Escriba una clase llamada TestArrayList cuyo método main recibe una secuencia de números, los agrega a 
una lista de tipo ArrayList, y luego de haber agregado todos los números a la lista, imprime el contenido de la
 misma iterando sobre cada elemento.
b.	Si en lugar de usar un ArrayList en el inciso anterior hubiera usado un LinkedList ¿Qué diferencia encuentra 
respecto de la implementación? Justifique --
c.	¿Existen otras alternativas para recorrer los elementos de la lista del punto 7a.?

 */
import java.util.*;
public class TestList {
	public static boolean esCapicua(ArrayList<Integer>lista) {
		int j=lista.size()-1;
		for(int i=0;i<lista.size()/2;i++) {
			System.out.println("-->I:"+lista.get(i));
			System.out.println("-->J:"+lista.get(j));
			if(lista.get(i)==lista.get(j)) {
				j--;
			}
			else {
				return false;
			}
		}
		return true;
	}
	public static void main(String[]args) {
		ArrayList<Integer> listaArray=new ArrayList<Integer>();
		listaArray.add(10);
		listaArray.add(20);
		listaArray.add(50);
		listaArray.add(60);
		listaArray.add(20);
		listaArray.add(10);
		//Punto B=con respecto a la implementacion definir el tipo --> LinkedList<Integer>=... 
		//pero si un uso de memoria mayor con los punteros
		//Punto c=for
		System.out.println("---Lista for normal---");
		for(int i=0;i<listaArray.size();i++) {
			System.out.println("Numero:"+listaArray.get(i));
		}
		
		//Punto c=for each
		System.out.println("---Lista for each---");
		for(int elem:listaArray) {
			System.out.println("---Numero:"+elem);
		}
		
		//Punto c=Iterator
		System.out.println("---Lista Iterator---");
		Iterator<Integer> it=listaArray.iterator();
		while(it.hasNext()) {
			System.out.println("---Numero:"+it.next());
		}
		if(esCapicua(listaArray)) {
			System.out.println("--La Lista es capicua--");
		}
		else {
			System.out.println("--La lista no es capicua--");
		}
	}
}
