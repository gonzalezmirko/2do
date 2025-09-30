package tp5.ejercicio2;

/*
 * Ejercicio 2
a.	Implemente en JAVA una clase llamada Recorridos ubicada dentro del paquete ejercicio2 
cumpliendo la siguiente especificación:
 
dfs(Graph<T> grafo): List<T>
// Retorna una lista con los datos de los vértices, con el recorrido en profundidad del grafo recibido como parámetro.
bfs(Graph<T> grafo): List<T>
// Retorna una lista con los datos de vértices, con el recorrido en amplitud del grafo recibido como parámetro.
 
b.	Estimar el orden de ejecución de los métodos anteriores.

 */
import tp1.ejercicio8.Queue;
import java.util.*;
import tp5.ejercicio1.*;
import tp5.ejercicio1.adjList.AdjListGraph;
public class Recorridos <T>{
	
	public List<T> dfs(Graph<T>grafo) {
		List <T> lista=new LinkedList<T>();
		//java pone en false todos los booleans
		boolean[]visitados=new boolean[grafo.getSize()];
		//tengo que recorrer cada vertice
		for(Vertex<T> vertice:grafo.getVertices()) {
			if(!visitados[vertice.getPosition()]) {
				dfsRec(grafo,vertice,visitados,lista);
			}
		}
		return lista;
	}
	private void dfsRec(Graph<T>grafo,Vertex<T>vertice,boolean[]visitados,List<T>lista) {
		lista.add(vertice.getData());
		visitados[vertice.getPosition()]=true;
		
		//me quedo con las adyacencias del vertice
		List<Edge<T>>adyacencias=grafo.getEdges(vertice);
		for(Edge<T> edge:adyacencias) {
			//Vertice destino de la arista
			Vertex<T>destino=edge.getTarget();
			if(!visitados[destino.getPosition()]) {
				dfsRec(grafo,destino,visitados,lista);
			}
		}
		
	}
	public List<T> bfs(Graph<T>grafo) {
		List <T> lista=new LinkedList<T>();
		boolean[]marca=new boolean[grafo.getSize()];
		for(int i=0;i<grafo.getSize();i++) {
			if(!marca[i]) {
				bfs2(grafo,i,marca,lista);
			}
		}
		return lista;
	}
	private void bfs2(Graph<T>grafo,int i,boolean[]marca,List<T>lista){
		Queue<Vertex<T>>cola=new Queue<Vertex<T>>();
		cola.enqueue(grafo.getVertex(i));
		marca[i]=true;
		while(!cola.isEmpty()) {
			Vertex<T>w=cola.dequeue();
			//Para todos los vecinos de w
			lista.add(w.getData());
			List<Edge<T>>adyacencias=grafo.getEdges(w);
			for(Edge<T>edge:adyacencias) {
				int j=edge.getTarget().getPosition();
				if(!marca[j]) {
					marca[j]=true;
					cola.enqueue(edge.getTarget());
				}
			}
		}
	}
	public static void main(String[] args) {
		AdjListGraph<String>grafo=new AdjListGraph<String>();
		Recorridos<String>recorridos=new Recorridos<String>();
		
		Vertex<String>vertice1=grafo.createVertex("Buenos Aires");
		Vertex<String>vertice2=grafo.createVertex("Santiago");
		Vertex<String>vertice3=grafo.createVertex("Asuncion");
		Vertex<String>vertice4=grafo.createVertex("Caracas");
		Vertex<String>vertice5=grafo.createVertex("Madrid");
		Vertex<String>vertice6=grafo.createVertex("Roma");
		Vertex<String>vertice7=grafo.createVertex("Tokio");
		Vertex<String>vertice8=grafo.createVertex("Paris");
		
		grafo.connect(vertice1, vertice2);
		grafo.connect(vertice1, vertice3);
		grafo.connect(vertice2, vertice6);
		grafo.connect(vertice3, vertice5);
		grafo.connect(vertice3, vertice4);
		grafo.connect(vertice4, vertice7);
		grafo.connect(vertice4, vertice8);
		grafo.connect(vertice5, vertice7);
		grafo.connect(vertice6, vertice7);
		grafo.connect(vertice7, vertice1);
		grafo.connect(vertice8, vertice7);
		
		List<String>listaDfs=recorridos.dfs(grafo);
		List<String>listaBfs=recorridos.bfs(grafo);
		
		System.out.println("-----------> DFS <---------");
		for(String dato :listaDfs) {
			System.out.println("-"+dato+"-");
		}
		
		System.out.println("------> BFS <-------");
		for(String dato:listaBfs) {
			System.out.println("-"+dato+"-");
		}
	}

}
