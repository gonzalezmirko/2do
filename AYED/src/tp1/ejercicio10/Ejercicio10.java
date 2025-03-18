package tp1.ejercicio10;
/*
 * Considere el siguiente problema: Se quiere modelar la cola de atención en un banco. 
 * A medida que la gente llega al banco toma un ticket para ser atendido, sin embargo, de acuerdo
 *  a la LEY 14564 de la Provincia de Buenos Aires, se establece la obligatoriedad de otorgar prioridad
 *   de atención a mujeres embarazadas, a personas con necesidades especiales o movilidad reducida y a 
 *   personas mayores de setenta (70) años. De acuerdo a las estructuras de datos vistas en esta práctica, 
 *   ¿qué estructura de datos sugeriría para el modelado de la cola del banco?
 */
import tp1.ejercicio8.DoubleEndedQueue;
public class Ejercicio10 {
	public static void atenderPersonas(Persona[]array) {
		DoubleEndedQueue<Persona> cola=new DoubleEndedQueue<Persona>();//Para encolar al principio por las prioridades que tiene el enunciado
		for(int i=0;i<array.length;i++) {
			if((array[i].getEdad()>70)||(array[i].isPrioridad())){
				cola.enqueueFirst(array[i]);
			}
			else {
				cola.enqueue(array[i]);
			}
		}
		System.out.println(cola.toString().toString());
	}
	public static void main(String []args) {
		Persona [] arrayPersonas= {new Persona("Mirko",false,24,1),new Persona("Ana",false,23,2),new Persona("Victoria",false,73,3),new Persona("Chana",true,74,4),new Persona("Lidia",true,48,5)};
		/*
		Persona persona1=new Persona("Mirko",false,24,1);
		Persona persona2=new Persona("Ana",false,23,2);
		Persona persona3=new Persona("Victoria",false,73,3);//cola de prioridad 
		Persona persona4=new Persona("Chana",true,74,4);//cola de prioridad 
		Persona persona5=new Persona("Lidia",true,48,5);//cola de prioridad 
		*/
		atenderPersonas(arrayPersonas);
	}
}
