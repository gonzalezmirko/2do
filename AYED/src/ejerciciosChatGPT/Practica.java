package ejerciciosChatGPT;
import java.util.*;
import tp1.ejercicio8.Queue;
import tp5.ejercicio1.*;
public class Practica {
	private Graph<String>grafo;
	/*
	 * Consigna 1: Caminos seguros (Listas de listas de String)
	Dado un grafo dirigido donde los vértices representan lugares y las aristas caminos 
	con un peso que indica la peligrosidad (0 a 10), implementar un método que devuelva 
	todos los caminos posibles desde "Entrada" hasta "Salida" que pasen solo por caminos
	 con peligrosidad menor o igual a 3.
	 */
	public List<List<String>> caminosSeguros(){
		List<List<String>>caminos=new LinkedList<List<String>>();
		if(!this.grafo.isEmpty()) {
			Vertex<String>origen=this.grafo.search("Entrada");
			Vertex<String>destino=this.grafo.search("Salida");
			if(origen!=null && destino!=null) {
				dfs1(origen,destino,this.grafo,new boolean[this.grafo.getSize()],new LinkedList<String>(),caminos);
			}
		}
		return caminos;
	}
	private void dfs1(Vertex<String>origen,Vertex<String>destino,Graph<String>grafo,boolean[]marcas,List<String>caminoActual,List<List<String>>caminos) {
		marcas[origen.getPosition()]=true;
		caminoActual.add(origen.getData());
		
		if(origen==destino) {
			caminos.add(new LinkedList<String>(caminoActual));
		}
		else {
			List<Edge<String>>edges=this.grafo.getEdges(origen);
			for(Edge<String>edge:edges) {
				Vertex<String>vertex=edge.getTarget();
				int pos=vertex.getPosition();
				int peso=edge.getWeight();
				if(!marcas[pos] && peso<=3) {
					dfs1(vertex,destino,grafo,marcas,caminoActual,caminos);
				}
			}
		}
		marcas[origen.getPosition()]=false;
		caminoActual.remove(caminoActual.size()-1);
	}
	/*
	Consigna 2: Caminos y su costo total (Listas de listas de objetos)
	Implementar una clase auxiliar llamada CaminoSeguro que tenga:
	public class CaminoSeguro {
    private List<String> camino;
    private int costoTotal;]
	Luego, implementar un método que devuelva todos los caminos desde "Inicio" hasta "Fin", 
	siempre que el peso de las aristas sea menor a 6, y por cada uno se guarde el camino completo y su peso total.
	public List<CaminoSeguro> caminosConCosto()
	 */
	public List<CaminoSeguro> caminosConCosto(){
		List<CaminoSeguro>caminos=new LinkedList<CaminoSeguro>();
		if(!this.grafo.isEmpty()) {
			Vertex<String>origen=this.grafo.search("Inicio");
			Vertex<String>destino=this.grafo.search("Fin");
			if(origen!=null && destino!=null) {
				dfs2(origen,destino,this.grafo,new boolean[this.grafo.getSize()],new LinkedList<String>(),caminos,0);
			}
		}
		return caminos;
	}
	public void dfs2(Vertex<String>origen,Vertex<String>destino,Graph<String>grafo,boolean[]marcas,List<String>caminoActual,List<CaminoSeguro>caminos,int sumaActual) {
		caminoActual.add(origen.getData());
		marcas[origen.getPosition()]=true;
		
		if(origen==destino) {
			caminos.add(new CaminoSeguro(new LinkedList<String>(caminoActual),sumaActual));
		}
		else {
			List<Edge<String>>edges=this.grafo.getEdges(origen);
			for(Edge<String>edge:edges) {
				Vertex<String>vertex=edge.getTarget();
				int pos=vertex.getPosition();
				int peso=edge.getWeight();
				if(!marcas[pos] && peso<=6) {
					dfs2(vertex,destino,grafo,marcas,caminoActual,caminos,sumaActual+peso);
				}
			}
		}
		caminoActual.remove(caminoActual.size()-1);
		marcas[origen.getPosition()]=false;
	}
	/*
	 * Consigna 3: Suma total de frutas (camino normal + suma de aristas)
	En un bosque representado como un grafo, encontrar uno solo de los caminos válidos desde "A" hasta "B" 
	en donde el peso de las aristas representa la cantidad de frutas. El método debe devolver la cantidad 
	total de frutas recolectadas en un camino cualquiera (no necesariamente el más corto ni el más largo).
	public int sumarFrutasCamino()
	 */
	public int sumarFrutasCamino() {
		int suma=0;
		if(!this.grafo.isEmpty()) {
			Vertex<String>origen=this.grafo.search("A");
			Vertex<String>destino=this.grafo.search("B");
			if(origen!=null && destino!=null) {
				suma=dfs3(origen,destino,this.grafo,new boolean[this.grafo.getSize()],0);
			}
		}
		return suma;
	}
	private int dfs3(Vertex<String>origen,Vertex<String>destino,Graph<String>grafo,boolean[]marcas,int sumaActual) {
		boolean ok=false;
		int suma=0;
		if(origen==destino) {
			ok=true;
			return sumaActual;
		}
		List<Edge<String>>edges=this.grafo.getEdges(origen);
		Iterator<Edge<String>>it=edges.iterator();
		while(it.hasNext() && !ok) {
			Edge<String>edge=it.next();
			Vertex<String>vertex=edge.getTarget();
			int peso=edge.getWeight();
			int pos=vertex.getPosition();
			if(!marcas[pos]) {
				suma=dfs3(vertex,destino,grafo,marcas,sumaActual+peso);
			}
		}
		if(ok) {
			return suma;
		}
		if(!ok) {
			marcas[origen.getPosition()]=false;
		}
		return 0;
	}
	/*
	 * Consigna 4: Recorrido en amplitud (BFS)
	Implementar un método que realice un recorrido BFS (por niveles) desde un vértice dado ("Inicio") 
	y retorne la lista de nodos visitados en el orden que se visitan.
	public List<String> recorridoBFS(String inicio)
	 */
	public List<String> recorridoBFS(String inicio){
		List<String>caminos=new LinkedList<String>();
		if(!this.grafo.isEmpty()) {
			Vertex<String>origen=this.grafo.search(inicio);
			if(origen!=null) {
				bfs(origen,this.grafo,new boolean[this.grafo.getSize()],caminos);
			}
		}
		return caminos;
	}
	private void bfs(Vertex<String>origen,Graph<String>grafo,boolean[]marcas,List<String>camino) {
		marcas[origen.getPosition()]=true;
		Queue<Vertex<String>>queue=new Queue<Vertex<String>>();
		
		queue.enqueue(origen);
		
		while(!queue.isEmpty()) {
			Vertex<String>vertex=queue.dequeue();
			if(vertex != null) {
				camino.add(vertex.getData());
				List<Edge<String>>edges=this.grafo.getEdges(vertex);
				for(Edge<String>edge:edges) {
					Vertex<String>v=edge.getTarget();
					int pos=v.getPosition();
					if(!marcas[pos]) {
						queue.enqueue(v);
						marcas[pos]=true;
					}
				}
			}
		}
	}
	/*
	 * Consigna 5: Camino con menor peso total
	En un grafo dirigido con pesos positivos, implementar un método que devuelva el camino de menor peso total
 	desde un vértice origen hasta uno destino, usando cualquier algoritmo (puede ser DFS + comparación manual, 
 	no es necesario usar Dijkstra si no estás familiarizado).
 	public List<String> caminoMasCorto(String origen, String destino)
	 */
	public List<String> caminoMasCorto(String origen, String destino){
		List<String>caminos=new LinkedList<String>();
		if(!this.grafo.isEmpty()) {
			Vertex<String>inicio=this.grafo.search(origen);
			Vertex<String>fin=this.grafo.search(destino);
			if(inicio!=null && fin !=null) {
				dfs5(inicio,fin,this.grafo,new boolean[this.grafo.getSize()],caminos,new LinkedList<String>(),0,Integer.MAX_VALUE);
			}
		}
		return caminos;
	}
	private void dfs5(Vertex<String>origen,Vertex<String>destino,Graph<String>grafo,boolean[]marcas,List<String>caminos,List<String>caminoActual,int sumaActual,int min) {
		marcas[origen.getPosition()]=true;
		boolean ok=false;
		caminoActual.add(origen.getData());
		if(origen==destino) {
			if(sumaActual<min) {
				caminos.removeAll(caminos);
				caminos.addAll(caminoActual);
				min=sumaActual;
			}
		}
		List<Edge<String>>edges=this.grafo.getEdges(origen);
		Iterator<Edge<String>>it=edges.iterator();
		while(it.hasNext() && !ok) {
			Edge<String>edge=it.next();
			Vertex<String>vertex=edge.getTarget();
			int pos=vertex.getPosition();
			int peso=edge.getWeight();
			if(!marcas[pos]) {
				dfs5(vertex,destino,grafo,marcas,caminos,caminoActual,sumaActual,sumaActual+peso);
			}
		}
		caminoActual.remove(caminoActual.size()-1);
		marcas[origen.getPosition()]=false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
