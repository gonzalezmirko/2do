package tp5.ejercicio3;
/*
 * 1.	devolverCamino (String ciudad1, String ciudad2): List<String>
Retorna la lista de ciudades que se deben atravesar para ir de ciudad1 a ciudad2 en caso de que se pueda llegar,
 si no retorna la lista vacía. (Sin tener en cuenta el combustible).
2.	devolverCaminoExceptuando (String ciudad1, String ciudad2, List<String> ciudades): List<String> 
Retorna la lista de ciudades que forman un camino desde ciudad1 a ciudad2, sin pasar por las ciudades que están contenidas en la lista ciudades pasada por parámetro, si no existe camino retorna la lista vacía. (Sin tener en cuenta el combustible).
3.	caminoMasCorto(String ciudad1, String ciudad2): List<String>
Retorna la lista de ciudades que forman el camino más corto para llegar de ciudad1 a ciudad2, si no existe 
camino retorna la lista vacía. (Las rutas poseen la distancia).
4.	caminoSinCargarCombustible(String ciudad1, String ciudad2, int tanqueAuto): List<String>
Retorna la lista de ciudades que forman un camino para llegar de ciudad1 a ciudad2. El auto no debe quedarse 
sin combustible y no puede cargar. Si no existe camino retorna la lista vacía.
5.	caminoConMenorCargaDeCombustible (String ciudad1, String ciudad2, int tanqueAuto): List<String>
 Retorna la lista de ciudades que forman un camino para llegar de ciudad1 a ciudad2 teniendo en cuenta que el auto 
 debe cargar la menor cantidad de veces. El auto no se debe quedar sin combustible en medio de una ruta, además puede 
 completar su tanque al llegar a cualquier ciudad. Si no existe camino retorna la lista vacía.

 */
import java.util.*;
import tp5.ejercicio1.*;
import tp5.ejercicio1.adjList.AdjListGraph;
import tp5.ejercicio2.Recorridos;
public class Mapa {
	private Graph<String>mapaCiudades;
	
	public Mapa(Graph<String>grafo) {
		this.mapaCiudades=grafo;
	}
	public List<String>devolverCamino(String ciudad1,String ciudad2) {
		List<String>lista=new LinkedList<String>();
		Vertex<String>origen=mapaCiudades.search(ciudad1);
		Vertex<String>destino=mapaCiudades.search(ciudad2);
		if(origen!=null && destino != null) {
			dfs(origen,destino,mapaCiudades,new boolean[mapaCiudades.getSize()],lista);
		}
		return lista;
	}
	private boolean dfs(Vertex<String>origen,Vertex<String>destino,Graph<String>mapa,boolean[]marca,List<String>lista) {
		lista.add(origen.getData());
		marca[origen.getPosition()]=true;
		
		if(origen==destino) {
			return true;
		}
		boolean ok=false;
		List<Edge<String>>edge=mapa.getEdges(origen);
		Iterator<Edge<String>>it=edge.iterator();
		while(it.hasNext() && !ok) {
			Vertex<String>sig=it.next().getTarget();
			if(!marca[sig.getPosition()]) {
				ok=dfs(sig,destino,mapa,marca,lista);
			}
		}
		if(!ok) {
			lista.remove(lista.size()-1);
		}
		return ok;
	}
	public List<String> devolverCaminoExceptuando(String ciudad1, String ciudad2, List<String> ciudades){
		List<String>lista=new LinkedList<String>();
		Vertex<String>origen=mapaCiudades.search(ciudad1);
		Vertex<String>destino=mapaCiudades.search(ciudad2);
		if(origen!=null && destino!=null) {
			dfsExceptuando(origen,destino,new boolean[mapaCiudades.getSize()],mapaCiudades,ciudades,lista);
		}
		return lista;
	}
	private boolean dfsExceptuando(Vertex<String>origen,Vertex<String>destino,boolean[]marca,Graph<String>mapa,List<String> ciudades,List<String>lista) {
		boolean ok=false;
		lista.add(origen.getData());
		marca[origen.getPosition()]=true;
		if(origen==destino) {
			return true;
		}
		List<Edge<String>>edge=mapa.getEdges(origen);
		Iterator<Edge<String>>it=edge.iterator();
		while(it.hasNext() && !ok) {
			Vertex<String>sig=it.next().getTarget();
			if (!marca[sig.getPosition()] && !ciudades.contains(sig.getData())) {
				ok=dfsExceptuando(sig,destino,marca,mapa,ciudades,lista);
			}
		}
		if(!ok) {
			lista.remove(lista.size()-1);
		}
		return ok;
	}
	public List<String>caminoMasCorto(String ciudad1, String ciudad2){
		List<String>lista=new LinkedList<String>();
		Vertex<String>origen=mapaCiudades.search(ciudad1);
		Vertex<String>destino=mapaCiudades.search(ciudad2);
		if(origen!=null && destino !=null) {
			dfsCorto(origen,destino,mapaCiudades,new boolean[mapaCiudades.getSize()],lista,new LinkedList<String>(),new Minimo(Integer.MAX_VALUE),0);
		}
		return lista;
	}
	private void dfsCorto(Vertex<String>origen,Vertex<String>destino,Graph<String>mapa,boolean[]marca,List<String>camino,List<String>caminoActual,Minimo min,int pesoActual) {
		caminoActual.add(origen.getData());
		marca[origen.getPosition()]=true;
		
		if(origen==destino && pesoActual<min.getMinimo()) {
			min.setMinimo(pesoActual);
			camino.removeAll(camino);
			camino.addAll(caminoActual);
			
		}else {
			List<Edge<String>>edge=mapa.getEdges(origen);
			Iterator<Edge<String>>it=edge.iterator();
			while(it.hasNext() && pesoActual<min.getMinimo()) {
				Edge<String>edgeActual=it.next();
				Vertex<String>vertex=edgeActual.getTarget();
				int pesoArista=edgeActual.getWeight();
				if(!marca[vertex.getPosition()] && pesoActual<min.getMinimo()) {
					dfsCorto(vertex,destino,mapa,marca,camino,caminoActual,min,pesoActual+pesoArista);
				}
			}
		}
		
		marca[origen.getPosition()]=false;
		caminoActual.remove(caminoActual.size()-1);
	}
	
