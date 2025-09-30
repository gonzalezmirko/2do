package tp3.ejercicio2;
/*
public List<Integer> numerosImparesMayoresQuePreOrden (GeneralTree <Integer> a, Integer n)
Método que retorna una lista con los elementos impares del árbol “a” que sean mayores al valor “n” 
pasados como parámetros, recorrido en preorden.

public List<Integer> numerosImparesMayoresQueInOrden (GeneralTree <Integer> a, Integer n)
Método que retorna una lista con los elementos impares del árbol “a” que sean mayores al valor “n” 
pasados como parámetros, recorrido en inorden.

public List<Integer> numerosImparesMayoresQuePostOrden (GeneralTree <Integer> a, Integer n)
Método que retorna una lista con los elementos impares del árbol “a” que sean mayores al valor “n” 
pasados como parámetros, recorrido en postorden.

public List<Integer> numerosImparesMayoresQuePorNiveles(GeneralTree <Integer> a, Integer n)
Método que retorna una lista con los elementos impares del árbol “a” que sean mayores al valor “n”
 pasados como parámetros, recorrido por niveles.
 
b)	Si ahora tuviera que implementar estos métodos en la clase GeneralTree<T>, 
¿qué modificaciones haría tanto en la firma como en la implementación de los mismos?
En la firma cambiaria el parametro GeneralTree<Integer>,osea lo sacaria por que ya tengo la variable de instancia
y nada mas creo.

 */
import java.util.*;
import tp3.ejercicio1.*;
import tp1.ejercicio8.Queue;
public class RecorridosAG {
	/*
	 * Método que retorna una lista con los elementos impares del árbol “a” que sean mayores al valor “n” 
	pasados como parámetros, recorrido en preorden.
	 */
	public static List<Integer> numerosImparesMayoresQuePreOrden (GeneralTree <Integer> a, Integer n){
		List<Integer> lista=new ArrayList<Integer>();
		if( ((a.getData() % 2)==1) && a.getData()>n) {
			lista.add(a.getData());
		}
		if(a.hasChildren()) {
			List<GeneralTree<Integer>>children=a.getChildren();
			for(GeneralTree<Integer> child:children) {
				lista.addAll(numerosImparesMayoresQuePreOrden(child,n));
			}
		}
		return lista;
	}
	/*
	 * public List<Integer> numerosImparesMayoresQueInOrden (GeneralTree <Integer> a, Integer n)
	Método que retorna una lista con los elementos impares del árbol “a” que sean mayores al valor “n” 
	pasados como parámetros, recorrido en inorden.

	 */
	public static List<Integer>numerosImparesMayoresQueInOrden(GeneralTree<Integer>a,Integer n){
		List<Integer>lista=new ArrayList<Integer>();
		List<GeneralTree<Integer>>children=a.getChildren();
		if(!children.isEmpty()) {
			lista.addAll(numerosImparesMayoresQueInOrden(children.get(0),n));
		}
		if(a.getData()%2==1 && a.getData()>n) {
			lista.add(a.getData());
		}
		for(int i=1;i<children.size();i++) {
			lista.addAll(numerosImparesMayoresQueInOrden(children.get(i),n));
		}
		return lista;
	}
	/*
	 * public List<Integer> numerosImparesMayoresQuePostOrden (GeneralTree <Integer> a, Integer n)
	Método que retorna una lista con los elementos impares del árbol “a” que sean mayores al valor “n” 
	pasados como parámetros, recorrido en postorden.
	 */
	public static List<Integer>numerosImparesMayoresQuePostOrden(GeneralTree<Integer>a,Integer n){
		List<Integer>lista=new ArrayList<Integer>();
		if(a.hasChildren()) {
			List<GeneralTree<Integer>>children=a.getChildren();
			for(GeneralTree<Integer>child:children) {
				lista.addAll(numerosImparesMayoresQuePostOrden(child,n));
			}
		}
		if(a.getData()%2==1 && a.getData()>n) {
			lista.add(a.getData());
		}
		return lista;
	}
	
	/*
	 * public List<Integer> numerosImparesMayoresQuePorNiveles(GeneralTree <Integer> a, Integer n)
	Método que retorna una lista con los elementos impares del árbol “a” que sean mayores al valor “n”
 	pasados como parámetros, recorrido por niveles.
 
	 */
	 public static List<Integer> numerosImparesMayoresQuePorNiveles(GeneralTree <Integer> a, Integer n){
		 Queue<GeneralTree<Integer>>queue=new Queue<GeneralTree<Integer>>();
		 List<Integer>lista=new ArrayList<Integer>();
		 queue.enqueue(a);
		 queue.enqueue(null);
		 while(!queue.isEmpty()) {
			 GeneralTree<Integer>aux=queue.dequeue();
			 if(aux!=null) {
				 if((aux.getData()%2==1) && (aux.getData()>n)) {
					 lista.add(aux.getData());
				 }
				 if(aux.hasChildren()) {
					 List<GeneralTree<Integer>>children=aux.getChildren();
					 for(GeneralTree<Integer>child:children) {
						 queue.enqueue(child);
					 }
				 }
			 }
			 else {
				 if(!queue.isEmpty()) {
					 queue.enqueue(null);
				 }
			 }
		 }
		 return lista;
	 }
	public static void main(String[] args) {
		/*
				1
 			2  3
 
		4 5 6 7

		 */
		GeneralTree<Integer> arbol=new GeneralTree<Integer>(1);
		arbol.addChild(new GeneralTree<Integer>(2));
		arbol.addChild(new GeneralTree<Integer>(3));
		arbol.getChildren().get(0).addChild(new GeneralTree<Integer>(4));
		arbol.getChildren().get(0).addChild(new GeneralTree<Integer>(5));
		arbol.getChildren().get(0).addChild(new GeneralTree<Integer>(6));
		arbol.getChildren().get(0).addChild(new GeneralTree<Integer>(7));
		//arbol.preOrden();//1 2 4 
		//arbol.inOrden();//4 2 5 6 7 1 3
		//arbol.entreNiveles();
		List<Integer>lista=RecorridosAG.numerosImparesMayoresQuePorNiveles(arbol, 3);
		for(Integer l:lista) {
			System.out.println("Dato:"+l);
		}

	}

}
