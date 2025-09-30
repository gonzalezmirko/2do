package tp2.ejercicio5;

import tp2.ejercicio1.BinaryTree;

/*
 * Implemente una clase Java llamada ProfundidadDeArbolBinario que tiene como variable de instancia un árbol binario
 * de números enteros y un método de instancia sumaElementosProfundidad (int p):int el cuál 
 * devuelve la suma de todos los nodos del árbol que se encuentren a la profundidad pasada como argumento.
 */
public class ProfundidadDeArbolBinario {
	private BinaryTree<Integer>arbol;
	
	public ProfundidadDeArbolBinario(BinaryTree<Integer>arbol) {
		this.arbol=arbol;
	}
	private int sumaElementosProfundidadRecursiva(BinaryTree<Integer>ab,int p,int i) {
		if(ab.isEmpty()) {
			return 0;
		}
		else {
			if(p==i) {//si es la profundidad sumamos
				return ab.getData();
			}
			int sumaIzq=0;
			if(ab.hasLeftChild()) {
				sumaIzq =sumaElementosProfundidadRecursiva(ab.getLeftChild(),p,i+1);
			}
			int sumaDer=0;
			if(ab.hasRitghChild()) {
				sumaDer =sumaElementosProfundidadRecursiva(ab.getRightChild(),p,i+1);	
			}
			//suma Total
			return sumaIzq+sumaDer;
		}
	}
	public int sumaElementosProfundidad(int p) {
		if(arbol.isEmpty()) {
			return 0;
		}
		return sumaElementosProfundidadRecursiva(this.arbol,p,0);
	}
	public static void main(String[]args) {
		/*
	     			|10|
     			|2|         |3|
  			|5|   |4|    |9|   |8|
		|7|  |8||5||6||12||8||2||1|
		 	*/
		BinaryTree<Integer>arbol=new BinaryTree<Integer>(10);
		BinaryTree<Integer>hijoIzq=new BinaryTree<Integer>(2);
		BinaryTree<Integer>hijoDer=new BinaryTree<Integer>(3);
		BinaryTree<Integer>subHijoIzqIzq=new BinaryTree<Integer>(5);
		BinaryTree<Integer>subHijoIzqDer=new BinaryTree<Integer>(4);
		BinaryTree<Integer>subHijoDerIzq=new BinaryTree<Integer>(9);
		BinaryTree<Integer>subHijoDerDer=new BinaryTree<Integer>(8);
		subHijoIzqIzq.addLeftChild(new BinaryTree<Integer>(7));
		subHijoIzqIzq.addRightChild(new BinaryTree<Integer>(8));
		subHijoIzqDer.addLeftChild(new BinaryTree<Integer>(5));
		subHijoIzqDer.addRightChild(new BinaryTree<Integer>(6));
		subHijoDerIzq.addLeftChild(new BinaryTree<Integer>(12));
		subHijoDerIzq.addRightChild(new BinaryTree<Integer>(8));
		subHijoDerDer.addLeftChild(new BinaryTree<Integer>(2));
		subHijoDerDer.addRightChild(new BinaryTree<Integer>(1));
		hijoIzq.addLeftChild(subHijoIzqIzq);
		hijoIzq.addRightChild(subHijoIzqDer);
		hijoDer.addLeftChild(subHijoDerIzq);
		hijoDer.addRightChild(subHijoDerDer);
		arbol.addLeftChild(hijoIzq);
		arbol.addRightChild(hijoDer);
		ProfundidadDeArbolBinario prof=new ProfundidadDeArbolBinario(arbol);
		System.out.println(prof.sumaElementosProfundidad(2));
		//arbol.printInOrden();
	}
}
