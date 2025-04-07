package tp2.ejercicioo3;

import java.util.*;
import tp2.ejercicio1.*;
public class ContadorArbol {
	private BinaryTree<Integer> arbol;
	
	public ContadorArbol(BinaryTree<Integer> arbol) {
		this.arbol=arbol;
	}
	private void agregarPar(List<Integer> lista,int numero) {
		if(esPar(numero)) {
			lista.add(numero);
		}
	}
	private boolean esPar(int n) {
		return (n%2)==0;
	}
	//De forma InOrden(lo estaba pensando mal xd)
	private void elementosPares(BinaryTree<Integer>ab,List<Integer>lista) {
		if(ab.isEmpty()) {
			lista.add(0);
		}
		else {
			if(ab.hasLeftChild()) {
				//int numero=ab.getLeftChild().getData();
				//agregarPar(lista,numero);
				this.elementosPares(ab.getLeftChild(),lista);
			}
			//primero evalua hijo izquierdo , despues raiz y por ultimo hijo derecho
			//El PostOrden cambiaria unicamente donde evaluar la raiz
			if(esPar(ab.getData())) {
				agregarPar(lista,ab.getData());
			}
			if(ab.hasRitghChild()) {
				//int numero=ab.getRightChild().getData();
				//agregarPar(lista,numero);
				this.elementosPares(ab.getRightChild(),lista);
			}
		}
	}
	public List<Integer> numerosPares(){
		if(this.arbol.isEmpty()) {
			return null;
		}
		List<Integer>lista=new ArrayList<Integer>();
		elementosPares(this.arbol,lista);
		return lista;
	}
	
	public static void main(String[] args) {
		BinaryTree<Integer>ab=new BinaryTree<Integer>(10);//raiz
		ab.addLeftChild(new BinaryTree<Integer>(20));//hijo izq
		ab.addRightChild(new BinaryTree<Integer>(30));//hijo der
		ab.getLeftChild().addLeftChild(new BinaryTree<Integer>(40));//hijo izq hijo izq
		ab.getLeftChild().addRightChild(new BinaryTree<Integer>(50));//hijo izq hijo der
		/*
		 	10
		 20		30
		40 50
		 */
		
		ContadorArbol contador=new ContadorArbol(ab);
		List<Integer> lista=contador.numerosPares();
		System.out.println("Lista con numeros Pares:");
		for(Integer elem:lista) {
			System.out.println("Elemento:"+elem);
		}
	}
}

