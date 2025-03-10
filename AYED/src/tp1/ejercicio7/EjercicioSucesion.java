package tp1.ejercicio7;
/*
g.	Considere que se aplica la siguiente función de forma recursiva. A partir de un número n positivo 
se obtiene una sucesión que termina en 1:
 
Por ejemplo, para n= 6, se obtiene la siguiente sucesión:
f(6) = 6/2 = 3 f(3) = 3*3 + 1 = 10 f(10) = 10/2 = 5 ….
Es decir, la sucesión 6, 3, 10, 5, 16, 8, 4, 2, 1. Para cualquier n con el que se arranque siempre se llegará al 1.
■ Escriba un programa recursivo que, a partir de un número n, devuelva una lista con cada miembro de la sucesión.
public class EjercicioSucesion { 
	public List<Integer> calcularSucesion (int n) {
	//código
}
}
h.	Implemente un método recursivo que invierta el orden de los elementos en un
ArrayList.
public void invertirArrayList(ArrayList<Integer> lista)
i.	Implemente un método recursivo que calcule la suma de los elementos en un
LinkedList.
public int sumarLinkedList(LinkedList<Integer> lista)

 */
import java.util.*;
public class EjercicioSucesion {
	private static void invertirRecursivo(ArrayList<Integer>lista,int ini,int fin) {
		if(ini<fin) {
			int copia=lista.get(ini);
			lista.set(ini, lista.get(fin));
			lista.set(fin, copia);
			invertirRecursivo(lista,ini+1,fin-1);
		}
	}
	public static void invertirArrayList(ArrayList<Integer>lista) {
		int ini=0;
		int fin=lista.size()-1;
		invertirRecursivo(lista,ini,fin);
	}
	public static int sumarRecursivo(LinkedList<Integer>l,int pos) {
		if(pos<l.size()) {
			int suma=0;
			suma += l.get(pos);
			return suma+sumarRecursivo(l,pos+1);
		}
		return 0;
	}
	public static int sumarLinkedList(LinkedList<Integer>lista) {
		int pos=0;
		return sumarRecursivo(lista,pos);
	}
	private static boolean esPar(int n) {
		return (n % 2)==0;
	}
	private static void calcularSucesionRecursivo(ArrayList<Integer> l,int n) {
		if(esPar(n)&&(n != 1)) {
			l.add(n);
			calcularSucesionRecursivo(l,n/2);
		}
		else if(n != 1){
			l.add(n);
			calcularSucesionRecursivo(l,3*n+1);
		}
		else {
			l.add(n);
		}
	}
	public static ArrayList<Integer> calcularSucesion(int n){
		ArrayList<Integer> lista=new ArrayList<Integer>();
		calcularSucesionRecursivo(lista,n);
		return lista;
	}
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		System.out.println("---Leer numero para sucesion:");
		int n=input.nextInt();
		ArrayList<Integer>listaEnteros=calcularSucesion(n);
		for(Integer elem:listaEnteros) {
			System.out.println("-->Numero:"+elem);
		}
		//puntoH
		for(Integer elem:listaEnteros) {
			System.out.println("-->Numero:"+elem);
		}
		invertirArrayList(listaEnteros);
		System.out.println("--INVERTIRRRRRRRRRRRRRR--");
		for(Integer elem:listaEnteros) {
			System.out.println("-->Numero:"+elem);
		}
		LinkedList<Integer>listaEnteros2=new LinkedList<Integer>();
		listaEnteros2.add(10);
		listaEnteros2.add(10);
		listaEnteros2.add(10);
		listaEnteros2.add(10);
		listaEnteros2.add(10);
		listaEnteros2.add(50);
		System.out.println("--Suma de la lista de enteros: "+sumarLinkedList(listaEnteros2));
	}
}
