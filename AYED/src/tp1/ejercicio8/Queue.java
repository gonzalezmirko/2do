package tp1.ejercicio8;
import java.util.*;
public class Queue<T> extends Sequence{
	protected List<T>data; 
	
	//Constructor de la clase.
	public Queue() {
			this.data=new ArrayList<T>();
	}
	
	//Retorna la cantidad de elementos de la cola.
	@Override
	public int size() {
		return data.size();
	}
	
	//Retorna verdadero si la cola no tiene elementos y falso en caso contrario
	@Override
	public boolean isEmpty() {
		return data.isEmpty();
	}
	
	//Inserta el elemento al final de la cola
	public void enqueue(T dato) {
		data.add(dato);
	}
	
	//Elimina el elemento del frente de la cola y lo retorna. Si la cola está vacía se produce un error.
	public T dequeue() {
		return data.remove(0);
	}
	
	//Retorna el elemento del frente de la cola. Si la cola está vacía se produce un error.
	public T head() {
		return data.get(0);
	}
	//Retorna los elementos de la cola en un String
	@Override
	public String toString() {
		String aux="";
		for (int i=0;i<data.size();i++) {
			aux += "Dato:"+data.get(i)+"\n";
		}
		return aux;
	}
}
