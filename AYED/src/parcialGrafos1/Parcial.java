package parcialGrafos1;
import tp5.ejercicio1.*;
import tp5.ejercicio1.adjList.AdjListGraph;

import java.util.*;
public class Parcial {
	private Vertex<Recinto>buscarEntrada(Graph<Recinto>grafo){
		Vertex<Recinto>vertex=null;
		List<Vertex<Recinto>>vertices=grafo.getVertices();
		Iterator<Vertex<Recinto>>it=vertices.iterator();
		boolean ok=false;
		while(it.hasNext() && !ok) {
			Vertex<Recinto>v=it.next();
			if(v.getData().getNombre().equals("Entrada")&& v.getData().getTiempo()==15) {
				vertex=v;
				ok=true;
			}
		}
		return vertex;
	}
	public int resolver(Graph<Recinto>sitios,int tiempo) {
		Vertex<Recinto>origen=buscarEntrada(sitios);
		int max=0;
		if(origen != null) {
			max=dfs(origen,sitios,new boolean[sitios.getSize()],tiempo-origen.getData().getTiempo(),1);
		}
		return max;
	}
	private int dfs(Vertex<Recinto>origen,Graph<Recinto>grafo,boolean[]marca,int tiempo,int tiempoActual) {
		marca[origen.getPosition()]=true;
		int max=tiempoActual;
		List<Edge<Recinto>>edges=grafo.getEdges(origen);
		Iterator<Edge<Recinto>>it=edges.iterator();
		while(it.hasNext()) {
			Edge<Recinto>edge=it.next();
			Vertex<Recinto>vertex=edge.getTarget();
			int peso=edge.getWeight();
			int tiempoAnimal=vertex.getData().getTiempo();
			if(!marca[vertex.getPosition()] && tiempo-(tiempoAnimal+peso)>=0) {
				int resultado=dfs(vertex,grafo,marca,tiempo-(tiempoAnimal+peso),tiempoActual+1);
				if(resultado>max) {
					max=resultado;
				}
			}
		}
		marca[origen.getPosition()]=false;
		return max;
	}
	public static void main(String[] args) {
		Graph<Recinto> grafo = new AdjListGraph<Recinto>();
        Vertex<Recinto> Entrada = grafo.createVertex(new Recinto("Entrada", 15));
        Vertex<Recinto> Cebras = grafo.createVertex(new Recinto("Cebras", 10));
        Vertex<Recinto> Tigres = grafo.createVertex(new Recinto("Tigres", 10));
        Vertex<Recinto> Flamencos = grafo.createVertex(new Recinto("Flamencos", 10));
        Vertex<Recinto> Murcielagos = grafo.createVertex(new Recinto("Murciélagos", 20));
        Vertex<Recinto> Wallabies = grafo.createVertex(new Recinto("Wallabies", 30));
        Vertex<Recinto> Tortugas = grafo.createVertex(new Recinto("Tortugas", 10));
        Vertex<Recinto> Pumas = grafo.createVertex(new Recinto("Pumas", 10));
        
        grafo.connect(Entrada, Cebras, 10);
        grafo.connect(Cebras, Entrada, 10);
        grafo.connect(Entrada, Tigres, 10);
        grafo.connect(Tigres, Entrada, 10);
        grafo.connect(Entrada, Murcielagos, 20);
        grafo.connect(Murcielagos, Entrada, 20);
        grafo.connect(Entrada, Flamencos, 25);
        grafo.connect(Flamencos, Entrada, 25);
        
        grafo.connect(Tigres, Cebras, 8);
        grafo.connect(Cebras, Tigres, 8);
        grafo.connect(Cebras, Tortugas, 10);
        grafo.connect(Tortugas, Cebras, 10);
        grafo.connect(Flamencos, Murcielagos, 25);
        grafo.connect(Murcielagos, Flamencos, 25);
        grafo.connect(Murcielagos, Wallabies, 10);
        grafo.connect(Wallabies, Murcielagos, 10);
        grafo.connect(Wallabies, Tortugas, 10);
        grafo.connect(Tortugas, Wallabies, 10);
        grafo.connect(Tortugas, Pumas, 15);
        grafo.connect(Pumas, Tortugas, 15);
        
        Parcial p = new Parcial();
        
        System.out.println(p.resolver(grafo, 100));
        System.out.println(p.resolver(grafo, 30));

	}

}
