package parcial4;

import java.util.List;

import tp3.ejercicio1.GeneralTree;

public class Parcial {
	private GeneralTree<Integer>arbol;
	
	public Parcial(GeneralTree<Integer>arbol) {
		this.arbol=arbol;
	}
	private void resolverRec(GeneralTree<Integer>arbol,List<GeneralTree<Integer>>result) {
		if(arbol.hasChildren()) {
			resolverRec(arbol.getChildren().get(0),result);
		}
		if(!arbol.isLeaf()&& arbol.getChildren().size()%2==0) {
			result.add(arbol);
		}
		for(int i=1;i<arbol.getChildren().size();i++) {
			resolverRec(arbol.getChildren().get(i),result);
		}
	}
	public static void main(String[] args) {

	}

}
