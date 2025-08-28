package parcial8;
import java.util.*;
import tp5.ejercicio1.*;
import tp1.ejercicio8.Queue;
public class Parcial {
	private void bfs(Graph<String>grafo,Vertex<String>origen,int distancia,int umbral,boolean[]marca,Datos datos) {
		Queue<Vertex<String>>queue=new Queue<Vertex<String>>();
		queue.enqueue(origen);
		queue.enqueue(null);
		marca[origen.getPosition()]=true;
		int nivel=0;
		boolean ok=false;
		int cantUsuarios=0;
		while(!queue.isEmpty() && nivel<=distancia && !ok) {
			Vertex<String>aux=queue.dequeue();
			if(aux!=null) {
				List<Edge<String>>edges=grafo.getEdges(aux);
				for(Edge<String>edge:edges) {
					int pos=edge.getTarget().getPosition();
					Vertex<String>vertex=edge.getTarget();
					if(!marca[pos] && nivel<=distancia) {
						queue.enqueue(vertex);
						marca[pos]=true;
						if(nivel+1==distancia) {
							cantUsuarios++;
						}
					}
				}
			}
			else {
				nivel++;
				if(nivel==distancia || queue.isEmpty()) {
					ok=true;
				}
				else {
					queue.enqueue(null);
				}
				
			}
		}
		datos.setCantUsuarios(cantUsuarios);
		datos.setEsPopular(cantUsuarios>=umbral);;
	}
	public Datos nivelPopularidad(Graph<String>red,String usuario,int distancia, int umbral) {
		Datos datos=null;
		if(!red.isEmpty()) {
			Vertex<String>origen=red.search(usuario);
			if(origen!=null) {
				datos=new Datos();
				bfs(red,origen,distancia,umbral,new boolean[red.getSize()],datos);
			}
		}
		return datos;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
