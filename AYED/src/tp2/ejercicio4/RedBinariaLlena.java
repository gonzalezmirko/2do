package tp2.ejercicio4;

import tp1.ejercicio8.Queue;
import tp2.ejercicio1.BinaryTree;

/*
Una red binaria es una red que posee una topología de árbol binario lleno. Por ejemplo:
Los nodos que conforman una red binaria llena tiene la particularidad de que todos ellos conocen cuál es 
su retardo de reenvío. El retardo de reenvío se define como el período comprendido entre que un nodo recibe un mensaje 
y lo reenvía a sus dos hijos.
Su tarea es calcular el mayor retardo posible, en el camino que realiza un mensaje desde la raíz hasta llegar a 
las hojas en una red binaria llena. En el ejemplo, debería retornar 10+3+9+12=34 (Si hay más de un máximo retorne 
el último valor hallado).
Nota: asuma que cada nodo tiene el dato de retardo de reenvío expresado en cantidad de segundos.
a)	Indique qué estrategia (recorrido en profundidad o por niveles) utilizará para resolver el problema.
b)	Cree una clase Java llamada RedBinariaLlena donde implementará lo solicitado en el método retardoReenvio():int

 */
public class RedBinariaLlena {
	private BinaryTree<Integer> arbol;
	
	public RedBinariaLlena(BinaryTree<Integer> arbol) {
		this.arbol=arbol;
	}
	public int retardoReenvio() {
		if(this.arbol.isEmpty()) {
			return 0;
		}
		else {
			int suma=0;
			Integer max=Integer.MIN_VALUE;
			Queue<BinaryTree<Integer>>cola=new Queue<BinaryTree<Integer>>();
			cola.enqueue(this.arbol);
			cola.enqueue(null);
			while(!cola.isEmpty()) {
				BinaryTree<Integer>ab=cola.dequeue();
				if(ab!=null) {
					if(ab.getData()>=max) {
						max=ab.getData();
					}
					if(ab.hasLeftChild()) {
						cola.enqueue(ab.getLeftChild());
					}
					if(ab.hasRitghChild()) {
						cola.enqueue(ab.getRightChild());
					}
				}
				else {
					suma+=max;
					max=Integer.MIN_VALUE;
					if(!cola.isEmpty()) {
						cola.enqueue(null);
					}
				}
			}
			return suma;
		}
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
		//arbol.entreNiveles(0, 3);
		RedBinariaLlena red=new RedBinariaLlena(arbol);
		System.out.println("----->Retardo Envio:"+red.retardoReenvio());
	}
}
