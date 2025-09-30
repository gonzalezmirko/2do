package ejerciciosChatGPT2;
import java.util.*;
import tp5.ejercicio1.*;
import tp1.ejercicio8.Queue;
public class Practica {
	private Graph<String>grafo;
	
	/*1. Niveles seguros por peligrosidad
	Tema: Recorrido por niveles, con umbral de peligrosidad.
	Un grafo dirigido representa un sistema de túneles. Cada arista tiene un peso que indica la peligrosidad (1 a 10). 
	Se quiere recorrer los túneles desde un nodo "Entrada" y encontrar todos los lugares que estén a 
	un máximo de 3 niveles de distancia, y donde cada camino recorrido tenga solo peligrosidad ≤ 5.
	Implementar un método:public List<String> lugaresSegurosDesdeEntrada();
	Usá BFS por niveles con null si querés. 
	*/
	public List<String> lugaresSegurosDesdeEntrada(){
		List<String>caminos=new LinkedList<String>();
		if(!grafo.isEmpty()) {
			Vertex<String>origen=grafo.search("Entrada");
			if(origen != null) {
				bfs1(origen,grafo,new boolean[grafo.getSize()],caminos);
			}
		}
		return caminos;
	}
	private void bfs1(Vertex<String>origen,Graph<String>grafo,boolean[]marcas,List<String>camino) {
		marcas[origen.getPosition()]=true;
		Queue<Vertex<String>>queue=new Queue<Vertex<String>>();
		queue.enqueue(origen);
		queue.enqueue(null);
		camino.add(origen.getData());
		int nivel=0;
		while(!queue.isEmpty() && nivel<=3) {
			Vertex<String>vertex=queue.dequeue();
			if(vertex!=null) {
				List<Edge<String>>edges=grafo.getEdges(vertex);
				for(Edge<String>edge:edges) {
					Vertex<String>vecino=edge.getTarget();
					int pos=vecino.getPosition();
					int peso=edge.getWeight();
					if(!marcas[pos] && peso<=5) {
						camino.add(vecino.getData());
						marcas[pos]=true;
						queue.enqueue(vecino);
					}
				}
			}
			else {
				nivel++;
				if(!queue.isEmpty()) {
					queue.enqueue(null);
				}
			}
		}
	}
	/*
	 * 2. Camino más corto en niveles con restricciones
	Tema: BFS clásico buscando la distancia más corta (en niveles), con restricción de peso.
	En un grafo no dirigido, cada arista indica la dificultad de atravesar un camino. 
	Desde el nodo "Inicio", se desea encontrar la menor cantidad de pasos (niveles) para llegar al nodo
	 "Tesoro", siempre que todas las aristas del camino tengan dificultad ≤ 4.
	Devolver el camino como lista:public List<String> caminoMasCortoSeguro();
	 */
	public List<String> caminoMasCortoSeguro(){
		List<String>camino=new LinkedList<String>();
		if(!grafo.isEmpty()) {
			Vertex<String>origen=grafo.search("Inicio");
			Vertex<String>destino=grafo.search("Tesoro");
			if(origen != null && destino != null) {
				bfs2(origen,destino,grafo,new boolean[grafo.getSize()],camino);
			}
		}
		return camino;
	}
	private void bfs2(Vertex<String>origen,Vertex<String>destino,Graph<String>grafo,boolean[]marca,List<String>camino) {
		marca[origen.getPosition()]=true;
		camino.add(origen.getData());
		
		Queue<Vertex<String>>queue=new Queue<Vertex<String>>();
		queue.enqueue(origen);
		queue.enqueue(null);
		int nivel=0;
		boolean encontre=false;
		while(!queue.isEmpty() && !encontre) {
			Vertex<String>vertex=queue.dequeue();
			if(vertex != null) {
				List<Edge<String>>edges=grafo.getEdges(vertex);
				for(Edge<String>edge:edges) {
					Vertex<String>vecino=edge.getTarget();
					int pos=vecino.getPosition();
					int peso=edge.getWeight();
					if(!marca[pos] && peso<=4 && !encontre) {
						marca[pos]=true;
						queue.enqueue(vecino);
						if(vecino==destino) {
							encontre=true;
						}
					}
				}
			}
			else {
				nivel++;
				if(!queue.isEmpty()) {
					queue.enqueue(null);
				}
			}
		}
	}
	/*
	3. Cantidad de personas alcanzables en N niveles
	Tema: Contar nodos por niveles, BFS + contador.
	Un grafo representa usuarios conectados en una red. Cada conexión (arista) tiene un peso que representa 
	el grado de confianza (1 a 10). Desde un usuario "X", contar cuántos otros usuarios se pueden alcanzar
	 en exactamente N niveles, solo usando conexiones con confianza ≥ 7.
	 public int contarUsuariosConfiables(String usuario, int niveles);
	 */
	public int contarUsuariosConfiables(String usuario, int niveles) {
		int cantidad=0;
		if(!grafo.isEmpty()) {
			Vertex<String>origen=grafo.search("X");
			if(origen != null) {
				cantidad=bfs3(origen,grafo,new boolean[grafo.getSize()],niveles);
			}
		}
		return cantidad;
	}
	private int bfs3(Vertex<String>origen,Graph<String>grafo,boolean[]marca,int niveles) {
		int cantidad=0;
		int nivel=0;
		boolean encontre=false;
		marca[origen.getPosition()]=true;
		
		Queue<Vertex<String>>queue=new Queue<Vertex<String>>();
		queue.enqueue(origen);
		queue.enqueue(null);
		
		while(!queue.isEmpty()&& nivel<=niveles && !encontre) {
			Vertex<String>vertex=queue.dequeue();
			if(vertex!=null) {
				List<Edge<String>>edges=grafo.getEdges(vertex);
				Iterator<Edge<String>>it=edges.iterator();
				while(it.hasNext() && nivel<=niveles &&!encontre) {
					Edge<String>edge=it.next();
					Vertex<String>vecino=edge.getTarget();
					int peso=edge.getWeight();
					int pos=vecino.getPosition();
					if(!marca[pos] && peso>=7) {
						marca[pos]=true;
						queue.enqueue(vecino);
						if(nivel==(niveles-1)) {
							cantidad++;
						}
					}
				}
			}
			else {
				nivel++;
				if(!queue.isEmpty()) {
					queue.enqueue(null);
				}
			}
		}
		return cantidad;
	}
	/*
	 *  4. Todos los caminos posibles hasta cierto nivel
	Tema: Listas de caminos válidos hasta un nivel máximo.
	Desde el vértice "Raíz", encontrar todos los caminos hasta cualquier otro nodo que estén a una
	profundidad máxima de 3 niveles, y donde cada arista tenga peso ≤ 8.
	public List<List<String>> caminosHastaNivelSeguro();
	Podés guardar caminos como listas actuales, con Queue de Pair<vertex,caminoActual> 
	si querés BFS real con caminos completos.
	 */
	public List<List<String>> caminosHastaNivelSeguro(){
		List<List<String>>caminos=new LinkedList<List<String>>();
		if(!grafo.isEmpty()) {
			Vertex<String>origen=grafo.search("Raiz");
			if(origen != null) {
				bfs4(origen,grafo,new boolean[grafo.getSize()],caminos);
			}
		}
		return caminos;
	}
	private void bfs4(Vertex<String>origen,Graph<String>grafo,boolean[]marca,List<List<String>>caminos) {
		marca[origen.getPosition()]=true;
		List<String>caminoActual=new LinkedList<String>();
		//caminoActual.add(origen.getData());
		
		Queue<Vertex<String>>queue=new Queue<Vertex<String>>();
		queue.enqueue(origen);
		queue.enqueue(null);
		int nivel=0;
		while(!queue.isEmpty() && nivel<=3) {
			Vertex<String>vertex=queue.dequeue();
			if(vertex!=null) {
				caminoActual.add(origen.getData());
				List<Edge<String>>edges=grafo.getEdges(vertex);
				Iterator<Edge<String>>it=edges.iterator();
				while(it.hasNext()) {
					Edge<String>edge=it.next();
					Vertex<String>vecino=edge.getTarget();
					int peso=edge.getWeight();
					int pos=vecino.getPosition();
					if(!marca[pos] && peso<=8 && nivel<=3) {
						marca[pos]=true;
						queue.enqueue(vecino);
					}
				}
			}
			else {
				nivel++;
				if(!queue.isEmpty()) {
					queue.enqueue(null);
				}
			}
		}
		if(nivel==3) {
			caminos.add(caminoActual);
		}
	}
/*
 *  5. Nodo más lejano alcanzable con peso acumulado limitado
	Tema: BFS con suma acumulada de pesos + profundidad.
	Desde el nodo "Origen", encontrar el nodo que esté más lejos (en niveles), 
	tal que la suma acumulada de los pesos de las aristas hasta llegar a él no supere un total de 20.
	public String nodoMasLejanoConLimite();
	Podés usar una clase auxiliar para ir encolando Vertex, nivel y pesoAcumulado.
	 */
	public String nodoMasLejanoConLimite() {
		String aux="";
		if(!grafo.isEmpty()) {
			Vertex<String>origen=grafo.search("Origen");
			if(origen != null) {
				bfs5(origen,grafo,new boolean[grafo.getSize()],aux);
			}
		}
		return aux;
	}
	private void bfs5(Vertex<String>origen,Graph<String>grafo,boolean[]marca,String aux) {
		marca[origen.getPosition()]=true;
		Queue<Vertex<String>>queue=new Queue<Vertex<String>>();
		queue.enqueue(origen);
		queue.enqueue(null);
		int total=0;
		String aux2="";
		boolean encontre=false;
		while(!queue.isEmpty() && total<=20 && !encontre) {
			Vertex<String>vertex=queue.dequeue();
			if(vertex!=null) {
				aux2=origen.getData();
				List<Edge<String>>edges=grafo.getEdges(vertex);
				Iterator<Edge<String>>it=edges.iterator();
				while(it.hasNext() && total<=20 && !encontre) {
					Edge<String>edge=it.next();
					Vertex<String>vecino=edge.getTarget();
					int pos=vecino.getPosition();
					int peso=edge.getWeight();
					if(!marca[pos] && total<=20) {
						total+=peso;
						marca[pos]=true;
						queue.enqueue(vecino);
					}
					else {
						encontre=true;
					}
				}
			}
			else {
				if(!queue.isEmpty()) {
					queue.enqueue(null);
				}
			}
		}
		aux.replaceAll(aux, aux2);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
