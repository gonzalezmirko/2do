package parcial2022;
import java.util.*;
import tp5.ejercicio1.*;
import tp5.ejercicio1.adjList.AdjListGraph;
public class Parcial {
	private void marcarCiudadesExceptuadas(List<String>lista,Graph<String>grafo,boolean[]marcas) {
		for(String ciudad:lista) {
			Vertex<String>vertex=grafo.search(ciudad);
			if(vertex!=null) {
				marcas[vertex.getPosition()]=true;
			}
		}
	}
	private void dfs(Graph<String>grafo,Vertex<String>origen,int cantLocalidades,int cantNafta,boolean[]marcas,List<String>camino,List<String>caminoActual) {
		marcas[origen.getPosition()]=true;
		caminoActual.add(origen.getData());
	
		 // Si cumple con la cantidad mínima y es mejor que el mejorCamino hasta ahora
		
		if(caminoActual.size()>=cantLocalidades && caminoActual.size()>camino.size()) {
			camino.removeAll(camino);
			camino.addAll(caminoActual);
		}
			List<Edge<String>>edges=grafo.getEdges(origen);
			Iterator<Edge<String>>it=edges.iterator();
			while(it.hasNext()) {
				Edge<String>edge=it.next();
				Vertex<String>vertex=edge.getTarget();
				int pos=vertex.getPosition();
				int peso=edge.getWeight();
				if(!marcas[pos] && peso<=cantNafta) {
					dfs(grafo,vertex,cantLocalidades,cantNafta-peso,marcas,camino,caminoActual);
				}
			}
		marcas[origen.getPosition()]=false;
		caminoActual.remove(caminoActual.size()-1);
	}
	public List<String>recorrido(Graph<String>grafo,int cantLocalidades,int cantNafta,List<String>localidadesExceptuadas){
		List<String>camino=new LinkedList<String>();
		if(!grafo.isEmpty()) {
			Vertex<String>origen=grafo.search("Mendoza");
			if(origen!=null) {
				boolean[] marcas=new boolean[grafo.getSize()];
				marcarCiudadesExceptuadas(localidadesExceptuadas,grafo,marcas);
				dfs(grafo,origen,cantLocalidades,cantNafta,marcas,camino,new LinkedList<String>());
			}
		}
		return camino;
	}
	public static void main(String[] args) {
		Graph<String> grafo = new AdjListGraph<>();

        Vertex<String> Mendoza = grafo.createVertex("Mendoza");
        Vertex<String> Tunuyan = grafo.createVertex("Tunuyán");
        Vertex<String> SanMartin = grafo.createVertex("San Martín");
        Vertex<String> Malargue = grafo.createVertex("Malargüe");
        Vertex<String> GeneralAlvear = grafo.createVertex("General Alvear");
        Vertex<String> SanRafael = grafo.createVertex("San Rafael");
        Vertex<String> LaPaz = grafo.createVertex("La Paz");
        Vertex<String> SantaRosa = grafo.createVertex("Santa Rosa");

        // Conexiones dirigidas con el peso como consumo de nafta (litros)
        grafo.connect(Mendoza, Tunuyan, 10);
        grafo.connect(Mendoza, SanMartin, 6);
        
        grafo.connect(Tunuyan, Malargue, 12);
        grafo.connect(Tunuyan, SanMartin, 10);
        
        grafo.connect(SanMartin, LaPaz, 8);
        grafo.connect(SanMartin, SanRafael, 13);
        
        grafo.connect(LaPaz, SantaRosa, 2);
        
        grafo.connect(SanRafael, Tunuyan, 11);
        grafo.connect(SanRafael, GeneralAlvear, 7);
        
        grafo.connect(GeneralAlvear, Malargue,6);
        
        
        List<String> exceptuadas = new LinkedList<String>();
        exceptuadas.add("General Alvear");
        exceptuadas.add("La Paz");
        Parcial par=new Parcial();
        System.out.println("Camino: " + par.recorrido(grafo,5,80,exceptuadas));
	}

}
