package tp3.ejercicio11;
/*
 * Implemente en la clase ParcialArboles el método:
public static boolean resolver(GeneralTree<Integer> arbol) que devuelve true si el árbol es creciente, 
falso sino lo es.
Un árbol general es creciente si para cada nivel del árbol la cantidad de nodos que hay en ese nivel 
es exactamente igual a la cantidad de nodos del nivel anterior + 1.
Por ejemplo, para el siguiente árbol, se debería retornar true

 */
import tp3.ejercicio1.*;
import tp1.ejercicio8.Queue;
import java.util.*;
public class ParcialArboles {
	private static boolean resolverEntreNiveles(GeneralTree<Integer>arbol) {
		Queue<GeneralTree<Integer>>queue=new Queue<GeneralTree<Integer>>();
		boolean ok=true;
		int cantNodos=0;
		queue.enqueue(arbol);
		queue.enqueue(null);
		while(!queue.isEmpty() && ok) {
			GeneralTree<Integer>ab=queue.dequeue();
			if(ab!=null) {
				cantNodos++;//1
				for(GeneralTree<Integer>child:ab.getChildren()) {
					queue.enqueue(child);
				}
			}
			else {
				System.out.println("CantNodos: "+cantNodos);
				System.out.println("Tamañao de la cola -null y -1 dato: "+(queue.size()-2));
				if(cantNodos==queue.size()-2) {
					ok=false;
				}
				if(!queue.isEmpty()) {
					queue.enqueue(null);
					cantNodos=0;
				}
			}
		}
		return ok;
	}
	public static boolean resolver(GeneralTree<Integer> arbol) {
		if(arbol!=null && !arbol.isEmpty()) {
			return resolverEntreNiveles(arbol);
		}
		return false;
	}
	public static void main(String[] args) {
		GeneralTree<Integer>arbol=new GeneralTree<Integer>(2);
		arbol.addChild(new GeneralTree<Integer>(1));
		arbol.addChild(new GeneralTree<Integer>(25));
		//arbol.addChild(new GeneralTree<Integer>(25));
		arbol.getChildren().get(0).addChild(new GeneralTree<Integer>(5));
		arbol.getChildren().get(0).addChild(new GeneralTree<Integer>(4));
		arbol.getChildren().get(1).addChild(new GeneralTree<Integer>(13));
		arbol.getChildren().get(0).getChildren().get(0).addChild(new GeneralTree<Integer>(18));
		arbol.getChildren().get(0).getChildren().get(1).addChild(new GeneralTree<Integer>(7));
		arbol.getChildren().get(0).getChildren().get(1).addChild(new GeneralTree<Integer>(11));
		arbol.getChildren().get(0).getChildren().get(1).addChild(new GeneralTree<Integer>(3));
		arbol.getChildren().get(0).getChildren().get(0).getChildren().get(0).addChild(new GeneralTree<Integer>(83));
		arbol.getChildren().get(0).getChildren().get(1).getChildren().get(2).addChild(new GeneralTree<Integer>(33));
		arbol.getChildren().get(0).getChildren().get(1).getChildren().get(2).addChild(new GeneralTree<Integer>(12));
		arbol.getChildren().get(0).getChildren().get(1).getChildren().get(2).addChild(new GeneralTree<Integer>(17));
		arbol.getChildren().get(0).getChildren().get(1).getChildren().get(2).addChild(new GeneralTree<Integer>(9));
		//arbol.entreNiveles();
		if(resolver(arbol)) {
			System.out.println("El arbol es creciente.");
		}
		else {
			System.out.println("El arbol no es creciente.");
		}
	}

}