	public List<String> caminoSinCargarCombustible(String ciudad1, String ciudad2, int tanqueAuto){
		List<String>camino=new LinkedList<String>();
		Vertex<String>origen=mapaCiudades.search(ciudad1);
		Vertex<String>destino=mapaCiudades.search(ciudad2);
		if(origen!=null && destino!=null && tanqueAuto>0) {
			caminoSinCargar(origen,destino,mapaCiudades,new boolean[mapaCiudades.getSize()],camino,tanqueAuto);
		}
		return camino;
	}
	private boolean caminoSinCargar(Vertex<String>origen,Vertex<String>destino,Graph<String>mapa,boolean[]marca,List<String>camino,int tanque) {
		marca[origen.getPosition()]=true;
		camino.add(origen.getData());
		
		if(origen==destino) {
			return true;
		}
		boolean ok=false;
		List<Edge<String>>edges=mapa.getEdges(origen);
		Iterator<Edge<String>>it=edges.iterator();
		
		while(it.hasNext() && !ok) {
			Edge<String>edge=it.next();
			Vertex<String>vertex=edge.getTarget();
			int peso=edge.getWeight();
			//costoActual=peso;
			if(!marca[vertex.getPosition()]&& tanque-peso>=0) {
				ok=caminoSinCargar(vertex,destino,mapa,marca,camino,tanque-peso);
			}
		}
		if(!ok) {
			camino.remove(camino.size()-1);
		}
		//marca[origen.getPosition()]=false;
		return ok;
	}
	
