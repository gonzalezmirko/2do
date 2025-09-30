package tp2.ejercicio9;
/*
 * Escribir en una clase ParcialArboles el método público con la siguiente firma: 
 * public BinaryTree<?> sumAndDif(BinaryTree<Integer> arbol)
El método recibe un árbol binario de enteros y devuelve un nuevo árbol que contenga en cada nodo dos tipos de información:
●	La suma de los números a lo largo del camino desde la raíz hasta el nodo actual.
●	La diferencia entre el número almacenado en el nodo original y el número almacenado en el nodo padre.

 */
import tp2.ejercicio1.*;
public class ParcialArboles {
	private BinaryTree<SumAndDif> sumAndDifRec(BinaryTree<Integer> arbol,int sumaAcumulada,Integer padre){
		int valorActual=arbol.getData();
		int suma=sumaAcumulada+valorActual;
		int dif=valorActual;
		if(padre!=null) {
			dif=valorActual-padre;
		}
		SumAndDif sad=new SumAndDif(suma,dif);//
		BinaryTree<SumAndDif>nuevoArbol=new BinaryTree<SumAndDif>(sad);
		if(arbol.hasLeftChild()) {
			BinaryTree<SumAndDif> izq=sumAndDifRec(arbol.getLeftChild(),suma,valorActual);
			nuevoArbol.addLeftChild(izq);
		}
		if(arbol.hasRitghChild()) {
			BinaryTree<SumAndDif> der=sumAndDifRec(arbol.getRightChild(),suma,valorActual);
			nuevoArbol.addRightChild(der);
		}
		return nuevoArbol;
	}
	public BinaryTree<SumAndDif> sumAndDif(BinaryTree<Integer> arbol){
		if(arbol.isEmpty()) {
			return null;
		}
		return sumAndDifRec(arbol,0,null);
	}
	public static void main(String[] args) {
		BinaryTree<Integer>arbol=new BinaryTree<Integer>(20);
		
		arbol.addLeftChild(new BinaryTree<Integer>(5));
		arbol.addRightChild(new BinaryTree<Integer>(30));
		
		arbol.getLeftChild().addLeftChild(new BinaryTree<Integer>(-5));
		arbol.getLeftChild().addRightChild(new BinaryTree<Integer>(10));
		arbol.getRightChild().addLeftChild(new BinaryTree<Integer>(50));
		arbol.getRightChild().addRightChild(new BinaryTree<Integer>(-9));
		
		arbol.getLeftChild().getRightChild().addLeftChild(new BinaryTree<Integer>(1));
		arbol.getRightChild().getLeftChild().addRightChild(new BinaryTree<Integer>(4));
		
		arbol.getRightChild().getLeftChild().getRightChild().addRightChild(new BinaryTree<Integer>(6));
		
		ParcialArboles par=new ParcialArboles();
		BinaryTree<SumAndDif> arbolNuevo=par.sumAndDif(arbol);
		arbolNuevo.entreNiveles(0, 4);
	}

}
