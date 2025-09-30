package tp2.ejercicio8;
/*
Ejercicio 8
Escribir en una clase ParcialArboles el método público con la siguiente firma: 
public boolean esPrefijo(BinaryTree<Integer> arbol1, BinaryTree<Integer> arbol2)
El método devuelve true si arbol1 es prefijo de arbol2, false en caso contrario.
Se dice que un árbol binario arbol1 es prefijo de otro árbol binario arbol2, 
cuando arbol1 coincide con la parte inicial del árbol arbol2 tanto en el contenido de los elementos 
como en su estructura. Por ejemplo, en la siguiente imagen: arbol1 ES prefijo de arbol2.

 */
import tp2.ejercicio1.BinaryTree;
public class ParcialArboles {
	private boolean esPrefijoRec(BinaryTree<Integer> arbol1,BinaryTree<Integer> arbol2) {
		if(!arbol1.getData().equals(arbol2.getData())) {
			return false;
		}
		boolean izq=true;
		boolean der=true;
		if(arbol1.hasLeftChild()) {
			if(!arbol2.hasLeftChild()) {
				return false;
			}
			izq=esPrefijoRec(arbol1.getLeftChild(),arbol2.getLeftChild());
		}
		if(arbol1.hasRitghChild()) {
			if(!arbol2.hasRitghChild()) {
				return false;
			}
			der=esPrefijoRec(arbol1.getRightChild(),arbol2.getRightChild());
		}
		return izq&&der;
	}
	public boolean esPrefijo(BinaryTree<Integer> arbol1,BinaryTree<Integer> arbol2) {
		if(arbol1.isEmpty()) {
			return true;
		}
		return esPrefijoRec(arbol1,arbol2);

	}
	public static void main(String[]args) {
		BinaryTree<Integer>arbol1=new BinaryTree<Integer>(65);
		
		arbol1.addLeftChild(new BinaryTree<Integer>(37));
		arbol1.addRightChild(new BinaryTree<Integer>(81));
		
		arbol1.getLeftChild().addRightChild(new BinaryTree<Integer>(47));
		arbol1.getRightChild().addRightChild(new BinaryTree<Integer>(93));
		
		BinaryTree<Integer>arbol2=new BinaryTree<Integer>(65);
		
		arbol2.addLeftChild(new BinaryTree<Integer>(37));
		arbol2.addRightChild(new BinaryTree<Integer>(81));
		
		arbol2.getLeftChild().addLeftChild(new BinaryTree<Integer>(22));
		arbol2.getLeftChild().addRightChild(new BinaryTree<Integer>(47));
		arbol2.getRightChild().addLeftChild(new BinaryTree<Integer>(76));
		arbol2.getRightChild().addRightChild(new BinaryTree<Integer>(93));
		
		ParcialArboles par=new ParcialArboles();
		System.out.println(par.esPrefijo(arbol1,arbol2));
	}
}