	public List<String>caminoConMenorCargaDeCombustible (String ciudad1, String ciudad2, int tanqueAuto){
		List<String>camino=new LinkedList<String>();
		Vertex<String>origen=mapaCiudades.search(ciudad1);
		Vertex<String>destino=mapaCiudades.search(ciudad2);
		if(origen != null && destino != null) {
			caminoConMenor(origen,destino,mapaCiudades,new boolean[mapaCiudades.getSize()],camino,new LinkedList<String>(),new Minimo(Integer.MAX_VALUE),0,tanqueAuto,tanqueAuto);
		}
		return camino;
	}
	public void caminoConMenor(Vertex<String>origen,Vertex<String>destino,Graph<String>mapa,boolean[]marca,List<String>camino,List<String>caminoActual,Minimo min,int cargaTanqueActual,int tanqueActual,int recarga) {
		caminoActual.add(origen.getData());
		marca[origen.getPosition()]=true;
		
		if(origen==destino) {
			if(cargaTanqueActual<min.getMinimo()) {
				min.setMinimo(cargaTanqueActual);
				camino.removeAll(camino);
				camino.addAll(caminoActual);
			}
		}
		else {
			List<Edge<String>>edges=mapa.getEdges(origen);
			Iterator<Edge<String>>it=edges.iterator();
			while(it.hasNext() && cargaTanqueActual<min.getMinimo()) {
				Edge<String>edge=it.next();
				Vertex<String>vertex=edge.getTarget();
				int peso=edge.getWeight();
				if(!marca[vertex.getPosition()]) {
					if(tanqueActual-peso>0) {
						caminoConMenor(vertex,destino,mapa,marca,camino,caminoActual,min,cargaTanqueActual,tanqueActual-peso,recarga);
					}
					else {
						caminoConMenor(vertex,destino,mapa,marca,camino,caminoActual,min,cargaTanqueActual+1,recarga,recarga);
					}
				}
			}
		}
		
		marca[origen.getPosition()]=false;
		caminoActual.remove(caminoActual.size()-1);
	}
	public static void main(String[] args) {
		AdjListGraph<String>grafo=new AdjListGraph<String>();
		
		Vertex<String>vertice1=grafo.createVertex("Buenos Aires");
		Vertex<String>vertice2=grafo.createVertex("Santiago");
		Vertex<String>vertice3=grafo.createVertex("Asuncion");
		Vertex<String>vertice4=grafo.createVertex("Caracas");
		Vertex<String>vertice5=grafo.createVertex("Madrid");
		Vertex<String>vertice6=grafo.createVertex("Roma");
		Vertex<String>vertice7=grafo.createVertex("Tokio");
		Vertex<String>vertice8=grafo.createVertex("Paris");
		
		grafo.connect(vertice1, vertice2,3);
		grafo.connect(vertice1, vertice3,6);
		grafo.connect(vertice2, vertice6,4);
		grafo.connect(vertice3, vertice5,10);
		grafo.connect(vertice3, vertice4,2);
		grafo.connect(vertice4, vertice7,20);
		grafo.connect(vertice4, vertice5,2);
		grafo.connect(vertice5, vertice7,60);
		grafo.connect(vertice6, vertice7,80);
		grafo.connect(vertice7, vertice1,10);
		grafo.connect(vertice8, vertice7,35);
		
		Mapa mapa=new Mapa(grafo);
		/*
		List<String>listaMapa=mapa.devolverCamino("Buenos Aires", "Madrid");
		for(int i=0;i<listaMapa.size();i++) {
			System.out.println("Ciudad:"+listaMapa.get(i));
		}
		*/
		/*
		List<String>ciudades=new LinkedList<String>();
		ciudades.add("Santiago");
		ciudades.add("Madrid");
		List<String>listaMapa2=mapa.devolverCaminoExceptuando("Buenos Aires", "Paris", ciudades);
		for(int i=0;i<listaMapa2.size();i++) {
			System.out.println("Ciudad:"+listaMapa2.get(i));
		}
		*/
		/*
		List<String>listaMapa3=mapa.caminoMasCorto("Buenos Aires", "Tokio");
		for(int i=0;i<listaMapa3.size();i++) {
			System.out.println("Ciudad:"+listaMapa3.get(i));
		}
		 */
		/*
		List<String>listaMapa4=mapa.caminoSinCargarCombustible("Buenos Aires", "Tokio",28);
		for(int i=0;i<listaMapa4.size();i++) {
			System.out.println("Ciudad:"+listaMapa4.get(i));
		}
		 */
		List<String>listaMapa5=mapa.caminoSinCargarCombustible("Buenos Aires", "Tokio",28);
		for(int i=0;i<listaMapa5.size();i++) {
			System.out.println("Ciudad:"+listaMapa5.get(i));
		}
	}

}
