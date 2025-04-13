package tp2.ejercicio6;

import tp2.ejercicio1.BinaryTree;

/*
 Cree una clase Java llamada Transformacion que tenga como variable de instancia un árbol binario de números enteros 
 y un método de instancia suma (): BinaryTree<Integer> el cuál devuelve el árbol en el que se reemplazó el valor 
 de cada nodo por la suma de todos los elementos presentes en su subárbol izquierdo y derecho. 
 Asuma que los valores de los subárboles vacíos son ceros. Por ejemplo:
 ¿Su solución recorre una única vez cada subárbol? En el caso que no, ¿Puede mejorarla para que sí lo haga?
 */
public class Transformacion {
	private BinaryTree<Integer>arbol;
	
	public Transformacion(BinaryTree<Integer> arbol) {
		this.arbol=arbol;
	}
	//Modifico el arbol de la variable de instancia por que no me salio hacer un nuevo arbol.-->ya me salio 
	private int sumaRecursiva(BinaryTree<Integer>ab){
		int sumaTotal=0;
		//creo que podria tener una sola variable pero queda mas legible asi
		int sumaIzq=0;
		int sumaDer=0;
		//Si es hoja sumo (seteo en 0 y me tengo q guardar el dato antes)
		if(ab.isLeaf()) {
			sumaTotal=ab.getData();
			ab.setData(0);
			return sumaTotal;
		}
		//suma de hijos izquierdos
		if(ab.hasLeftChild()) {
			sumaIzq=sumaRecursiva(ab.getLeftChild());
		}
		if(ab.hasRitghChild()) {
			sumaDer=sumaRecursiva(ab.getRightChild());
		}
		sumaTotal =sumaIzq+sumaDer;
		//me quedo con el dato actual
		int aux=ab.getData();
		ab.setData(sumaTotal);
		return sumaTotal+aux;
		
	}
	
	//Forma alternativa de hacerlo
	public BinaryTree<Integer>sumaRecursivaAlternativa(BinaryTree<Integer>ab){
		//podria ser sin parametros 
		int suma=0;
		BinaryTree<Integer>nuevoArbol=new BinaryTree<Integer>();
		if(ab.isLeaf()) {
			nuevoArbol.setData(0);
		}
		if(ab.hasLeftChild()) {
			BinaryTree<Integer> izq = sumaRecursivaAlternativa(ab.getLeftChild());
			suma+= izq.getData()+ab.getLeftChild().getData();
			nuevoArbol.addLeftChild(izq);
		}
		if(ab.hasRitghChild()) {
			BinaryTree<Integer>der=sumaRecursivaAlternativa(ab.getRightChild()); 
			suma +=der.getData()+ab.getRightChild().getData();
			nuevoArbol.addRightChild(der);
		}
		nuevoArbol.setData(suma);
		return nuevoArbol;
	}
	public BinaryTree<Integer>suma(){
		if(this.arbol.isEmpty()) {
			return null;
		}
		//sumaRecursiva(this.arbol);
		return sumaRecursivaAlternativa(this.arbol);
	}
	public static void main(String[] args) {
		/*
					  |1|
				|2|         |3|
			   	  |4|     |5|  |6|
		  				|7| |8|		
		 */
		BinaryTree<Integer>arbol=new BinaryTree<Integer>(1);
		
		BinaryTree<Integer>hijoIzq=new BinaryTree<Integer>(2);
		BinaryTree<Integer>hijoDer=new BinaryTree<Integer>(3);
		
		BinaryTree<Integer>subHijoIzqDer=new BinaryTree<Integer>(4);
		BinaryTree<Integer>subHijoDerIzq=new BinaryTree<Integer>(5);
		BinaryTree<Integer>subHijoDerDer=new BinaryTree<Integer>(6);
		
		subHijoDerIzq.addLeftChild(new BinaryTree<Integer>(7));
		subHijoDerIzq.addRightChild(new BinaryTree<Integer>(8));
		hijoIzq.addRightChild(subHijoIzqDer);
		hijoDer.addLeftChild(subHijoDerIzq);
		hijoDer.addRightChild(subHijoDerDer);
		arbol.addLeftChild(hijoIzq);
		arbol.addRightChild(hijoDer);
		//arbol.printInOrden();
		Transformacion transformacion=new Transformacion(arbol);
		//arbol.printInOrden();			
		BinaryTree<Integer>ab=transformacion.suma();
		ab.entreNiveles(0, 4);;
	}

}
