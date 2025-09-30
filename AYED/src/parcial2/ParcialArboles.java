package parcial2;

import tp3.ejercicio1.GeneralTree;
import java.util.*;
import tp1.ejercicio8.Queue;
public class ParcialArboles {
	private GeneralTree<Integer>arbol;
	
	public ParcialArboles(GeneralTree<Integer>arbol) {
		this.arbol=arbol;
	}
	private void buscarNivel(GeneralTree<Integer>arbol,int num,List<Integer>lista) {
		Queue<GeneralTree<Integer>>queue=new Queue<GeneralTree<Integer>>();
		queue.enqueue(arbol);
		queue.enqueue(null);
		boolean encontre=false;
		boolean cumpleNivel=false;
		while(!queue.isEmpty() && !encontre) {
			GeneralTree<Integer>ab=queue.dequeue();
			if(ab!=null) {
				lista.add(ab.getData());
				if(ab.getChildren().size()>=num) {
					cumpleNivel=true;
				}
				for(GeneralTree<Integer>child:ab.getChildren()) {
					queue.enqueue(child);
				}	
			}
			else {
				if(cumpleNivel) {
					encontre=true;
				}
				else {
					lista.clear();
					cumpleNivel=false;
				}
				if(!queue.isEmpty()) {
					queue.enqueue(null);
					
				}
			}
		}
	}
	public List<Integer> nivel(int num){
		List<Integer>lista=new ArrayList<Integer>();
		if(this.arbol!=null && !this.arbol.isEmpty()) {
			buscarNivel(this.arbol,num,lista);
		}
		return lista;
	}
	public static void main(String[] args) {
		GeneralTree<Integer>arbol=new GeneralTree<Integer>(10);
		arbol.addChild(new GeneralTree<Integer>(8));
		arbol.addChild(new GeneralTree<Integer>(-5));
		arbol.getChildren().get(0).addChild(new GeneralTree<Integer>(5));
		arbol.getChildren().get(0).addChild(new GeneralTree<Integer>(22));
		arbol.getChildren().get(0).addChild(new GeneralTree<Integer>(23));
		arbol.getChildren().get(1).addChild(new GeneralTree<Integer>(19));
		arbol.getChildren().get(1).addChild(new GeneralTree<Integer>(20));
		arbol.getChildren().get(1).addChild(new GeneralTree<Integer>(1));
		arbol.getChildren().get(0).getChildren().get(0).addChild(new GeneralTree<Integer>(-6));
		arbol.getChildren().get(0).getChildren().get(0).addChild(new GeneralTree<Integer>(2));
		arbol.getChildren().get(0).getChildren().get(0).addChild(new GeneralTree<Integer>(6));
		arbol.getChildren().get(0).getChildren().get(1).addChild(new GeneralTree<Integer>(28));
		arbol.getChildren().get(0).getChildren().get(1).addChild(new GeneralTree<Integer>(55));
		arbol.getChildren().get(0).getChildren().get(1).addChild(new GeneralTree<Integer>(18));
		arbol.getChildren().get(1).getChildren().get(0).addChild(new GeneralTree<Integer>(4));
		arbol.getChildren().get(1).getChildren().get(0).addChild(new GeneralTree<Integer>(2));
		arbol.getChildren().get(1).getChildren().get(0).addChild(new GeneralTree<Integer>(8));
		//arbol.entreNiveles();
		ParcialArboles par=new ParcialArboles(arbol);
		System.out.println(par.nivel(3));
	}

}
