package parcial202501;
import java.util.*;
import tp3.ejercicio1.*;
public class Parcial {
	
	private static void caminoSignoAlternanteRec(GeneralTree<Integer>arbol,List<Integer>mejorCamino,List<Integer>caminoActual,int costo,boolean esPositivo,Maximo max) {
		caminoActual.add(arbol.getData());
		costo += arbol.getData();
		if(arbol.isLeaf()) {
			if(costo>max.getMax()) {
				max.setMax(costo);
				mejorCamino.clear();
				mejorCamino.addAll(caminoActual);
			}
		}
		else {
			for(GeneralTree<Integer>child:arbol.getChildren()) {
				if(esPositivo && child.getData()<0 || !esPositivo && child.getData()>=0) {
					caminoSignoAlternanteRec(child,mejorCamino,caminoActual,costo,true,max);
				}
			}
		}
		caminoActual.remove(caminoActual.size()-1);
	}
	public static List<Integer> caminoSignoAlternante(GeneralTree<Integer> arbol) {
        List<Integer> camino = new LinkedList<>();
        if(arbol != null && !arbol.isEmpty())
        		caminoSignoAlternanteRec(arbol,camino,new LinkedList<>(),0,arbol.getData()>=0,new Maximo());
        return camino;
    }
	public static void main(String[] args) {
        GeneralTree<Integer> raiz = new GeneralTree<>(-2);
        raiz.addChild(new GeneralTree<>(2));
        raiz.addChild(new GeneralTree<>(-4));
        raiz.getChildren().getFirst().addChild(new GeneralTree<>(7));
        raiz.getChildren().getFirst().addChild(new GeneralTree<>(-5));
        raiz.getChildren().getFirst().addChild(new GeneralTree<>(8));
        raiz.getChildren().getFirst().getChildren().get(2).addChild(new GeneralTree<>(-1));
        raiz.getChildren().get(1).addChild(new GeneralTree<>(6));

        System.out.println(Parcial.caminoSignoAlternante(raiz));
    }

}
