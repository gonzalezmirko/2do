package tp1.ejercicio8;
import java.util.*;
public class Stack<T> extends Sequence{
	private List<T> data;
	
	public Stack() {
		data=new ArrayList<T>();
	}
	
	@Override
	public int size() {
		return data.size();
	}
	
	@Override
	public boolean isEmpty() {
		return data.isEmpty();
	}
	
	public void push(T dato) {
		data.add(dato);
	}
	
	public T pop(){
		return data.remove(size()-1);
	}
	
	@Override
	public String toString() {
		String aux="";
		for(T elem:data) {
			aux += "--Dato:"+elem+"--\n";
		}
		return aux;
	}
}
