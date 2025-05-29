package parcial5;

import tp2.ejercicio1.*;
public class ParcialParcial {
	private BinaryTree<Integer>arbol;
	
	public ParcialParcial(BinaryTree<Integer>arbol) {
		this.arbol=arbol;
	}
	public BinaryTree<Integer>nuevoTree(){
		BinaryTree<Integer>nuevoArbol=new BinaryTree<Integer>();
		if(this.arbol!=null && !this.arbol.isEmpty()) {
			return nuevoTreeRec(this.arbol);
		}
		return nuevoArbol;
	}
	private BinaryTree<Integer>nuevoTreeRec(BinaryTree<Integer>arbol){
		
		int sumaIzq=0;
		
		BinaryTree<Integer>nuevoArbol=new BinaryTree<Integer>(arbol.getData());
		BinaryTree<Integer> izq=new BinaryTree<Integer>();
		BinaryTree<Integer> der=new BinaryTree<Integer>(); 
		if(arbol.hasLeftChild()) {
			sumaIzq=arbol.getLeftChild().getData();
			//nuevoArbol.addLeftChild(nuevoTreeRec(arbol.getLeftChild()));
			izq.setData(sumaIzq+arbol.getData());
		}
		if(arbol.hasRitghChild()) {
			//nuevoArbol.addRightChild(nuevoTreeRec(arbol.getRightChild()));
			der.setData(arbol.getRightChild().getData());
			//der=nuevoTreeRec(arbol.getRightChild());
		}
		nuevoArbol.addLeftChild(izq);
		nuevoArbol.addRightChild(der);
		return nuevoArbol;
	}
	/*           5
	 
	/  \
	3      2/ \     \
	6      8     9

	 */

	    public static void main(String[]args) {
	        BinaryTree<Integer> arbol = new BinaryTree<Integer>(5);

	        arbol.addLeftChild(new BinaryTree<Integer>(3));
	        arbol.addRightChild(new BinaryTree<Integer>(2));

	        arbol.getLeftChild().addLeftChild(new BinaryTree<Integer>(6));
	        arbol.getLeftChild().addRightChild(new BinaryTree<Integer>(8));
	        arbol.getRightChild().addRightChild(new BinaryTree<Integer>(9));
	        ParcialParcial ar=new ParcialParcial(arbol);
	        //arbol.entreNiveles(0, 2);
	        BinaryTree<Integer>nuevoParcial=ar.nuevoTree();
	        nuevoParcial.entreNiveles(0, 2);
	    }
}
