package parcialGrafos2025;
import java.util.*;
import tp5.ejercicio1.*;
public class Parcial {
	
	public List<String>rutaOptimaDistribucion(Graph<String>reino,String castillo,String aldea,int maxPosiones){
		List<String>camino=new LinkedList<String>();
		if(!reino.isEmpty()) {
			Vertex<String>origen=reino.search(castillo);
			Vertex<String>destino=reino.search(aldea);
			if(origen != null && destino !=null) {
				dfs(origen,destino,reino,new boolean[reino.getSize()],maxPosiones,new Maximo(),camino,new LinkedList<String>());
			}
		}
		return camino;
	}
	private void dfs(Vertex<String>origen,Vertex<String>destino,Graph<String>reino,boolean[]marca,int maxPosiones,Maximo max,List<String>camino,List<String>caminoActual) {
		marca[origen.getPosition()]=true;
		caminoActual.add(origen.getData());
		if(origen==destino) {
			if(caminoActual.size()>max.getMax()) {
				max.setMax(caminoActual.size());
				camino.removeAll(camino);
				camino.addAll(caminoActual);
			}
		}
		else {
			List<Edge<String>>edges=reino.getEdges(origen);
			Iterator<Edge<String>>it=edges.iterator();
			while(it.hasNext()) {
				Edge<String>edge=it.next();
				Vertex<String>vertex=edge.getTarget();
				int peso=edge.getWeight();
				if(!marca[vertex.getPosition()] && (peso<maxPosiones)) {
					dfs(vertex,destino,reino,marca,maxPosiones-peso,max,camino,caminoActual);
				}
				
			}
		}
		marca[origen.getPosition()]=false;
		caminoActual.remove(caminoActual.size()-1);
	}

	public static void main(String[] args) {
		

	}

}
