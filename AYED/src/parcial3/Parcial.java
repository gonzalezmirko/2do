package parcial3;
import java.util.List;

import parcial2.ParcialArboles;
import tp3.ejercicio1.*;
public class Parcial {
	private GeneralTree<Integer>arbol;
	public Parcial(GeneralTree<Integer>arbol) {
		this.arbol=arbol;
	}
	private boolean resolverRec(GeneralTree<Integer>arbol,int k,int suma) {
		suma += arbol.getData();//preorden
		if(arbol.isLeaf()){
			return suma==k;
		}
		for(GeneralTree<Integer>child:arbol.getChildren()) {
			if(resolverRec(child,k,suma)) {
				return true;//si alguno cumplio
			}
		}
		return false;//si ninguno cumplio
	}
	public boolean resolver(int k) {
		if(this.arbol!=null && !this.arbol.isEmpty()) {
			return resolverRec(this.arbol,k,0);
		}
		return false;
	}
	public static void main(String[] args) {
		GeneralTree<Integer>arbol=new GeneralTree<Integer>(2);
		arbol.addChild(new GeneralTree<Integer>(1));
		arbol.addChild(new GeneralTree<Integer>(2));
		arbol.getChildren().get(0).addChild(new GeneralTree<Integer>(10));
		arbol.getChildren().get(0).addChild(new GeneralTree<Integer>(4));
		arbol.getChildren().get(1).addChild(new GeneralTree<Integer>(19));
		arbol.entreNiveles();
		Parcial par=new Parcial(arbol);
		System.out.println(par.resolver(13));
	}

}
