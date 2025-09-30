package parcial22;
import java.util.*;
import tp5.ejercicio1.*;
public class Parcial {
	
	public List<String> resolver(Graph<String>ciudades,String origen,String destino,List<String>pasandoPor) {
		List<String>camino=new LinkedList<String>();
		if(!ciudades.isEmpty()) {
			Vertex<String>inicio=ciudades.search(origen);
			Vertex<String>fin=ciudades.search(destino);
			if(inicio != null && fin !=null) {
				dfs(inicio,fin,ciudades,new boolean[ciudades.getSize()],pasandoPor,camino,new LinkedList<String>());
			}
		}
		return camino;
	}
	private boolean contieneCiudades(List<String>camino,List<String>obligatorios) {
		boolean ok=true;
		Iterator<String>it=obligatorios.iterator();
		while(it.hasNext() && ok) {
			String ciudad=it.next();
			if(!camino.contains(ciudad)) {
				ok=false;
			}
		}
		return ok;
	}
	private void dfs(Vertex<String>origen,Vertex<String>destino,Graph<String>grafo,boolean[]marca,List<String>listaCiudades,List<String>camino,List<String>caminoActual) {
		marca[origen.getPosition()]=true;
		caminoActual.add(origen.getData());
		if(origen==destino) {
			if(contieneCiudades(caminoActual,listaCiudades)) {
				camino.clear();
				camino.addAll(caminoActual);
			}
		}
		List<Edge<String>>edges=grafo.getEdges(origen);
		for(Edge<String>edge:edges) {
			Vertex<String>vertex=edge.getTarget();
			int pos=vertex.getPosition();
			if(!marca[pos]) {
				dfs(vertex,destino,grafo,marca,listaCiudades,camino,caminoActual);
			}
		}
		caminoActual.remove(caminoActual.size()-1);
		marca[origen.getPosition()]=false;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
