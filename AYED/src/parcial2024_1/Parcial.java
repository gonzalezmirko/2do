package parcial2024_1;
import java.util.*;
import tp5.ejercicio1.*;
import tp1.ejercicio8.Queue;
public class Parcial {
	
	public Datos nivelPopuralidad(Graph<String>red,String usuario,int distancia,int umbral) {
		Datos datos=new Datos();
		if(!red.isEmpty()) {
			Vertex<String>origen=red.search(usuario);
			if(origen!=null) {
				bfs(origen,red,new boolean[red.getSize()],distancia,umbral,datos);
			}
		}
		return datos;
	}
	private void bfs(Vertex<String>origen,Graph<String>grafo,boolean[]marca,int distancia,int umbral,Datos datos) {
		marca[origen.getPosition()]=true;
		Queue<Vertex<String>>queue=new Queue<Vertex<String>>();
		queue.enqueue(origen);
		queue.enqueue(null);
		
		int nivel=0;
		int cantUsuarios=0;
		while(!queue.isEmpty() && nivel<=distancia) {
			Vertex<String>vertex=queue.dequeue();
			if(vertex!=null) {
				List<Edge<String>>edges=grafo.getEdges(vertex);
				for(Edge<String>edge:edges) {
					Vertex<String>vecino=edge.getTarget();
					int pos=vecino.getPosition();
					if(!marca[pos]) {
						marca[pos]=true;
						queue.enqueue(vecino);
						//si llego al nivel deseado -->osea se encolan los vecinos(usuarios) para el siguiente nivel
						if(nivel==distancia-1) {
							cantUsuarios++;
						}
					}
				}
			}
			else {
				nivel++;
				if(nivel==distancia) {
					datos.setCantidadUsuarios(cantUsuarios);
					datos.setEsPopular(cantUsuarios>=umbral);
				}
				if(!queue.isEmpty()) {
					queue.enqueue(null);
				}
			}
		}
	}
	public static void main(String[] args) {

	}

}
