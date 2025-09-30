package tp3.ejercicio9;
/*
 * Implemente en la clase ParcialArboles el método:
public static boolean esDeSeleccion (GeneralTree<Integer> arbol)
que devuelve true si el árbol recibido por parámetro es de selección, falso sino lo es.
Un árbol general es de selección si cada nodo tiene en su raíz el valor del menor de sus hijos.
Por ejemplo, para el siguiente árbol se debería retornar: true

 */
import tp3.ejercicio1.*;
import java.util.*;
public class ParcialArboles {
	private static boolean esDeSeleccionRec(GeneralTree<Integer>arbol) {
		if(arbol.isLeaf()) {
			return true;
		}
		int minChild=Integer.MAX_VALUE;
		boolean ok=true;
		for(GeneralTree<Integer>child:arbol.getChildren()) {
			minChild=Math.min(minChild, child.getData());
			if(!esDeSeleccionRec(child)) {
				return false;
			}
		}
		return minChild==arbol.getData() && ok;
	}
	public static boolean esDeSeleccion (GeneralTree<Integer> arbol) {
		boolean ok=false;
		if(arbol!=null && !arbol.isEmpty()) {
			return esDeSeleccionRec(arbol);
		}
		return ok;
	}
	public static void main(String[] args) {
		GeneralTree<Integer>arbol=new GeneralTree<Integer>(12);
		arbol.addChild(new GeneralTree<Integer>(12));
		arbol.addChild(new GeneralTree<Integer>(25));
		arbol.getChildren().get(0).addChild(new GeneralTree<Integer>(35));
		arbol.getChildren().get(0).addChild(new GeneralTree<Integer>(12));
		arbol.getChildren().get(1).addChild(new GeneralTree<Integer>(25));
		arbol.getChildren().get(0).getChildren().get(0).addChild(new GeneralTree<Integer>(35));
		arbol.getChildren().get(0).getChildren().get(1).addChild(new GeneralTree<Integer>(14));
		arbol.getChildren().get(0).getChildren().get(1).addChild(new GeneralTree<Integer>(12));
		arbol.getChildren().get(0).getChildren().get(1).addChild(new GeneralTree<Integer>(33));
		arbol.getChildren().get(0).getChildren().get(0).getChildren().get(0).addChild(new GeneralTree<Integer>(35));
		arbol.getChildren().get(0).getChildren().get(1).getChildren().get(2).addChild(new GeneralTree<Integer>(35));
		arbol.getChildren().get(0).getChildren().get(1).getChildren().get(2).addChild(new GeneralTree<Integer>(83));
		arbol.getChildren().get(0).getChildren().get(1).getChildren().get(2).addChild(new GeneralTree<Integer>(90));
		arbol.getChildren().get(0).getChildren().get(1).getChildren().get(2).addChild(new GeneralTree<Integer>(33));
		//arbol.entreNiveles();
		if(esDeSeleccion(arbol)) {
			System.out.println("El arbol es de seleccion.");
		}else {
			System.out.println("El arbol no es de seleccion.");
		}
	}

}
