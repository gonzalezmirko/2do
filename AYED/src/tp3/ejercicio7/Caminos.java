package tp3.ejercicio7;
/*
 * Dada una clase Caminos que contiene una variable de instancia de tipo GeneralTree de números enteros, 
 * implemente un método que retorne el camino a la hoja más lejana. En el caso de haber más de un camino 
 * máximo retorne el primero que encuentre.
 * El método debe tener la siguiente firma: public List<Integer> caminoAHojaMasLejana ()
	Por ejemplo, para el siguiente árbol, la lista a retornar seria: 12, 17, 6, 1 de longitud 3 
	(Los caminos 12, 15, 14, 16 y 12, 15, 14, 7 son también máximos, pero se pide el primero).

 */
import tp3.ejercicio1.*;
import java.util.*;
public class Caminos {
	private GeneralTree<Integer>arbol;
	
	public Caminos(GeneralTree<Integer>arbol) {
		this.arbol=arbol;
	}
	private boolean caminoAHojaMasLejanaRec(GeneralTree<Integer>arbol,List<Integer>lista,List<Integer>listaMax) {
		boolean encontreHoja=false;
		lista.add(arbol.getData());
		if(arbol.isLeaf()) {
			if(lista.size()>listaMax.size()) {
				listaMax.clear();
				listaMax.addAll(lista);
			}
		}
		else {
			for(GeneralTree<Integer>child:arbol.getChildren()) {
				encontreHoja=caminoAHojaMasLejanaRec(child,lista,listaMax);
			}
		}
		lista.remove(lista.size()-1);
		return encontreHoja;
	}
	public List<Integer> caminoAHojaMasLejana (){
		List<Integer>lista=new LinkedList<Integer>();
		List<Integer>listaMax=new LinkedList<Integer>();
		if(this.arbol!=null && !this.arbol.isEmpty()) {
			caminoAHojaMasLejanaRec(this.arbol,lista,listaMax);
		}
		return listaMax;
	}
	
	
	public int todosLosCaminosAHojaLejanaRec(GeneralTree<Integer>arbol,List<List<Integer>>lista,List<Integer>listaActual,int max) {
		listaActual.add(arbol.getData());
		if(arbol.isLeaf()) {
			if(listaActual.size()>max) {
				lista.clear();// Limpio todos los anteriores
				max=listaActual.size();
				lista.add(new LinkedList<Integer>(listaActual));//importantisiom hacer lo del new( // agrego el nuevo más largo)
			}
			else {
				if(listaActual.size()==max) {
					lista.add(new LinkedList<Integer>(listaActual));//importantisiom hacer lo del new(// si es igual, lo agrego también)
				}
			}
		}
		else {
			for(GeneralTree<Integer>child:arbol.getChildren()) {
				max=todosLosCaminosAHojaLejanaRec(child,lista,listaActual,max);
			}
		}
		listaActual.remove(listaActual.size()-1);
		return max;
	}
	public List<List<Integer>> todosLosCaminosAHojaMasLejana(){
		List<List<Integer>>lista=new LinkedList<List<Integer>>();
		if(this.arbol!=null && !this.arbol.isEmpty()) {
			todosLosCaminosAHojaLejanaRec(this.arbol,lista,new LinkedList<Integer>(),0);
		}
		return lista;
	}
	public static void main(String[] args) {
		/*
		 * 			12
		 * 		17      9      15
		 *    10  6     8    14 18
		 *        1        16  7
		 */
		GeneralTree<Integer>arbol=new GeneralTree<Integer>(12);
		arbol.addChild(new GeneralTree<Integer>(17));
		arbol.addChild(new GeneralTree<Integer>(9));
		arbol.addChild(new GeneralTree<Integer>(15));
		arbol.getChildren().get(0).addChild(new GeneralTree<Integer>(10));
		arbol.getChildren().get(0).addChild(new GeneralTree<Integer>(6));
		arbol.getChildren().get(1).addChild(new GeneralTree<Integer>(8));
		arbol.getChildren().get(2).addChild(new GeneralTree<Integer>(14));
		arbol.getChildren().get(2).addChild(new GeneralTree<Integer>(18));
		arbol.getChildren().get(0).getChildren().get(1).addChild(new GeneralTree<Integer>(1));
		arbol.getChildren().get(2).getChildren().get(0).addChild(new GeneralTree<Integer>(16));
		arbol.getChildren().get(2).getChildren().get(0).addChild(new GeneralTree<Integer>(7));
		Caminos cam=new Caminos(arbol);
		//arbol.entreNiveles();
		//System.out.println(cam.caminoAHojaMasLejana());
		System.out.println(cam.todosLosCaminosAHojaMasLejana());
		
	}

}
