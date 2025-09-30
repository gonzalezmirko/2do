package tp2.ejercicio7;

import tp2.ejercicio1.BinaryTree;

/*
 * Escribir en una clase ParcialArboles que contenga UNA ÚNICA variable de instancia de tipo BinaryTree 
 * de valores enteros NO repetidos y el método público con la siguiente firma:
public boolean isLeftTree (int num)
El método devuelve true si el subárbol cuya raíz es “num”, tiene en su subárbol izquierdo una cantidad mayor 
estricta de árboles con un único hijo que en su subárbol derecho. Y false en caso contrario. Consideraciones:
●	Si “num” no se encuentra en el árbol, devuelve false.
●	Si el árbol con raíz “num” no cuenta con una de sus ramas, considere que en esa rama hay -1 árboles con único hijo.
Por ejemplo, con un árbol como se muestra en la siguiente imagen:


 */
public class ParcialArboles {
	private BinaryTree<Integer>arbol;
	public ParcialArboles(BinaryTree<Integer>arbol) {
		this.arbol=arbol;
	}
	private int contarNodos(BinaryTree<Integer>ab) {//se puede hacer mas facil pero me salio asi :)
		if(ab.hasLeftChild()) {
			if(!ab.hasRitghChild()) {
				return 1+contarNodos(ab.getLeftChild());
			}
			return contarNodos(ab.getLeftChild());
		}
		if(ab.hasRitghChild()) {
			if(!ab.hasLeftChild()) {
				return 1+contarNodos(ab.getRightChild());
			}
			return contarNodos(ab.getRightChild());
		}
		return 0;
	}
	private boolean isLeftTreeRecursivo(int num,BinaryTree<Integer>ab) {
		int izq=0;
		int der=0;
		if(ab.getData()==num) {
			if(ab.hasLeftChild()) {
				izq=contarNodos(ab.getLeftChild());
			}
			else {
				izq=-1;
			}
			if(ab.hasRitghChild()) {
				der=contarNodos(ab.getRightChild());
			}
			else {
				der=-1;
			}
			System.out.println("Izquierda: "+izq);
			System.out.println("Derecha: "+der);
			return izq>der;
		}
		if(ab.hasLeftChild()) {
			if(isLeftTreeRecursivo(num,ab.getLeftChild())) {//si encontre el numero en el arbol
				return true;
			}
		}
		if(ab.hasRitghChild()) {
			if(isLeftTreeRecursivo(num,ab.getRightChild())) {//igual
				return true;
			}
		}
		return false;
	}
	public boolean isLeftTree(int num) {
		if(this.arbol.isEmpty()) {
			return false;
		}
		return isLeftTreeRecursivo(num,this.arbol);
	}
	public static void main(String[] args) {
		/*
		 						|2|
		 				|7|				  |-5|
		 		 |23|	    |6|	     |19|
		 	  |-3|	    |55|   |11|		  |4|
		 						      |18|
		 */	
		BinaryTree<Integer>arbol=new BinaryTree<Integer>(2);
		
		arbol.addLeftChild(new BinaryTree<Integer>(7));
		arbol.addRightChild(new BinaryTree<Integer>(-5));
		
		arbol.getLeftChild().addLeftChild(new BinaryTree<Integer>(23));
		arbol.getLeftChild().addRightChild(new BinaryTree<Integer>(6));
		arbol.getRightChild().addLeftChild(new BinaryTree<Integer>(19));
		
		arbol.getLeftChild().getLeftChild().addLeftChild(new BinaryTree<Integer>(-3));
		arbol.getLeftChild().getRightChild().addLeftChild(new BinaryTree<Integer>(55));
		arbol.getLeftChild().getRightChild().addRightChild(new BinaryTree<Integer>(11));
		arbol.getRightChild().getLeftChild().addRightChild(new BinaryTree<Integer>(4));
		
		arbol.getRightChild().getLeftChild().getRightChild().addLeftChild(new BinaryTree<Integer>(18));
		//arbol.entreNiveles(0, 4);
		ParcialArboles par=new ParcialArboles(arbol);
		System.out.println(par.isLeftTree(-3));
		/*
		 Prueba://7,2,-5,13,-3
		 7 --> 1>0 :true
		 2 --> 1>3 :false
		 -5 --> 2>-1 :true
		 19 --> -1>1 :false
		 -3 --> -1>-1 :false
		 */
	}

}
