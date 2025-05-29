package tp3.ejercicio10;
/*
 * Implemente la clase ParcialArboles, y el método:
public static List<Integer> resolver(GeneralTree<Integer> arbol)
que recibe un árbol general de valores enteros, que solo pueden ser 0 o 1 y 
devuelve una lista con los valores que componen el “camino filtrado de valor máximo”, 
se llama “filtrado” porque sólo se agregan al camino los valores iguales a 1 (los 0 no se agregan),
 mientras que es “de valor máximo” porque se obtiene de realizar el siguiente cálculo: es la suma de los 
 valores de los nodos multiplicados por su nivel. De haber más de uno, devolver el primero que se encuentre.
Por ejemplo, para el árbol general que aparece en el gráfico, el resultado de la invocación al método resolver 
debería devolver una lista con los valores: 1, 1, 1 , y NO 1, 0 , 1, 1 dado que filtramos el valor 0.
Con esa configuración se obtiene el mayor valor según el cálculo: 1*0 + 0*1 + 1*2 + 1*3
(El camino 1*0+1*1+0*2+0*3+1*4 también da 5, pero no es el primero)
Nota: No puede generar la lista resultado con 0 / 1 y en un segundo recorrido eliminar los elementos con valor 0

 */
import java.util.*;
import tp3.ejercicio1.*;
public class ParcialArboles {
	public static void resolverRec(GeneralTree<Integer>arbol,List<Integer>mejorCamino,int nivel,List<Integer>caminoActual,Maximo max,int sumaParcial){
		int valor=arbol.getData();
		
		if(valor==1) {
			caminoActual.add(1);
		}
		sumaParcial+=valor*nivel;
		
		if(arbol.isLeaf()) {
			if(sumaParcial>max.getMax()) {
				mejorCamino.clear();
				mejorCamino.addAll(caminoActual);
				max.setMax(sumaParcial);;
			}
		}
		for(GeneralTree<Integer>child:arbol.getChildren()) {
			resolverRec(child,mejorCamino,nivel+1,caminoActual,max,sumaParcial);
		}
		if(valor==1) {
			caminoActual.remove(caminoActual.size()-1);
		}
	}
	public static List<Integer> resolver(GeneralTree<Integer> arbol){
		List<Integer>mejorCamino=new LinkedList<Integer>();
		if(arbol!=null && !arbol.isEmpty()) {
			resolverRec(arbol,mejorCamino,0,new LinkedList<Integer>(),new Maximo(),0);
		}
		return mejorCamino;
	}
	public static void main(String[] args) {
		GeneralTree<Integer>arbol=new GeneralTree<Integer>(1);
		arbol.addChild(new GeneralTree<Integer>(0));
		arbol.addChild(new GeneralTree<Integer>(1));
		arbol.addChild(new GeneralTree<Integer>(1));
		arbol.getChildren().get(0).addChild(new GeneralTree<Integer>(1));
		arbol.getChildren().get(0).addChild(new GeneralTree<Integer>(1));
		arbol.getChildren().get(1).addChild(new GeneralTree<Integer>(1));
		arbol.getChildren().get(1).addChild(new GeneralTree<Integer>(0));
		arbol.getChildren().get(2).addChild(new GeneralTree<Integer>(0));
		arbol.getChildren().get(0).getChildren().get(0).addChild(new GeneralTree<Integer>(1));
		arbol.getChildren().get(0).getChildren().get(0).addChild(new GeneralTree<Integer>(1));
		arbol.getChildren().get(0).getChildren().get(0).addChild(new GeneralTree<Integer>(1));	
		arbol.getChildren().get(1).getChildren().get(1).addChild(new GeneralTree<Integer>(0));
		arbol.getChildren().get(2).getChildren().get(0).addChild(new GeneralTree<Integer>(0));
		arbol.getChildren().get(1).getChildren().get(1).getChildren().get(0).addChild(new GeneralTree<Integer>(1));
		arbol.getChildren().get(2).getChildren().get(0).getChildren().get(0).addChild(new GeneralTree<Integer>(0));
		arbol.getChildren().get(2).getChildren().get(0).getChildren().get(0).addChild(new GeneralTree<Integer>(0));
		//arbol.entreNiveles();
		System.out.println("Mejor Camino:"+resolver(arbol));
	}

}
