package parcial6;
import tp2.ejercicio1.*;
import java.util.*;
public class Parcial {
	public List<Integer>resolver(BinaryTree<Integer>ab,int min){
		List<Integer>lista=new LinkedList<Integer>();
		if(ab!=null && !ab.isEmpty()) {
			resolverRec(ab,lista,new LinkedList<Integer>(),0,min);
		}
		return lista;
	}
	private void resolverRec(BinaryTree<Integer>ab,List<Integer>listaMejor,List<Integer>listaActual,int contPares,int min) {
		listaActual.add(ab.getData());
		if(ab.getData()%2==0) {
			contPares++;
		}
		if(ab.hasLeftChild()) {
			resolverRec(ab.getLeftChild(),listaMejor,listaActual,contPares,min);
		}
		if(ab.hasRitghChild()) {
			resolverRec(ab.getRightChild(),listaMejor,listaActual,contPares,min);
		}
		if(ab.isLeaf()) {
			if(contPares<=min) {
				listaMejor.clear();
				listaMejor.addAll(listaActual);
			}
		}
		listaActual.remove(listaActual.size()-1);
	}
	public static void main(String[] args) {
	}

}
