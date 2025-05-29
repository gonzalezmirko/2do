package parcial1;

import tp3.ejercicio1.*;
import java.util.*;
public class ParcialArboles {
	private GeneralTree<Integer>arbol;
	
	public ParcialArboles(GeneralTree<Integer>arbol) {
		this.arbol=arbol;
	}
	private boolean caminoRec(GeneralTree<Integer>arbol,List<Integer>lista,int n) {
		lista.add(arbol.getData());
		if(!arbol.isLeaf() && arbol.getChildren().size()<n) {
			lista.remove(lista.size()-1);
			return false;
		}
		if(arbol.isLeaf()) {
			return true;
		}
		for(GeneralTree<Integer>child:arbol.getChildren()) {
			boolean ok=caminoRec(child,lista,n);
			if(ok) {
				return true;
			}
		}
		lista.remove(lista.size()-1);
		return false;
	}
	public List<Integer>camino(int n){
		List<Integer>lista=new LinkedList<Integer>();
		if(this.arbol!=null && !this.arbol.isEmpty()) {
			caminoRec(this.arbol,lista,n);
		}
		return lista;
	}
	public static void main(String[] args) {
		GeneralTree<Integer>arbol=new GeneralTree<Integer>(10);
		arbol.addChild(new GeneralTree<Integer>(8));
		arbol.addChild(new GeneralTree<Integer>(42));
		arbol.addChild(new GeneralTree<Integer>(-5));
		arbol.getChildren().get(0).addChild(new GeneralTree<Integer>(5));
		arbol.getChildren().get(0).addChild(new GeneralTree<Integer>(22));
		arbol.getChildren().get(2).addChild(new GeneralTree<Integer>(19));
		arbol.getChildren().get(2).addChild(new GeneralTree<Integer>(-9));
		arbol.getChildren().get(0).getChildren().get(0).addChild(new GeneralTree<Integer>(-6));
		arbol.getChildren().get(0).getChildren().get(1).addChild(new GeneralTree<Integer>(28));
		arbol.getChildren().get(0).getChildren().get(1).addChild(new GeneralTree<Integer>(55));
		arbol.getChildren().get(0).getChildren().get(1).addChild(new GeneralTree<Integer>(18));
		arbol.getChildren().get(2).getChildren().get(0).addChild(new GeneralTree<Integer>(4));
		//arbol.entreNiveles();
		ParcialArboles par=new ParcialArboles(arbol);
		System.out.println(par.camino(3));
	}

}
